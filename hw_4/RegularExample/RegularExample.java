//CSCI.605.04 - Advance OO Programming Concepts
//Homework 4.2
//RegularExample
//Submitted by Anisha pareek and Sheetal Kashid

package hw_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class RegularExample {
	
	// Contains all the patterns to test
	static String[] allPatternsToTest = {
			"a word which is one character long", 
				"^.$",
			"starts with 'a' followed by one digit or more digits", 
				"a\\d+",
			"a word with the vowels 'aeiou' in order and each vowel can appear only once",
				"[^aeiou]*a[^aeiou]*e[^aeiou]*i[^aeiou]*o[^aeiou]*u[^aeiou]*",
			"starts with 'a' followed by 3 digits", 
				"a\\d{3}",
			"starts with 'a' followed by 3 or more digits", 
				"a\\d{3,}",
			"starts with 'a' followed by 2 digits in the range between 8 and 9 only", 
				"a[8-9]{2}",
			"includes only lower case characters, but not the character 'h', 'p', and 'b'", 
				"[a-z&&[^hpb]]*",
	};
	
	// Matches every word against all the patterns and prints the ones that match
	public static void matchTheWord(String word) {
		
		int index = 0;
		
		System.out.println("\nInput: " + word);
		
		while(index < allPatternsToTest.length) {
			
			String regex = allPatternsToTest[index+1];
			String verbalExp = allPatternsToTest[index];
			
			if ( Pattern.matches(regex, word)) {
				System.out.println("This regular expression \'" + regex +
						"\' matches the following input: " + word + " = \n\tVerbal explanation: " + verbalExp);
			}
			index += 2;
		}
		System.out.println();

	}
	
	public static void main(String[] args) {
		
		String delimiter = "";
		File inputFile = null;
		
		// Gets the delimiter and the filePath from the command line arguments
		for ( int index = 0; index < args.length; index += 2 ) {
			if (args[index].equals("-d")) {
				delimiter = args[index + 1];
			}
			else if ( args[index].equals("-input")) {
				inputFile = new File(args[index + 1]);
			}
		}		
		
		try {
			
			// Scan the input with the specified delimiter
			Scanner sc = new Scanner(inputFile).useDelimiter(delimiter);
			
			while(sc.hasNext()) {
				
				String word = sc.next();
				
				// Check if the word is not an empty string
				if ( word.length() > 0) {
					matchTheWord(word);
				}
			}
			sc.close();
			
		} catch (FileNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
