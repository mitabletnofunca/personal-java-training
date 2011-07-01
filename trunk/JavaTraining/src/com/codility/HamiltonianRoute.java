package com.codility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class HamiltonianRoute {
	
	/**
	 * 
	 * @param A town route
	 * @return -2 violates any of 3 HamRules
	 * @return -1 possible Hamiltonian routes exceed 100M
	 * @return 0 no Hamiltonian route
	 * @return X count of HamRoutes
	 */
	public int hamiltonian_routes_count(int[] A) {

		// Get route of visited towns and convert to Array.
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
		ArrayList<String> highwayRoads = createRoads(highwayRoute);
		
		ArrayList<Integer> towns = new ArrayList<Integer>(new HashSet<Integer>(route));
		// TODO: get possible HamRoutes from new temp Array (hamRoute)
		int cntHamRoute = getHamiltonianRoutes(towns, highwayRoads);
		
		return cntHamRoute;
	}

	private int getHamiltonianRoutes(ArrayList<Integer> towns,
			ArrayList<String> highwayRoads) {
		// TODO: How to get Hamiltonian route
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
		
		ArrayList<ArrayList<Integer>> hamiltonianRoutes = new ArrayList<ArrayList<Integer>>();
		for (Integer town : towns) {
			//ArrayList<Integer> hamiltonianRoute = new ArrayList<Integer>();
			ArrayList<Integer> townsVisited = new ArrayList<Integer>(towns.size());
			townsVisited.add(town);
			
			// Get all roads from the town.
			//   3 is the count of road possible from each town
			ArrayList<String> roadsFromTown = new ArrayList<String>(3);
			for (String road : highwayRoads) {
				if (road.startsWith(town.toString())) {
					roadsFromTown.add(road);
				}
			}
			
			for (int i = 0; i < roadsFromTown.size() ; i++) {
				Integer nextTown = new Integer(roadsFromTown.get(i).substring(1));
				townsVisited.add(nextTown);
				
				for (int j = 0 ; j < highwayRoads.size() ; j++ ) {
					String road = highwayRoads.get(j);
					if (road.startsWith(nextTown.toString())) {
						Integer tempNextTown = new Integer(road.substring(1));
						if (!townsVisited.contains(tempNextTown)) {
							townsVisited.add(new Integer(tempNextTown));
							nextTown = tempNextTown;
							j = -1;
						}
						if (townsVisited.containsAll(towns)) {
							hamiltonianRoutes.add(townsVisited);
							townsVisited = new ArrayList<Integer>(towns.size());
							townsVisited.add(town);
							break;
						}
					}					
				}

			}
		}
		return hamiltonianRoutes.size();
	}
	
	private void scratchGetHamiltonianRoute() {
		/*
		// Get all roads from the town.
		//   3 is the count of road possible from each town
		ArrayList<String> roadsFromTown = new ArrayList<String>(3);
		for (String road : highwayRoads) {
			if (road.startsWith(town.toString())) {
				roadsFromTown.add(road);
			}
		}
		
		for (int i = 0; i < roadsFromTown.size() ; i++) {
			Integer nextTown = new Integer(roadsFromTown.get(i).substring(1));
			townsVisited.add(nextTown);
			
			for (String road : highwayRoads) {
				if (road.startsWith(town.toString())) {
					town = new Integer(road.substring(1));
					if (!townsVisited.contains(town)) {
						townsVisited.add(new Integer(town));
					}
					if (townsVisited.containsAll(towns)) {
						hamiltonianRoutes.add(townsVisited);
						break;
					}
				}					
			}

		}
		*/
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

		ArrayList<String> roads = createRoads(route);
		
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

	private ArrayList<String> createRoads(ArrayList<Integer> route) {
		
		ArrayList<String> roads = new ArrayList<String>(route.size());
		
		for (int i=0; i<route.size(); i++) {
			int[] road = { route.get(i), 0 };
			if ( i != route.size() - 1 ) {
				road[1] = route.get(i+1);
			} else {
				road[1] = route.get(0);
			}

			StringBuffer strBuff = new StringBuffer();
			strBuff.append(String.valueOf(road[0]));
			strBuff.append(String.valueOf(road[1]));
			roads.add(strBuff.toString());
		}

	    return roads;
    }

}
