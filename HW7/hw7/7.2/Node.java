package hw7;

public class Node<T> {
	
	// Holds the count of duplicate nodes
	int counter = 1;
	
	// Holds the value of the node
	T payload;
	
	// Points to the left child of the node
	Node<T> left;
	
	// Points to the right child of the node
	Node<T> right;
	
	public Node(T payload) {
		this.payload = payload;
	}
	
	// Checks if the node has a left child
	public boolean hasLeftChild() {
		return this.left != null;
	}
	
	// Checks if the node has a right child
	public boolean hasRightChild() {
		return this.right != null;
	}
	
	// Checks if the node is a leaf node
	public boolean isLeaf() {
		return (this.left == null) && (this.right == null);
	}
	
	// Checks if the node has any child
	public boolean hasAnyChildren() {
		return (this.left != null) || (this.right != null);
	}
	
	// Checks if the node has both the children
	public boolean hasBothChildren() {
		return (this.left != null) && (this.right != null);
	}
	
	// Returns the string representation of a node
	public String toString() {
		String str = "";
		str += "(l: " + this.left + " " + this.payload + "/" +
				this.counter + " r: " + this.right + ")"; 
		return str;
	}
	
}

















