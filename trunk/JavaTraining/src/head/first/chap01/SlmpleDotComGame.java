package head.first.chap01;

import head.first.helpers.GameHelper;

public class SlmpleDotComGame {
	public static void main(String[] args) {
		int numOfGuesses = 0;
		GameHelper helper = new GameHelper();
		SimpleDotCom dot = new SimpleDotCom();
		
		// TODO: NG#1.
		//int[] location = { 1, 2, 3 };

		int startLoc = (int) Math.random() * 5;
		int[] location = { startLoc, startLoc+1, startLoc+2 };
		dot.setLocationCells(location);

		// TODO: NG#4.
		// int numOfGuesses = 0;
		String result = "";
		while (!result.equals("kill")) {
			// TODO: NG#2.
			//numOfGuesses++;
			
			// TODO: NG#3.
			// GameHelper helper = new GameHelper();
			String userInput = helper.getUserInput("Enter a number ");
			result = dot.checkYourself(userInput);
			numOfGuesses++;
		}
		
		System.out.println("You took " + numOfGuesses + " guesses");
	}
}
