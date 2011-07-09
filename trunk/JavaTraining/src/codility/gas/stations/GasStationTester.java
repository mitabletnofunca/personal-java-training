package codility.gas.stations;


public class GasStationTester {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] distance = { 10, 9, 8 };
		int[] price = { 2, 1, 3 };
		int tank = 15;
		GasStations gasStations = new GasStations();
		/* 
		 * Return value should be 41:
		 *  Refill strategy: R = { 10, 15, 2 }
		 *  Cost: C = 10*2 + 15*1 + 2*3
		 */
		System.out.println(gasStations.gas_stations(distance, price, tank));
		
	}
}
