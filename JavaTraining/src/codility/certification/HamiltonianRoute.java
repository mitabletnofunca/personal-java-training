package codility.certification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class HamiltonianRoute {
	
	private ArrayList<Integer> towns = new ArrayList<Integer>();
	static final int MAX_HAM_ROUTES = 100000000;
	
	/**
	 * 
	 * @param A town route
	 * @return -2 violates any of 3 Hamiltonian rules
	 * @return -1 possible Hamiltonian routes exceed 100M
	 * @return 0 no Hamiltonian route
	 * @return X count of HamRoutes
	 */
	public int hamiltonian_routes_count(int[] A) {

		// Get route of visited towns and convert to array.
		ArrayList<Integer> route = new ArrayList<Integer>(A.length);
		for (int i=0; i<A.length; i++) {
			route.add(A[i]);
		}
		
		/** Check town route A for violation of 3 Hamiltonian rules
		 * 		1) each road connects distinct towns
		 * 		2) each town is visited either exactly once or exactly thrice
		 * 		3) each road is taken exactly twice
		 */
		if (hasHamRuleViolation(route)) return -2;
		
		//	Get cul-de-sacs and create (insert) circular highway routes
		/** Connect all cul-de-sacs (copy orig route to highwayRoute;
		 *		insert cul-de-sac routes (roads taken twice))
		 */
		ArrayList<Integer> highwayRoute = createHighwayRoute(route);
		
		// Get possible Hamiltonian routes from new roads with circular highways
		ArrayList<String> highwayRoads = createRoads(highwayRoute, false);
		towns = new ArrayList<Integer>(new HashSet<Integer>(route));
		
		// Get surrounding towns from start town.
		//ArrayList<Integer> nextTowns = getSurroundingTowns(route);
		ArrayList<Integer> surroundingTowns = new ArrayList<Integer>();
		surroundingTowns.add(A[1]);
		surroundingTowns.add(A[3]);
		surroundingTowns.add(A[5]);
		
		getHamiltonianRoutes(A[0], surroundingTowns, highwayRoads);
		
		// Get count of unique Hamiltonian routes. 
		int cntHamRoute = getHamiltonianRouteCount();

		return cntHamRoute;
	}

	private int getHamiltonianRouteCount() {
		ArrayList<ArrayList<String>> uniqueHamRoutes = new ArrayList<ArrayList<String>>();
		for (ArrayList<Integer> hamRoute : hamiltonianRoutes) {
			ArrayList<String> hamRoads = createRoads(hamRoute, true);
			if (!uniqueHamRoutes.contains(hamRoads)) {
				uniqueHamRoutes.add(hamRoads);
			}
		}
		int cntHamRoutes = 0;
		if (uniqueHamRoutes.size() > MAX_HAM_ROUTES) {
			cntHamRoutes = -1;
		} else {
			cntHamRoutes = uniqueHamRoutes.size();
		}
		return cntHamRoutes;
	}

	private ArrayList<ArrayList<Integer>> hamiltonianRoutes = new ArrayList<ArrayList<Integer>>();
	
	private void getHamiltonianRoutes(Integer town, ArrayList<Integer> surroundingTowns, ArrayList<String> highwayRoads) {
		// How to get Hamiltonian route
		/**
		 * 1. For each town, get all possible roads (from highwayRoute)
		 * 2. For each possible road (from a town), get the end town (town at
		 * the next end of road)
		 * 3. Have a list to keep track if a town was already visited. 
		 * If yes, skip that road. Else, repeat for each possible road until
		 * all roads (from a town) are exhausted. (Repeat for all towns)
		 * 4. Count as Hamiltonian route only if:
		 *   a. All towns were visited
		 *   b. Order of towns (road) taken are unique
		 *     (1-3-4 = 3-4-1 = 1-4-3)
		 */
		
		/* Hamiltonian Routes to be considered are only those that start from 
		 * the first town (A[0])
		//for (Integer town : towns) {
		*/
			//Integer town = towns.get(0);
			ArrayList<Integer> townsVisited = new ArrayList<Integer>(towns.size());
			townsVisited.add(town);
			getHamiltonianRoute(town, surroundingTowns, townsVisited, highwayRoads);
		//}
	}
	
	private void getHamiltonianRoute(Integer town, ArrayList<Integer> surroundingTowns,
			ArrayList<Integer> townsVisited, ArrayList<String> highwayRoads) {
		
		ArrayList<Integer> tempTownsVisited = new ArrayList<Integer>(townsVisited);
		
		for (int i = 0; i < highwayRoads.size() ; i++) {
			String road = highwayRoads.get(i);
			Integer tempNextTown = null;
			if (road.startsWith(town.toString())) {
				tempNextTown = new Integer(road.substring(1));
			} else {
				continue;
			}
			
			/* TODO: Possible performance improvement:
			 *  - Break loop once all surrounding towns of startTown were visited
			 *  before all towns were visited.
			 */
			
			if (tempNextTown != null && !townsVisited.contains(tempNextTown)) {
				tempTownsVisited.add(tempNextTown);
				if (hamiltonianRoutes.contains(tempTownsVisited) 
					|| (tempTownsVisited.containsAll(surroundingTowns)
						&& tempTownsVisited.size() != towns.size())) {
					break;
				} else if (tempTownsVisited.containsAll(towns)) {
					Integer lastTown = tempTownsVisited.get(tempTownsVisited.size()-1);
					if (surroundingTowns.contains(lastTown))
						hamiltonianRoutes.add(tempTownsVisited);
					break;
				} else {
					getHamiltonianRoute(tempNextTown, surroundingTowns, tempTownsVisited, highwayRoads);
				}
				tempTownsVisited = new ArrayList<Integer>(townsVisited);
			}
		}
	}

	ArrayList<Integer> culDeSacs = new ArrayList<Integer>();

	private ArrayList<Integer> createHighwayRoute(ArrayList<Integer> route) {
		// Create highway route
		// 		1. Loop thru each cul-de-sac
		// 		2. Find index of each cul-de-sac in the highwayRoute
		// 		3. Insert highway road: cul-de-sac -> <next cul-de-sac, cul-de-sac>
		
		ArrayList<Integer> highwayRoute = new ArrayList<Integer>(route);

		// 1. Loop thru each cul-de-sac
		int insertIdx = 0;
		for (int i = 0; i < this.culDeSacs.size() ; i++) {
			
			// create highway road to next cul-de-sac
			ArrayList<Integer> highwayRoad = new ArrayList<Integer>();
			int nextCulDeSac = 0;
			if (i != this.culDeSacs.size()-1) {
				nextCulDeSac=i+1;
			}
			highwayRoad.add(this.culDeSacs.get(nextCulDeSac));
			highwayRoad.add(this.culDeSacs.get(i));

			// 2. Find index of each cul-de-sac in the highwayRoute.
			// 3. Insert highwayRoad to highwayRoute: cul-de-sac, <next cul-de-sac, cul-de-sac>
			for ( ; insertIdx < highwayRoute.size(); insertIdx++ ) {
				if (highwayRoute.get(insertIdx).equals(this.culDeSacs.get(i))) {
					highwayRoute.addAll(insertIdx+1, highwayRoad);
					insertIdx = insertIdx + 2;
					break;
				}
			}
		}
		return highwayRoute;
	}

	// Get roads and store in an Array.
	//private ArrayList<String> roads = null;

	private boolean hasHamRuleViolation(ArrayList<Integer> route) {
		
		// HamRule#1: each road connects distinct towns
		// HamRule#2: each town is visited either exactly once or exactly thrice
		// HamRule#3: each road is taken exactly twice

		ArrayList<String> roads = createRoads(route, false);
		
		for (int i=0; i<route.size(); i++) {
			int[] road = { route.get(i), 0 };
			if ( i != route.size() - 1 ) {
				road[1] = route.get(i+1);
			} else {
				road[1] = route.get(0);
			}
			
			// HamRule#1: each road connects distinct towns
			if (road[0] == road[1]) return true;
			
			// HamRule#2: each town is visited either exactly once or exactly thrice
			int townOccurrence = Collections.frequency(route, road[0]);
			if (townOccurrence != 1 && townOccurrence != 3) return true;
			
			// Store all cul-de-sac town for creation of highway routes
			if (townOccurrence == 1) this.culDeSacs.add(road[0]);
			
			// HamRule#3: each road is taken exactly twice
			StringBuffer returnRoad = new StringBuffer();
			returnRoad.append(String.valueOf(road[1]));
			returnRoad.append(String.valueOf(road[0]));
			int roadOccurrence = Collections.frequency(roads, returnRoad.toString());
			if (roadOccurrence != 1) return true;
		}

		return false;
	}

	private ArrayList<String> createRoads(ArrayList<Integer> route, boolean reverseRoad) {
		
		ArrayList<String> roads = new ArrayList<String>(route.size());
		
		for (int i=0; i<route.size(); i++) {
			int[] road = { route.get(i), 0 };
			if ( i != route.size() - 1 ) {
				road[1] = route.get(i+1);
			} else {
				road[1] = route.get(0);
			}

			StringBuffer strBuff = new StringBuffer();
			if (reverseRoad && (road[0] > road[1])) {
				strBuff.append(String.valueOf(road[1]));
				strBuff.append(String.valueOf(road[0]));
			} else {
				strBuff.append(String.valueOf(road[0]));
				strBuff.append(String.valueOf(road[1]));
			}
			roads.add(strBuff.toString());
		}

		if (reverseRoad) {
			Collections.sort(roads);
		}
		
	    return roads;
    }

}
