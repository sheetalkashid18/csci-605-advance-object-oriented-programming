package hw_5;


// Class Flexible that can dynamically increases the
// size of the array to store more objects
class Flexible extends Array {
	
	// Sets the isFlexible to true
	Flexible() {
		isFlexible = true;
	}
	
	// Adds an object to the Flexible object
	boolean add(Object obj) {
		
		// If the array has reached its max limit, double its size
		if (soMany == max) {
			Object[] tmp = new Object[max * 2];
			for(int index = 0; index < soMany; index++) {
				tmp[index] = arr[index];
			}
			arr = tmp;
			max *= 2;
		}
		
		// Call the superclass add() method
		super.add(obj);
		
		return true;
	}
	
}
