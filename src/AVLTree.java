import java.util.ArrayList;

public class AVLTree<T extends Comparable<T>> implements AVLTreeInterface<T> {

	public Node<T> root;

	/**
	 * Basic storage units in a tree. Each Node object has a left and right
	 * children fields.
	 * 
	 * If a node does not have a left and/or right child, its right and/or left
	 * child is null.
	 * 
	 */
	private class Node<E> {
		private E data;
		private Node<E> left, right; // left and right subtrees

		public Node(E data) {
			this.data = data;
		}
	}

	// CHANGES START BELOW THIS LINE


	// CHANGES END ABOVE THIS LINE	
}
