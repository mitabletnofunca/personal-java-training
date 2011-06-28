package com.codility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class HamiltonianRoute {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] route1 = { 4, 0, 4, 3, 4, 1, 5, 1, 7, 6, 7, 2, 7, 1 };
		System.out.println(hamiltonian_routes_count(route1));
		
		int[] route2 = { 4, 4, 0, 3, 4, 1, 5, 1, 7, 6, 7, 2, 7, 1 };
		System.out.println(hamiltonian_routes_count(route2));
		
		

	}
	
	/**
	 * 
	 * @param A town route
	 * @return -2 violates any of 3 HamRules
	 * @return -1 cntHamRoute exceed 100M
	 * @return 0 no HamRoute
	 * @return X count of HamRoutes
	 */
	public static int hamiltonian_routes_count(int[] A) {
		
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
		
		// TODO: get possible HamRoutes from new temp Array (hamRoute)
		
		return returnVal;
	}

	private static boolean hasHamRuleViolation(int[] route) {
		
		// HamRule#1: each road connects distinct towns
		// HamRule#2: each town is visited either exactly once or exactly thrice
		// HamRule#3: each road is taken exactly twice

		// Get roads and store in an Array.
		ArrayList<String> roads = new ArrayList<String>(route.length);
		// Get route of visited towns and convert to Array.
		ArrayList<Integer> routeArr = new ArrayList<Integer>(route.length);
		for (int i=0; i<route.length; i++) {
			routeArr.add(route[i]);

			int[] road = { route[i], 0 };
			if ( i != route.length - 1 ) {
				road[1] = route[i+1];
			} else {
				road[1] = route[0];
			}

			StringBuffer strBuff = new StringBuffer();
			strBuff.append(String.valueOf(road[0]));
			strBuff.append(String.valueOf(road[1]));
			roads.add(strBuff.toString());
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
			int townOccurrence = Collections.frequency(routeArr, road[0]);
			if (townOccurrence != 1 && townOccurrence != 3) return true;
			
			// HamRule#3: each road is taken exactly twice
			StringBuffer returnRoad = new StringBuffer();
			returnRoad.append(String.valueOf(road[1]));
			returnRoad.append(String.valueOf(road[0]));
			int roadOccurrence = Collections.frequency(roads, returnRoad.toString());
			if (roadOccurrence != 1) return true;
		}

		return false;
	}

}
