package hw10;

class Result implements Comparable<Result>{
	
	Integer firstNumberofSequence;
	String output;
	
	public Result(int firstNumberofSequence, String output) {
		this.firstNumberofSequence = firstNumberofSequence;
		this.output = output;
	}

	@Override
	public int compareTo(Result other) {
		int compareValue = this.firstNumberofSequence.compareTo(other.firstNumberofSequence);
		return compareValue;
	}
	
}


public class Palindrome extends Thread {
	
	// Stores the starting and ending numbers for which the property will be checked
	// Also stores between what delay to check palindrome
	static int START = 80;
    static int END  = 90;
    static int MAXIMUM_DELAYED = 10;
    static int MINIMUM_DELAYED = 4;
    
    // The size of the array of numbers that needs to be checked for the property by this thread
    int size;
    
    // A list to store the results
//    static List<Result> theResults = Collections.synchronizedList(new ArrayList<Result>());
    
    static StorageInterface<String> aStorageInterfaceString = new SortedStorage<String>();
    
    // the array of numbers to be tested by this thread
    int[] numbersToTest;
    
    public Palindrome(int[] numbersToTest, int size) {
    	this.numbersToTest = numbersToTest;
    	this.size = size;
	}
    
    public void run()
    {
    	// Check every number of the array against the property
    	for(int index = 0; index < size; index++) {
    		checkProperty(this.numbersToTest[index]);
    	}
    }
    
    // This function returns string after reversing the digits.
    public static String rev(int k) {
    	
    	//Converts the integer to string
    	String str = Integer.toString(k); 
    	
    	//creates a new string builder object containing the string
    	StringBuilder st = new StringBuilder(str); 
    	
    	//returns the reverse of the string
    	return ((st.reverse()).toString()); 
    	
    }
    
    //Program checks if the number becomes a palindrome in between MINIMUM_DELAYED and MAXIMUM_DELAYED
    public static void checkProperty(int numberToTest) {
    	
    	int noOfDelays = 0;
    	int flag = 0;
    	int number = numberToTest;
    	int newNumber = 0;
    	
    	// Just move ahead while the delay is less than the minimum delay
    	while(noOfDelays < MINIMUM_DELAYED - 1) {
			
			// adds the number with it's reverse of the number to get a new number
    		newNumber = Integer.parseInt(rev(number)) + number;
    		
			//assigns the new value to j to proceed ahead
    		number = newNumber; 
    		noOfDelays++; 
		}
    	
    	while(noOfDelays < MAXIMUM_DELAYED) {
    		
    		// adds the number with it's reverse of the number to get a new number
    		newNumber = Integer.parseInt(rev(number)) + number;
    		
    		// if it is a palindrome print and exit the loop
    		if(Integer.toString(newNumber).equals(rev(newNumber))){	
    			
    			String str = numberToTest + ":\t" + "delayed " + (noOfDelays+1) + ": " + 
    	    			"     " +  number + " + " + rev(number) + " = " + newNumber;
    			aStorageInterfaceString.add(str);
    			
    			flag = 1;
    			break;
    		}
    		
    		// if the delay is the last one do not move ahead, just end the loop 
    		else if(noOfDelays == MAXIMUM_DELAYED-1) { 
    			
    			if (flag != 1){
    	    		break;
    	    	}
    		}
    		
    		//assigns the new value to j to proceed ahead
    		number = newNumber; 
    		noOfDelays++; 
    	}	
    	
    }
		
	//Main function
	public static void main(String args[]) {
		
		// The total threads specified through command line
		int noOfThreads = Integer.parseInt(args[0]);
		
		// Total number of objects that need to be checked against the property
		int totalObjects = (END - START + 1);
		
		// Number of objects to be checked per thread
		double numbersPerThread = Math.floor(totalObjects / (double)noOfThreads);
		
		// The size of the array that contains the numbers to be tested by a thread
		int sizeOfArr = (int)numbersPerThread;
		
		// The size of the array that contains the numbers to be tested by the last thread
		int sizeOfArrForLastThread = totalObjects - (sizeOfArr * (noOfThreads - 1));
		
		int current = START;
		
		for(int thread_count = 0; thread_count < noOfThreads; thread_count++) {
			int[] arr = new int[sizeOfArr];
			if (thread_count == noOfThreads - 1) {
				arr = new int[sizeOfArrForLastThread];
			}
			int size = 0;
			
			// Divide the numbers to be tested among threads
			for(int index = 0; index < arr.length; index++) {
				if(current <= END) {
					arr[index] = current;
					current += 1;
					size += 1;
				}
				else {
					size = index;
					break;
				}
			}
			if(size != 0) {
				// create a new thread for the array of numbers
				(new Palindrome(arr, size)).start();
			}
		}
		
		// Wait while all the threads finish their execution
		int count = 0;
		while ( count != 1 ) {
			try {
				count = activeCount();
				sleep(500);
			}
			catch (  InterruptedException e ) {
				e.getMessage();
			}
		}
		
		// Prints the nodes in the bst
		aStorageInterfaceString.printOut();
		
	}
}



