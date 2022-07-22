//CSCI.605.04 - Advance OO Programming Concepts
//Homework 3.1
//StringIntegerArrays
//Submitted by Anisha pareek and Sheetal Kashid

import java.util.Arrays;

class StringIntegerArrays {

 public static void main( String args[] ) {
	 String aString;
     String bString;
     String cString;
     String dString;
     String eString;
     
     if ( args.length == 0 ) {
         aString = "2513";
         bString = "2513";
         cString = "ABCDECFG";
         dString = "abcDECFG";
         eString = aString + ( bString + cString ) + dString;
         
     } 
     else {
         aString = "213";
         bString = "2513";
         cString = "ABCDECFGT";
         dString = "abcDECFG";
         eString = bString + ( bString + cString ) + dString;

     }
     
     // To determine if aString and bString are identical
     System.out.println("I " + aString.equals(bString));
     
     //To determine if cString and dString are identical ignoring case differences
     System.out.println("II " + cString.equalsIgnoreCase(dString));
     
     //Cutting out the the first character of aString and printing it.
     System.out.println("III " + aString.substring(0,1));
     
     //Cut out the the last two characters of aString and printing it.
     System.out.println("IV " + aString.substring(aString.length()-2, aString.length()));
     
     //Cut out the string of cString from the beginning of the string to the first occurrence of ’C’ including ’C’ and printing it.
     System.out.println("V " + cString.substring(0, cString.indexOf('C')+1));
     
     //Cut out of dString from the first digit in aString and the last digit in aString and print it
     System.out.println("VI " + dString.substring(Integer.parseInt(aString.substring(0, 1)), Integer.parseInt(aString.substring(aString.length()-1))));
     
     //Sort the characters in aString and cut out from dString beginning with the lowest number to the second lowest number.
     //Convert aString to a character array
     char[] chars = aString.toCharArray();
     
     //Sort the array
     Arrays.sort(chars);
     
     //print
     System.out.println("VII " + dString.substring(Integer.parseInt(String.valueOf(chars[0])), Integer.parseInt(String.valueOf(chars[1]))));
     
     //Print eString in such a ways that the output is sorted.
     //Convert eString to a character array
     char[] echars = eString.toCharArray();
     
     //Sort the array
     Arrays.sort(echars);
     
     //print
     System.out.print("VIII ");
     System.out.println(echars);
     
     //To determine if aString is part of eString.
     System.out.println("IX " + eString.contains(aString));
     
     //To determine if dString is part of cString ignoring case differences.
     System.out.println("X " + cString.toLowerCase().contains(dString.toLowerCase()));

}

}

