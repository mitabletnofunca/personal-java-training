package codility.task;


public class EqualizationStepsTester {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] A = { 11, 3, 7, 1 };
		EqualizationSteps instance = new EqualizationSteps();
		System.out.println(instance.equalization_steps(A));
		
		int[] B = {12, 4, 8, 0};
		System.out.println(instance.equalization_steps(B));
		
		int[] C = {1, 1, 1, 1};
		System.out.println(instance.equalization_steps(C));
	}
}
