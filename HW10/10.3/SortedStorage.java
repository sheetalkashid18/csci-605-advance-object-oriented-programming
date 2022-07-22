package hw10;

//SortedStorage class has the class type of T where T should extend the Comparable interface
//and also the StorageClass implements the interface StorageInterface
public class SortedStorage<T extends Comparable<T>> implements StorageInterface<T>{
	
	// Holds the root node of the sorted storage i.e. binary search tree
	Node<T> root;
	
	// Keeps a count of the number of null values in the storage
	int soManyNullValues = 0;
	
	@Override
	/***
	 * Adds the value x to the storage class and returns true if successful
	 * Wraps the value in a node and then calls the add() method to add the node
	 * to the bst
	 */
	public synchronized boolean add(T x) {
		boolean rValue = false;
		Node<T> newNode = new Node<T>(x);
		rValue = this.add(newNode);
		return rValue;
	}
	
	@Override
	/***
	 * Prints out the elements of the bst in an ascending order 
	 */
	public void printOut() {
		if (this.root != null) {
			printResult(this.root);
		}
	}
	
	/***
	 * Helper function for the printOut() method
	 * @param node - The root node of the concerned bst
	 */
	public void printResult( Node<T> node ) {
		if(node.left != null) {
			printResult(node.left);
		}
		System.out.println(node.payload);
		if(node.right != null) {
			printResult(node.right);
		}
	}

	@Override
	/***
	 * Finds a value x in the storage class
	 * Returns true if found, false otherwise
	 */
	public boolean find(T x) {
		boolean rValue = false;
		Node<T> newNode = new Node<T>(x);
		rValue = this.find(newNode);
		return rValue;
	}
	
	@Override
	/***
	 * returns true if there is any null value stored in the class
	 */
	public boolean includesNull() {
		boolean rValue = false;
		if (this.soManyNullValues > 0) {
			rValue = true;
		}
		return rValue;
	}

	@Override
	/***
	 * Returns true if the value x is successfully deleted 
	 * from the storage class, false otherwise
	 */
	public synchronized boolean delete(T x) {
		boolean rValue = false;
		Node<T> newNode = new Node<T>(x);
		rValue = this.delete(newNode);
		return rValue;
	}
	
	/***
	 * Adds the newNode to the storage class implementation of 
	 	a binary search tree 
	 * @param newNode - The node to be added
	 * @return True if the node is added successfully, false otherwise
	 */
	public boolean add( Node<T> newNode ) {
		boolean rValue = false;
		
		// If the value passed is a null value decrement 
		// soManyNullValues(iff value > 1) by 1 and return true
		if ( newNode.payload == null) {
			soManyNullValues += 1;
			rValue = true;
		}
		
		// If the root of the bst is null, then make the 
		// newNode the root and return true
		else if (root == null) {
			root = newNode;
			rValue = true;
		}
		
		// call the helper method to search the apt location to add the newNode
		else {
			rValue = _add(root, newNode);
		}
		return rValue;
	}
	
	
	/***
	 * Helper method for the add(Node<>) method
	 * @param currentNode - Holds the root of the subtree in which the 
	 * 						newNode is to be added
	 * @param newNode - The node that is to be added
	 * @return - returns true if the newNode is added successfully
	 */
	public boolean _add(Node<T> currentNode, Node<T> newNode) {
		boolean rValue = false;
		int compValue = currentNode.payload.compareTo(newNode.payload);
		
		// If the payload of the currentNode is greater than the newNode payload
		if( compValue > 0 ) {
			if(!currentNode.hasLeftChild()) {
				currentNode.left = newNode;
				rValue = true;
			}
			else {
				rValue = this._add(currentNode.left, newNode);
			}
			
		}
		
		// If the payload of the currentNode is less than the newNode payload
		else if (compValue < 0){
			if(!currentNode.hasRightChild()) {
				currentNode.right = newNode;
				rValue = true;
			}
			else {
				rValue = this._add(currentNode.right, newNode);
			}
		}
		
		// If the payload of the currentNode is equal to the newNode payload
		// It means the node is a duplicate and therefore just increment the 
		// counter
		else {
			currentNode.counter += 1;
			rValue = true;
		}
		return rValue;
	}
	
	/***
	 * Finds if a node exists in the bst
	 * @param node - The node that is to be found
	 * @return True, if the node is found and false otherwise
	 */
	public boolean find( Node<T> node ) {
		boolean rValue = false;
		
		// If the value to be looked for is a null value
		if ( node.payload == null ) {
			if ( soManyNullValues > 0 ) {
				rValue = true;
			}
		}
		
		// If the value to be looked for is equal to the root value
		else if(this.root.payload.compareTo(node.payload) == 0 ) {
			rValue = true;
		}
		
		// If the value to be looked for is less than the root value
		else if( this.root.payload.compareTo(node.payload) > 0 ) {
			rValue = _find(this.root.left, node);
		}
		
		// If the value to be looked for is greater than the root value
		else {
			rValue = _find(this.root.right, node);
		}
		return rValue;
	}
	
