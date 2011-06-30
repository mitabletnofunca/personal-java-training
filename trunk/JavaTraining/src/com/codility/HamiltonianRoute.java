package com.codility;

import java.util.ArrayList;
import java.util.Collections;

public class HamiltonianRoute {
	
	private ArrayList<Integer> highwayRoute = null;
	
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
		/** Check town route A for violation of 3 Hamiltonian rules
		 * 		1) each road connects distinct towns
		 * 		2) each town is visited either exactly once or exactly thrice
		 * 		3) each road is taken exactly twice
		 */
		if (hasHamRuleViolation(A)) return -2;
		
		//	TODO: Get cul-de-sacs and create (insert) circular highway routes
		/** TODO: Connect all cul-de-sacs (copy orig route A to tempVar;
		 *		insert cul-de-sac routes (roads must be taken twice))
		 */
		createHighwayRoute();
		
		// TODO: get possible HamRoutes from new temp Array (hamRoute)
		
		return returnVal;
	}

	ArrayList<Integer> culDeSacs = new ArrayList<Integer>();
	private void createHighwayRoute() {
		// TODO: Create highway route
		// 		1. Loop thru each cul-de-sac
		// 		2. Find each cul-de-sac in the Integer route array (highwayRoute)
		// 		3. Insert road: cul-de-sac -> <next cul-de-sac, cul-de-sac>
		
		this.highwayRoute = new ArrayList<Integer>(this.routeArr);

		// 1. Loop thru each cul-de-sac
		int nxtCulDeSacIndx = 0;
		for (int i = 0; i < this.culDeSacs.size() ; i++) {
			// 2. Find each cul-de-sac in the Integer route array (highwayRoute).
			ArrayList<Integer> highwayRoad = new ArrayList<Integer>();
			int nextCulDeSac = 0;
			if (i != this.culDeSacs.size()-1) {
				nextCulDeSac=i+1;
			}
			highwayRoad.add(this.culDeSacs.get(nextCulDeSac));
			highwayRoad.add(this.culDeSacs.get(i));
						
			// 3. Insert road: cul-de-sac -> <next cul-de-sac, cul-de-sac>
			//int index = this.highwayRoute.indexOf(this.culDeSacs.get(i)) + nxtCulDeSacIndx;
			String strHighwayRoute = this.highwayRoute.toString();
			int index = strHighwayRoute.indexOf(this.culDeSacs.get(i), nxtCulDeSacIndx);
			nxtCulDeSacIndx = i + 2;
			
			this.highwayRoute.addAll(index+1, highwayRoad);
		}
	}

	// Get roads and store in an Array.
	private ArrayList<String> roads = null;

	// Get route of visited towns and convert to Array.
	private ArrayList<Integer> routeArr = null;
	
	private boolean hasHamRuleViolation(int[] route) {
		
		// HamRule#1: each road connects distinct towns
		// HamRule#2: each town is visited either exactly once or exactly thrice
		// HamRule#3: each road is taken exactly twice

		this.roads = new ArrayList<String>(route.length);
		this.routeArr = new ArrayList<Integer>(route.length);
		
		for (int i=0; i<route.length; i++) {
			this.routeArr.add(route[i]);

			int[] road = { route[i], 0 };
			if ( i != route.length - 1 ) {
				road[1] = route[i+1];
			} else {
				road[1] = route[0];
			}

			StringBuffer strBuff = new StringBuffer();
			strBuff.append(String.valueOf(road[0]));
			strBuff.append(String.valueOf(road[1]));
			this.roads.add(strBuff.toString());
		}
		
		for (int i=0; i<route.length; i++) {
			int[] road = { route[i], 0 };
			if ( i != route.length - 1 ) {
				road[1] = route[i+1];
			} else {
				road[1] = route[0];
			}
			
			// HamRule#1: each road connects distinct towns
			if (road[0] == road[1]) return true;
			
			// HamRule#2: each town is visited either exactly once or exactly thrice
			int townOccurrence = Collections.frequency(this.routeArr, road[0]);
			if (townOccurrence != 1 && townOccurrence != 3) return true;
			
			// Store all cul-de-sac town for creation of highway routes
			if (townOccurrence == 1) this.culDeSacs.add(road[0]);
			
			// HamRule#3: each road is taken exactly twice
			StringBuffer returnRoad = new StringBuffer();
			returnRoad.append(String.valueOf(road[1]));
			returnRoad.append(String.valueOf(road[0]));
			int roadOccurrence = Collections.frequency(this.roads, returnRoad.toString());
			if (roadOccurrence != 1) return true;
		}

		return false;
	}

}
