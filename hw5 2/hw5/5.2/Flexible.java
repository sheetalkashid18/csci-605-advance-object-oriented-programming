package hw_5;


// Class Flexible that can dynamically increase the
// size of the array to store more objects
class Flexible extends Array {
	
	// Sets the isFlexible to true
	Flexible() {
		setFlexibility(true);
	}
	
	// Adds an object to the Flexible object
	boolean add(Object obj) {
		
		// If the array has reached its max limit, double its size
		if (size() == getMax()) {
			setSizeOfArray(size()*2);
		}
		
		// Call the superclass add() method
		super.add(obj);
		
		return true;
	}
	
	public String toString() {
		String toDisplay = "Name: Flexible\n";
		toDisplay += "Creation time: " + getCreationTime() + "\n";
		toDisplay += "Unlimited: " + getIsFlexible() + "\n";
		toDisplay += "soMany: " + size() + "\n";
		toDisplay += "nullObjectAdded: " + getIsNullObjectAdded() + "\n";
		toDisplay += "Modification time: " + getModificationTime() + "\n";
		toDisplay += getElements();
		toDisplay += "\n";
		return toDisplay;
	}
	
}
