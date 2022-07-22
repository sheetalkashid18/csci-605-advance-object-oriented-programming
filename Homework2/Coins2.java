package hw_605_2;

public class Coins2 {

	// Stores the combination of coins needed to pay the amount	
	// along with the negative coins which the cashier pays back
	static int[] coinsToPay; 
	
	// Stores the coins that cashier gives back
	static int[] coinsCashierGivesBack;
	
	// Stores all the coins given initially
	static int[] coinsIHave;
	
	// Stores the number of coins remaining so far after paying 
	// the amount and receiving back the coins from the cashier
	static int coinsRemainingSoFar;
	
	// Recursively checks for all the possible combinations of coins which can 
	// be used to pay the given amount and stores the longest possible 
	// combination in coinsToPay array
	public static void findCombinations(int[] coins, int amount) {
		// Checks if the amount to be paid is 0
		if ( amount == 0) {
			return;
		}

		// Checks if there is only one coin in the coins array
		if (coins.length == 1) {

			// Checks if the value of the coin is equal to the amount to pay
			if (coins[0] == amount) {

				// Compares the number of coins remaining so far and the number 
				// of coins that will be left if the newly generated combination
				// of coins is used and stores the minimum of the two
				if ( coinsRemainingSoFar > findNumberOfRemainingCoins(coins) ) {

					coinsRemainingSoFar = findNumberOfRemainingCoins(coins);
					coinsToPay = coins;
				}
			}
		}


		// Checks if there are no coins in the coins array
		else if (coins.length < 1) {
			System.out.println("Not possible to pay.");
			return;
		}


		// If the number of coins in the coins array is greater than 1
		else {

			// Checks if the sum of the coins is less than the amount to pay
			if (calculatePositiveSum(coins) < amount) {
				return;
			}

			// If the number of coins is greater than 1
			else {

				// Checks if the sum of the coins is equal to the amount to pay
				if (calculateSum(coins) == amount) {

					// Compares the number of coins remaining so far and the number 
					// of coins that will be left if the newly generated combination
					// of coins is used and stores the minimum of the two
					if ( coinsRemainingSoFar > findNumberOfRemainingCoins(coins) ) {

						coinsRemainingSoFar = findNumberOfRemainingCoins(coins);
						coinsToPay = coins;
					}
				}

				// Removes one coin at a time to generate all possible 
				// combinations of immediately smaller length
				for (int j = 0; j < coins.length; j++) {

					int[] newCoins = new int[coins.length - 1];
					int k  = 0;

					for ( int i = 0; i < coins.length; i++) {

						if (j != i) {

							// Stores each newly generated combination in newCoins
							newCoins[k++] = coins[i];  
						}
					}

					// recursively finds all the possible combinations from the 
					// newly found combination
					findCombinations(newCoins, amount);
				}
			}
		}
	}
	
	
	// Returns the number of coins remaining after paying
	// and receiving coins(if any) from the cashier
	public static int findNumberOfRemainingCoins(int[] coins) {
		
		// Stores the number of negative coins in the coins array
		int numberOfNegativeCoins = 0;
		
		// Finds the total number of negative coins
		for (int index = 0; index < coins.length; index++) {
			if (coins[index] < 0 ) {
				numberOfNegativeCoins += 1;
			}
		}
		
		int noOfPositiveCoins = coins.length - numberOfNegativeCoins;
		int coinsRemainingNow = coinsIHave.length - noOfPositiveCoins + numberOfNegativeCoins;
		
		return coinsRemainingNow;
	}
	 
	 
	 // Calculates and returns the sum of all the elements in an array
	 public static int calculateSum(int[] arr) {
		 int sum = 0;
		 for(int i = 0; i < arr.length; i++) {
			 sum += arr[i];
		 }
		 return sum;
	 }
	 
	 // Calculates the sum of the positive(the coins that payer has) coins
	 public static int calculatePositiveSum(int[] coins) {
		 int sum = 0;
		 for(int i = 0; i < coins.length; i++) {
			 if (coins[i] > 0) {
				 sum += coins[i];
			 }
		 }
		 return sum;
	 }
	 
	 
	 // Prints the given array in reverse order to match with the output required
	 public static void printArray(int[] arr) {
		 
		 // Keeps track of the number of coins cashier gives back
		 int index = 0;
		 
		 // Prints the coins that need to be paid and stores the 
		 // negative coins(coins that cashier gives back) in the
		 // coinsCashierGivesBack array
		 for (int i = arr.length - 1; i >= 0; i--) {
			 
			 if (arr[i] < 0) {
				 coinsCashierGivesBack[index++] = arr[i] * (-1);
			 }
			 else {
				// Appends "cents" with each coin value to format the output
				 System.out.print(arr[i] + " cents "); 
			 }
			 
		 }
		 
		 System.out.print("and the cashier gave me ");
		 
		 // Checks if the cashier gives any coins back or not
		 // If yes, then prints those coins
		 if (index > 0) {
			 
			 for(int j = 0; j < index; j++) {
				 System.out.print(coinsCashierGivesBack[j] + " cents ");
			 }
		 }
		 
		// When the cashier does not give any coins back
		 else { 
			 System.out.print("0 cents ");
		 }
		 
		 System.out.println();
	 }
	 
	 // Takes the amount that needs to be paid as a parameter and
	 // prints the combination of coins that needs to be paid
	 // and that needs to be received(if any) from the cashier
	 public static void printTheCombination(int amount) {
		 
		// Checks if there are any coins in the coinsToPay
		 if (coinsToPay.length > 0  || amount == 0) {
			 System.out.print(amount + " cents:\tI gave the cashier the following coins ");
			 if (amount == 0) {
				 System.out.println("0 cents");
			 }
			 else {
				 printArray(coinsToPay);
			 }
		 }
		 else {
			 System.out.println(amount + " cents:\tcan not be paid");
		 }
	 }
	 
	 public static void main(String args[]) {
		 
		 int[] coins = {1, 1, 2, 2, 3};
		 int[] toPay = { 7, 5, 4, 1, 0 };
		 int[] cashierCoins = { 1, 2 };
		 
		 // Store the coins in a global array so as to be able
		 // to access them in the findCombinations() method
		 coinsIHave = coins;
		 
		 // Initialize it with the maximum length possible which is the 
		 // total number of cashierCoins itself
		 coinsCashierGivesBack = new int[cashierCoins.length];
		 
		 // Stores together the coins available and the coins that the cashier 
		 // can give back (as negative coins)
		 int[] totalCoins = new int[cashierCoins.length + coins.length];
		 for(int index = 0; index < coins.length; index++) {
			 totalCoins[index] = coins[index];
		 }
		 for(int index = 0; index < cashierCoins.length; index++) {
			 totalCoins[coins.length + index] = cashierCoins[index] * (-1);
		 }
		 
		 // Contains the maximum number of coins that the payer 
		 // can be left with
		 coinsRemainingSoFar = totalCoins.length + 1;

		 // Finds combination of coins for each toPay amount
		 for (int i = 0; i < toPay.length; i++) {
			 
			 // Finds various combinations of coins
			 findCombinations(totalCoins, toPay[i]);
			 
			 printTheCombination(toPay[i]);
			 
			 // Resets the value of all global arrays
			 int[] empty = {};
			 coinsToPay = empty;
			 coinsRemainingSoFar = totalCoins.length + 1;
			 coinsIHave = coins;
			 coinsCashierGivesBack = new int[cashierCoins.length];
			 
		 }
		 			 
	 }
	

}
