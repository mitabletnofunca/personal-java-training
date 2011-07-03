package codility.gas.stations;


public class GasStationTester {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] distance = { 4, 0, 4, 3, 4, 1, 5, 1, 7, 6, 7, 2, 7, 1 };
		int[] price = { 4, 0, 4, 3, 4, 1, 5, 1, 7, 6, 7, 2, 7, 1 };
		int tank = 15;
		GasStations gasStations = new GasStations();
		// return value should be 41
		System.out.println(gasStations.gas_stations(distance, price, tank));
		
	}
}
