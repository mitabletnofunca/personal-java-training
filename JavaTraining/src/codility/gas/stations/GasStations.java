package codility.gas.stations;

public class GasStations {
	
	
	/**
	 * @param D distances
	 * @param P prices
	 * @param T tank capacity
	 * @return
	 * 	-1 no valid refill strategy exists<br>
	 * 	-2 result exceeds 1,000,000,000<br>
	 * 	C cost of cheapest refill strategy
	 * 
	 */
	public int gas_stations(int[] D, int[] P, int T) {
		
		/*
		 * Problem points:
		 * 1) How to compute for remaining fuel
		 * 2) Algo for computing the best refill strategy (R[])
		 *   - possible algo:
		 *     - first refill strategy (R[0]) will range from
		 *     distance of first town (D[0]), up to tank capacity (T).
		 * Sequence L: amount of fuel when leaving town
		 *   L[K] = (R[0]+...+R[K]) - (D[0]+...D[k-1])
		 * Sequence A: amount of fuel when arriving at town
		 *   A[K] = L[K-1] - D[K-1]
		 * Number C: total cost of refill strategy
		 *   C = R[0]*P[0] +...+ R[N-1]*P[N-1]
		 */
		
		// TODO: Step 1: Create logic for creating a refill strategy (regardless if best or not);
		//   Just need to come up with a logic for generating a refill strategy.
		// TODO: Step 2: Identify the best/cheapest refill strategy.
		// TODO: Step 3: Compute Cost (C) based on the best/cheapest refill strategy (R):
		//   C = R[0]*P[0] +...+ R[N-1]*P[N-1]
		return 0;
	}
}
