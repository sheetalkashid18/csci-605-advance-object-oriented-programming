import java.lang.*;

public class Palindrome {
	
	//Stores the starting and ending numbers for which the property will be checked
	// Also stores how much to delay to check palindrome
	
	static int START = 69;
    static int MAXIMUM  = 91;
    static int MAXIMUM_DELAYED = 3;
    
    // This function returns string after reversing the digits.
    
    public static String rev(int k) {
    	
    	//Converts the integer to string
    	String str = Integer.toString(k); 
    	
    	//creates a new string builder object containing the string
    	StringBuilder st = new StringBuilder(str); 
    	
    	//returns the reverse of the string
    	return ((st.reverse()).toString()); 
    	
    }
    
    //Program checks if the number becomes a palindrome in 3 delays
    public static void checkProperty(int i) {
    	
    	int n = 0;
    	int flag = 0;
    	int j = i;
    	int newi = 0;
    	while(n < MAXIMUM_DELAYED) {
    		
    		// adds the number with it's reverse of the number to get a new number
    		newi = Integer.parseInt(rev(j)) + j; 
    		
    		// if it is a palindrome print and exit the loop
    		if(Integer.toString(newi).equals(rev(newi))){	
    			System.out.print(i + "\t" + "delayed " + (n+1) + ": " );
    			System.out.println( "     " +  j + " + " + rev(j) + " = " + newi);
    			flag = 1;
    			break;
    		}
    		
    		// if the delay is the last one donot move ahead, print and end the loop 
    		else if(n == MAXIMUM_DELAYED-1) { 
    			if (flag != 1){
    	    		System.out.print(i + "\t" + "delayed " + (n+1) + ": " + "    does not become a palindrome within ");
    	    		System.out.print((n+1) + " iterations ( " + j + " + " + rev(j) + " = " );
    	    		System.out.println(newi + ": " + newi + " != " + rev(newi) + " )");
    	    		break;
    	    	}
    		}
    		
    		//assigns the new value to j to proceed ahead
    		j = newi; 
    		n++; 
    	}	
    	
    }
    
    
    // This method calls all the numbers from start to the maximum and checks the palindrome property
	public static void palindrome() {
		
		// Check property for all numbers
		for (int index = START; index <= MAXIMUM; index++) {
			checkProperty(index);
		}
	}
		
	//Main function
	public static void main(String args[]) {
		
		palindrome();
		
	}

}
