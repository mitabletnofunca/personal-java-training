package com.codility;

import java.util.ArrayList;
import java.util.Collections;

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

		int returnVal = 0;

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
		
		//	TODO: Get cul-de-sacs and create (insert) circular highway routes
		/** TODO: Connect all cul-de-sacs (copy orig route A to tempVar;
		 *		insert cul-de-sac routes (roads must be taken twice))
		 */
		ArrayList<Integer> highwayRoute = createHighwayRoute(route);
		
		ArrayList<String> highwayRoads = createRoads(highwayRoute);
		
		// TODO: get possible HamRoutes from new temp Array (hamRoute)
		
		return returnVal;
	}

	ArrayList<Integer> culDeSacs = new ArrayList<Integer>();

	private ArrayList<Integer> createHighwayRoute(ArrayList<Integer> route) {
		// TODO: Create highway route
		// 		1. Loop thru each cul-de-sac
		// 		2. Find each cul-de-sac in the Integer route array (highwayRoute)
		// 		3. Insert road: cul-de-sac -> <next cul-de-sac, cul-de-sac>
		
		ArrayList<Integer> highwayRoute = new ArrayList<Integer>(route);

		// 1. Loop thru each cul-de-sac
		int insertIdx = 0;
		for (int i = 0; i < this.culDeSacs.size() ; i++) {
			
			// create road
			ArrayList<Integer> highwayRoad = new ArrayList<Integer>();
			int nextCulDeSac = 0;
			if (i != this.culDeSacs.size()-1) {
				nextCulDeSac=i+1;
			}
			highwayRoad.add(this.culDeSacs.get(nextCulDeSac));
			highwayRoad.add(this.culDeSacs.get(i));

			// 2. Find each cul-de-sac in the highwayRoute.
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
