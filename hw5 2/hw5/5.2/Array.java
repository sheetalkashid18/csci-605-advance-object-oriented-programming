package hw_5;

import java.util.Date;
import java.util.Objects;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

class Array {
	
	// Holds the maximum number of elements an Array object can hold
	private static int max = 4;
	
	// An array for storing elements in an Array object
	private Object[] arr;
	
	// Holds the number of elements added so far in an Array object
	private int soMany;
	
	// Contains true if the Array has flexible size, false otherwise
	private boolean isFlexible;
	
	// SimpleDateFormat object to store and print the 
	// creation and modification time of an Array object
	private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss aa");
	
	// Assumed that only a single null object can be added 
	// and contains true if it has already been added
	private boolean isNullObjectAdded;
	
	// Stores the creation time of an Array object
	private String creationTime;
	
	// Stores the modification time of an Array object
	private String modificationTime;
	
	// Set the Time zone and store the creation time 
	Array() {
		arr = new Object[max];
		soMany = 0;
		isFlexible = false;
		isNullObjectAdded = false;
		df.setTimeZone(TimeZone.getTimeZone("IST"));
		creationTime = df.format(new Date());
	}
	
	void setSizeOfArray(int size) {
		Object[] tmp = new Object[size];
		for(int index = 0; index < soMany; index++) {
			tmp[index] = arr[index];
		}
		arr = tmp;
		max = size;
	}
	
	void setFlexibility(boolean val) {
		isFlexible = val;
	}

	// Adds an object to the Array class
	// returns true if the object was added successfully;
	// false otherwise
	boolean add(Object obj) {
		
		// Store the modification time
		modificationTime = df.format(new Date());
		
		boolean rValue = false;
		
		// Add only if the elements added so far are less 
		// than the max number of objects that can be stored 
		if (soMany < max) {
			
			// Check if the object to be added is null. If yes, then add 
			// only when there is no null object already added otherwise 
			// just add the object
			if ( ( Objects.equals(obj, null) && !isNullObjectAdded ) || !Objects.equals(obj, null) ) {
				
				// Add the null object and set isNullObjectAdded as true
				if ( Objects.equals(obj, null)) {
					isNullObjectAdded = true;
				}
				arr[soMany++] = obj;
				rValue = true;
			}
		}
		return rValue;
	}
	
	// Deletes the first occurrence of the object specified from the Array object
	// returns true if the object was deleted successfully;
	// false otherwise
	boolean delete(Object obj) {
		
		// Store the modification time
		modificationTime = df.format(new Date());
		
		boolean rValue = false;
		
		// Delete only if the there is at least one object
		if ( soMany > 0 ) {
			
			// If the object deleted is a null, set isNullObjectAdded as false
			if (Objects.equals(obj, null)) {
				isNullObjectAdded = false;
			}
			
			// A temporary array that will contain all the 
			// objects except the one that is to be deleted
			Object[] tmp = new Object[max];
			
			// Index for the temporary array
			int tmpi = 0;
			
			// A flag to prevent deletion of all objects of the
			// specified value. It is set to true as soon as the 
			// first occurrence of the specified object is deleted
			// and ends the following 'for' loop
			boolean firstOccDeleted = false;
			
			for(int index = 0; index < soMany; index++) {
				if ( Objects.equals(obj, arr[index]) && !firstOccDeleted) {
					rValue = true;
					firstOccDeleted = true;
				}
				else {
					tmp[tmpi++] = arr[index];
				}
			}
			if ( rValue == true ) {
				soMany -= 1;
				tmp[tmpi] = null;
				arr = tmp;
			}
		}
		return rValue;
	}
	
	// Returns the maximum number of objects that can be stored by an Array object
	int getMax() {
		return max;
	}
	
	// Returns the number of objects stored currently in an Array object
	int size() {
		return soMany;
	}
	
	// Returns true if the obj is contained in the Array object
	boolean contains(Object obj) {
		
		boolean rValue = false;
		for(int index = 0; index < soMany; index++) {
			if ( Objects.equals(obj, arr[index])) {
				rValue = true;
			}
		}
		return rValue;
	}
	
	// Returns true if no more elements could be added 
	boolean isFull() {
		return (soMany == max);
	}
	
	// Returns true if the Array object is empty
	boolean isEmpty() {
		return (soMany == 0);
	}
	
	String getModificationTime() {
		return modificationTime;
	}
	
	String getCreationTime() {
		return modificationTime;
	}
	
	boolean getIsFlexible() {
		return isFlexible;
	}
	
	boolean getIsNullObjectAdded() {
		return isNullObjectAdded;
	}
	
	String getElements() {
		String toDisplay = "";
		for(int index = 0; index < soMany; index++) {
			toDisplay += (arr[index] + " ");
		}
		return toDisplay;
	}
	
	// Returns a String containing the textual representation of
	// a Array object
	public String toString() {
		String toDisplay = "Name: Array\n";
		toDisplay += "Creation time: " + creationTime + "\n";
		toDisplay += "Unlimited: " + isFlexible + "\n";
		toDisplay += "soMany: " + soMany + "\n";
		toDisplay += "nullObjectAdded: " + isNullObjectAdded + "\n";
		toDisplay += "Modification time: " + modificationTime + "\n";
		for(int index = 0; index < soMany; index++) {
			toDisplay += (arr[index] + " ");
		}
		toDisplay += "\n";
		return toDisplay;
	}
	
	public static void main(String args[])  {
		Array aArray = new Array();
		aArray.add(null);
		aArray.add("abc");
		System.out.println(aArray);
	}
	
}





















