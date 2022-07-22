//CSCI.605.04 - Advance OO Programming Concepts
//Homework 3.2
//StringIntegerArrays
//Submitted by Anisha pareek and Sheetal Kashid


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Picture {
	
	// Holds all the characters in the picture file
	static Vector<Character> pictureCharacters = new Vector<>();
	
	// Holds the word strings in words file
	static Vector<String> words = new Vector<>();
	
	// Holds the characters correctly guessed in a word
	static Vector<Character> charactersGuessed = new Vector<>();
	
	// Holds all the characters in a word
	static Vector<Character> charsInAWord = new Vector<>();
	
	// Maximum number of guesses allowed for each word
	static int MAX_GUESSES = 9;
	
	// Stores the characters in a word into the charsInAWord vector
	public static void generateChars(String word) {
		for ( int i = 0; i < word.length(); i++) {
			charsInAWord.add(word.charAt(i));
		}
	}
	
	// Prints every Xth character in the picture file
	// Prints a dot for all the remaining indices
	public static void displayPicture(int printEveryXthChar) {
		
		int indexToPrint = printEveryXthChar - 1;
		
		for (int j = 0; j < pictureCharacters.size(); j++) {
			
			// If the character is a newline, increment indexToPrint by 1
			if (pictureCharacters.get(j) == '\n') {
				indexToPrint += 1;
				System.out.println();
			}
			
			else if ( j == indexToPrint ) {
				System.out.print(pictureCharacters.get(j));
				indexToPrint += printEveryXthChar;
			}
			else {
				System.out.print(".");
			}
		}
	}
	
	public static void main(String args[]) {
		
		File wordsFile = new File(args[0]);
		File pictureFile = new File(args[1]);
		
		Scanner sc;
		
		try {
			sc = new Scanner(pictureFile);
			
			// Read the picture file and store the characters in the 
			// 'pictureCharacters' vector
			while ( sc.hasNextLine()) {
				String line = sc.nextLine();
				for(int i = 0; i < line.length(); i++) {
					pictureCharacters.add(line.charAt(i));
				}
				pictureCharacters.add('\n');
			}
			sc = new Scanner(wordsFile);
			
			// Read the words file and store the words in the 'words' vector
			while (sc.hasNextLine()) {
				words.add(sc.nextLine());
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// To store the length of the random word to be picked for guessing
		int lengthOfWord;
		
		// To store the total number of correct guesses so far for the current word
		int soManyCorrectGuesses;
		
		// To store the totalGuesses made by the user whether right or wrong
		int totalGuesses;
				
		// While there is any word left in the 'words' vector
		while(words.size() > 0) {
			
			int randomIndex = (int)(Math.random() * words.size());
			String randomWord = words.get(randomIndex);
			lengthOfWord = randomWord.length();
			
			// Remove the currently generated random word from the words vector
			words.remove(randomIndex);
			
			// Generate the characters in the current word and store in the
			// 'charsInAWord' vector
			generateChars(randomWord);
			
			soManyCorrectGuesses = 0;
			
			// Every Xth character that needs to be printed
			int printEveryXthChar = (lengthOfWord - soManyCorrectGuesses) + 1;
			
			displayPicture(printEveryXthChar);
			
			totalGuesses = 0;
			int count = 0;
			
			while(totalGuesses < MAX_GUESSES && soManyCorrectGuesses < lengthOfWord) {

				int charsUnguessed = lengthOfWord - charactersGuessed.size();
				
				System.out.print(count++ + ": ");
				
				// Prints the characters guessed correctly, if any, by the user
				for ( int k = 0; k < charactersGuessed.size(); k++) {
					System.out.print(charactersGuessed.get(k));
				}
				
				// Prints dots for the number of characters still unguessed
				for (int k = 0; k < charsUnguessed; k++) {
					System.out.print(".");
				}
				
				// Add a line 
				System.out.println();
				
				sc = new Scanner(System.in);
				char guess = sc.next().charAt(0);
				
				// Check if the word contains the guess 
				if (charsInAWord.contains(guess)) {
					charactersGuessed.add(guess);
					
					// remove the character that has been correctly guessed
					charsInAWord.remove(charsInAWord.indexOf(guess)); 
					soManyCorrectGuesses += 1;
				}
				else {
					System.out.println("Incorrect guess\n");
				}
				totalGuesses += 1;
				printEveryXthChar = (lengthOfWord - soManyCorrectGuesses) + 1;
				displayPicture(printEveryXthChar);

			}
			
			if ( soManyCorrectGuesses == lengthOfWord) {
				System.out.println("You have guessed the correct word. It was: " + randomWord);
			}
			else {
				System.out.println("You ran out of guesses for this word. It was: " + randomWord);
			}
			System.out.println();
			
			// Reset the vector for the new word
			charactersGuessed = new Vector<Character>();
		}
		
		System.out.println("No more words left to guess.");
		System.out.println("I hope you enjoyed the game, bye !");
		
	}
	
}
















