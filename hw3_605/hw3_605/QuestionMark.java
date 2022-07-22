//CSCI.605.04 - Advance OO Programming Concepts
//Homework 3.3
//StringIntegerArrays
//Submitted by Anisha pareek and Sheetal Kashid

package hw_605_3;


public class QuestionMark {

	public static boolean aGreaterB(int a, int b) {
		
		return ( a > b );
	}

	public static int findMaximum(int a, int b)	{
		
		// If a > b, return a otherwise return b
		return ( a > b ? a : b );
	}

	public static int findMaximum(int a, int b, int c, int d) {

		int maxAndB = (a > b ? a : b); // If a > b, return a otherwise return b
		int maxCndD = (c > d ? c : d); // If c > d, return c otherwise return d
		
		// If maxAndB > maxCndD, return maxAndB otherwise return maxCndD
		return (maxAndB > maxCndD ? maxAndB : maxCndD); 

	}

	public static int leftToRight(int a, int b)	{
		
		// If a != 0, then check if b != 0. If true, return a/b otherwise return -1
		// If a == 0, return 0
		return ( a != 0 ) ? (b != 0 ? a/b : -1) : 0;

	}

	public static void main( String[] args ) {
		int a = 5;
		int b = 1;
		int c = 2;
		int d = 1;
		System.out.println("aGreaterB(" + a + "," + b + ") = " + aGreaterB(a, b ) );
		System.out.println("findMaximum(" + a + "," + b + ") = " + findMaximum(a, b ) );
		System.out.println("findMaximum(" + a + ", " + b + ", " + c + ", " + d + " ) = " + 
				findMaximum(a, b, c, d ) );
		a = 0;
		b = 0;
		System.out.println("leftToRight(" + a++ + "," + b++ + ") = " + findMaximum(a, b ) );
		System.out.println("leftToRight(" + --a + "," + b + ") = " + findMaximum(a, b ) );
	}
}
