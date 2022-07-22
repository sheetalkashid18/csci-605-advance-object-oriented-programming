//CSCI.605.04 - Advance OO Programming Concepts
//Homework 4.1
//BattleShip.java
//Submitted by Anisha pareek and Sheetal Kashid

package hw_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class BattleShip {
	
	// Number of rows in the ocean_matrix
	static int ROWS;
	
	// Number of columns in the ocean_matrix
	static int COLUMNS;
	
	// Stores the state of ocean with all the information about the 
	// boat positions in it
	static String[][] OCEAN_MATRIX;
	
	// Stores the state of the ocean with the information that is 
	// to be displayed to the user
	static String[][] OCEAN_SEEN_BY_USER;
	
	// The maximum number of hits allowed
	static int MAX_HITS = 5;
	
	// Stores the value of every position in the ocean_matrix that 
	// does not contain water i.e. all the boat id's (repeated, probably)
	static int[] boatArray;
	
	static int lengthOfBoatArray;
	static int totalNumberOfBoats;
	
	// Updates the state of the ocean after it has been hit by the user
	public static void updateOcean(int row, int col) {
		
		// Checks if the user has hit at water
		// If yes, update the state of the ocean as seen by the user
		// and display a 'w' at the corresponding position
		if ( OCEAN_MATRIX[row][col].equals("w")) {
			OCEAN_SEEN_BY_USER[row][col] = "w";
		}
		
		// Checks if the boat was not already hit by the user
		else if ( !OCEAN_MATRIX[row][col].equals("X")) {
			
			System.out.println("HIT");
			
			// Decrement the total number of boats floating in the ocean by 1
			totalNumberOfBoats -= 1;
			
			// Get the boat_id that has been hit
			String boat_id = OCEAN_MATRIX[row][col];
			
			// Check if the boat spans in the vertical direction by keeping the column
			// value same and changing the row values. Replace every position 
			// with the same boat_id by 'x'
			for ( int index = 0; index < ROWS; index++) {
				
				if (OCEAN_MATRIX[index][col].equals(boat_id)) {
					
					OCEAN_MATRIX[index][col] = "X";
					OCEAN_SEEN_BY_USER[index][col] = "X";
				}
			}
			
			// Check if the boat spans in the horizontal direction by keeping the row
			// value same and changing the column values. Replace every position 
			// with the same boat_id by 'x'
			for(int index = 0; index < COLUMNS; index++) {
				
				if (OCEAN_MATRIX[row][index].equals(boat_id)) {

					OCEAN_MATRIX[row][index] = "X";
					OCEAN_SEEN_BY_USER[row][index] = "X";
				}
			}
		}

	}
	
	// Initialize the state of the ocean as to be displayed to the user initially
	public static void initializeOceanSeenByUser() {
		
		OCEAN_SEEN_BY_USER = new String[ROWS][COLUMNS];
		for ( int row_num = 0; row_num < ROWS; row_num++ ) {
			for ( int col_num = 0; col_num < COLUMNS; col_num++ ) {
				OCEAN_SEEN_BY_USER[row_num][col_num] = ".";
			}
		}
	}
	
	public static void playGame() {
		
		printOceanForUser();
		
		// Number of times the user has tried to hit any boat
		int numberOfHits = 0;
		
		Scanner sc = new Scanner(System.in);
		
		// Run the loop while there is any boat left to be hit and while the number 
		// of hits by the user is less than the maximum number of hits allowed
		while ( totalNumberOfBoats > 0 && numberOfHits++ < MAX_HITS) {
			
			// Input the row coordinate
			System.out.print("row coordinate (0 <= row < " + ROWS + "): ");
			int row = sc.nextInt();
			
			// Input the column coordinate
			System.out.print("column coordinate (0 <= column < " + COLUMNS + "): ");
			int col = sc.nextInt();
			
			// Update the state of the ocean after the hit 
			updateOcean(row, col);
			
			printOceanForUser();
	
		}
		
		if ( totalNumberOfBoats == 0 ) {
			System.out.println("Game Over, all boats have been hit.");
		}
		else {
			System.out.println("You have exhausted all your attempts. The ocean is: ");
			printOcean();
		}
		
		sc.close();
	}
	
	// Print the actual state of the ocean with all the information
	// about the position of the boats
	public static void printOcean() {
		
		for ( int row = 0; row < ROWS; row++ ) {
			for ( int col = 0; col < COLUMNS; col++ ) {
				System.out.print(OCEAN_MATRIX[row][col] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	// Print the state of the ocean as to be displayed to the user
	// Shows only the information guessed by the user
	public static void printOceanForUser() {
		
		System.out.println("\nX indicates a hit.");
		System.out.println("w indicates a miss, but you know now there is water.\n");
		
		for ( int row = 0; row < ROWS; row++ ) {
			for ( int col = 0; col < COLUMNS; col++ ) {
				System.out.print(OCEAN_SEEN_BY_USER[row][col] + " ");
			}
			System.out.println();
		}
		
		System.out.println();
	}
	
	// Reads the file entered by the user and stores the information 
	// in the OCEAN_MATRIX array
	public static void readFile(File battleFieldFile) {
		
		try {
			
			Scanner sc = new Scanner(battleFieldFile);
			
			int row_num = 0;
			
			boolean isInitialized = false;
			
			while(sc.hasNextLine()) {
				
				// Trim any whitespaces at the beginning or end of the String
				// Parse the String with one or more whitespaces acting as the delimiter
				String[] wordsInALine = (sc.nextLine()).trim().split("\\s+");
				
				String keyword = wordsInALine[0];
				
				if ( keyword.equals("width") ) {
					COLUMNS = Integer.parseInt( wordsInALine[1] );
				}
				else if ( keyword.equals("height") ) {
					ROWS = Integer.parseInt(wordsInALine[1]);
				}
				
				// If the keyword == "row"
				else {
					
					// Check if the OCEAN_MATRIX has not been initialized before
					// If yes, skip it. If no, initialize it.
					if ( !isInitialized ) {
						OCEAN_MATRIX = new String[ROWS][COLUMNS];
						isInitialized = true;
					}
					
					for ( int col_num = 0; col_num < COLUMNS; col_num++ ) {
						OCEAN_MATRIX[row_num][col_num] = wordsInALine[col_num + 1];
					}
					
					row_num += 1;
				}
			}
			sc.close();
			
			
		} catch (FileNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Counts the total number of boats in the ocean
	public static void countTotalBoats() {
		
		int count = 0;
		
		// Counts the unique boat id's in the boatArray
		for ( int index = 0; index < lengthOfBoatArray; index++) {
			count++;
			int initial = boatArray[index];
			while(boatArray[index + 1] == initial) {
				index++;
			}
		}
		totalNumberOfBoats = count;
	}
	
	// Initialize the boatArray with the boat id's in the ocean
	public static void initializeBoatArray() {
		
		int index = 0;
		
		// Set the length of the boatArray equal to the maximum length possible
		// which will happen when every position in the ocean_matrix is occupied
		// by a boat
		boatArray = new int[ROWS * COLUMNS];
		
		// Store the value of every position in the ocean_matrix array 
		// that does not contain water in the boatArray 
		for ( int row_num = 0; row_num < ROWS; row_num++ ) {
			for ( int col_num = 0; col_num < COLUMNS; col_num++ ) {
				if ( !OCEAN_MATRIX[row_num][col_num].equals("w")) {
					boatArray[index++] = Integer.parseInt(OCEAN_MATRIX[row_num][col_num]);
				}
			}
		}	
		
		// Store the total number of boat id's in the boatArray
		lengthOfBoatArray = index;
		
		// Sort the boatArray to count the total number of unique boat id's
		Arrays.sort(boatArray);
		
		countTotalBoats();

	}
	
	public static void main(String args[]) {
		
		// Read the file specified in the command line
		readFile(new File(args[0]));
		
		// Initialize the boatArray with the boat id's in the ocean
		initializeBoatArray();
		
		// Initialize the state of the ocean as to be displayed to the user initially
		initializeOceanSeenByUser();
		
		// If there are no boats in the ocean, do not play the game
		if ( totalNumberOfBoats > 0 ) {
			playGame();
		}
	}
	
}