	/***
	 * Helper method for the find method
	 * @param currentNode - The root node of the subtree in which the newNode is to be searched for
	 * @param nodeToFind - The node to be searched for
	 * @return True if the node is found, false otherwise
	 */
	public boolean _find(Node<T> currentNode, Node<T> nodeToFind) {
		
		boolean rValue = false;
		if (currentNode != null) {
			int compValue = currentNode.payload.compareTo(nodeToFind.payload);
			
			if ( compValue == 0 ) {
				rValue = true;
			}
			else if ( compValue > 0 ) {
				rValue = _find(currentNode.left, nodeToFind);
			}
			else {
				rValue = _find(currentNode.right, nodeToFind);
			}
		}
		return rValue;
		
	}
	
	/***
	 * Deletes a node
	 * @param node - the node that is to be deleted
	 * @return True if the node is deleted, false otherwise
	 */
	public boolean delete(Node<T> node) {
		boolean rValue = false;
		if (node.payload == null) {
			if( soManyNullValues > 0 ) {
				soManyNullValues--;
				rValue = true;
			}
		}
		else if (_find(this.root, node)){
			rValue = _delete(this.root, node);
		}
		return rValue;
	}
	
	/***
	 * Helper method of the delete() method
	 * @param currentNode - The root of the subtree in which the nodeToDelete is to
	 * be looked for
	 * @param nodeToDelete - The node that is to be deleted
	 * @return true if the node is deleted, false otherwise
	 */
	public boolean _delete(Node<T> currentNode, Node<T> nodeToDelete) {
		boolean rValue = false;
		
		// if the node to be deleted does not exist in the tree
		if (currentNode == null) {
			rValue = false;
		}
		
		// If the nodeTodelete is the root node
		else if (this.root.equals(currentNode) && 
				currentNode.payload.compareTo(nodeToDelete.payload) == 0) {
			
			deleteAccordingly(null, currentNode);
			rValue = true;
		}
		
		// If the nodeTodelete is the left child of the current root node
		else if(currentNode.hasLeftChild() && 
				currentNode.left.payload.compareTo(nodeToDelete.payload) == 0) {
			deleteAccordingly(currentNode, currentNode.left);
			rValue = true;
			
		}
		
		// If the nodeTodelete is the right child of the current root node
		else if(currentNode.hasRightChild() && 
				currentNode.right.payload.compareTo(nodeToDelete.payload) == 0){
			deleteAccordingly(currentNode, currentNode.right);
			rValue = true;
		}
		
		// If the root node payload is greater than nodeTodelete then look for
		// the nodeTodelete in the left subtree
		else if (currentNode.payload.compareTo(nodeToDelete.payload) > 0) {
			rValue = _delete(currentNode.left, nodeToDelete);
		}
		
		// If the root node payload is less than nodeTodelete then look for
		// the nodeTodelete in the right subtree
		else if (currentNode.payload.compareTo(nodeToDelete.payload) < 0) {
			rValue = _delete(currentNode.right, nodeToDelete);
		}
		
		return rValue;
	}
	
	/***
	 * Helper function for the delete() method
	 * @param parentNode - The parent of the node to be deleted
	 * @param nodeToDelete - The node to be deleted
	 */
	public void deleteAccordingly(Node<T> parentNode, Node<T> nodeToDelete) {
		
		if (nodeToDelete.counter > 1) {
			nodeToDelete.counter -= 1;
		}
		
		// The node to be deleted is a leaf node
		else if(nodeToDelete.isLeaf()) {
			
			if (parentNode == null) {
				this.root = null;
			}
			else if(parentNode.left.equals(nodeToDelete)) {
				parentNode.left = null;
			}
			else {
				parentNode.right = null;
			}
		}
		
		// If the node to be deleted has both the children
		else if (nodeToDelete.hasBothChildren()){
			Node<T> successor = removeSuccessor(nodeToDelete, nodeToDelete.right);
			nodeToDelete.payload = successor.payload;
			nodeToDelete.counter = successor.counter;
		}
		
		// If the node to be deleted has only one child
		else if (nodeToDelete.hasLeftChild()){
			if (parentNode == null) {
				this.root = nodeToDelete.left;
			}
			else if (parentNode.left.equals(nodeToDelete)) {
				parentNode.left = nodeToDelete.left;
			}
			else {
				parentNode.right = nodeToDelete.left;
			}
		}
		else {
			if (parentNode == null) {
				this.root = nodeToDelete.right;
			}
			else if (parentNode.left.equals(nodeToDelete)) {
				parentNode.left = nodeToDelete.right;
			}
			else {
				parentNode.right = nodeToDelete.right;
			}
		}
	}
	
	/***
	 * Removes the successor node of the node(that has both the children) that is to be deleted
	 * @param parentNode - the node that is to be deleted
	 * @param currentNode - the root of the right subtree of the node to be deleted
	 * @return The successor node for the deleted node
	 */
	public Node<T> removeSuccessor ( Node<T> parentNode, Node<T> currentNode ){
		while (currentNode.hasLeftChild()) {
			parentNode = currentNode;
			currentNode = currentNode.left;
		}
		if (parentNode.left.equals(currentNode)) {
			parentNode.left = null;
		}
		else {
			parentNode.right = null;
		}
		return currentNode;
	}
	
	/***
	 * Returns the string representation of the Storage class
	 */
	public String toString() {
		return "\nIncludes so many null values: " + soManyNullValues + "\n" + this.root;
	}
	
}



















