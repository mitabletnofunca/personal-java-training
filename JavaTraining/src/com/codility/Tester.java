package com.codility;

import java.util.ArrayList;
import java.util.Collections;

public class Tester {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] route1 = { 4, 0, 4, 3, 4, 1, 5, 1, 7, 6, 7, 2, 7, 1 };
		HamiltonianRoute hamRoute = new HamiltonianRoute();
		System.out.println(hamRoute.hamiltonian_routes_count(route1));
		
		int[] route2 = { 4, 4, 0, 3, 4, 1, 5, 1, 7, 6, 7, 2, 7, 1 };
		hamRoute = new HamiltonianRoute();
		System.out.println(hamRoute.hamiltonian_routes_count(route2));
		
		int[] route3 = { 5, 1, 5, 3, 5, 4 };
		hamRoute = new HamiltonianRoute();
		System.out.println(hamRoute.hamiltonian_routes_count(route3));

	}
}
