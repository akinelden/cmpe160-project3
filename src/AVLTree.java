import java.util.ArrayList;
import java.util.LinkedList;

public class AVLTree<T extends Comparable<T>> implements AVLTreeInterface<T> {

	public Node<T> root;

	/**
	 * Basic storage units in a tree. Each Node object has a left and right
	 * children fields.
	 * <p>
	 * If a node does not have a left and/or right child, its right and/or left
	 * child is null.
	 */
	private class Node<E> {
		private E data;
		private Node<E> left, right; // left and right subtrees
		private int height = 1;

		public Node(E data) {
			this.data = data;
		}
	}

	// CHANGES START BELOW THIS LINE

	/**
	 * @return true if the tree is empty
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * @param node The parent node whose number of child nodes is to be calculated
	 * @return Number of child nodes under the given node
	 */
	private int childNumberOf(Node<T> node) {
		if (node == null) {
			return 0;
		}
		return childNumberOf(node.left) + childNumberOf(node.right) + 1;
	}

	/**
	 * @return the number of nodes in the tree
	 */
	public int size() {
		return childNumberOf(root);
	}

	/**
	 * Returns true if the object in the parameter
	 * is in the tree
	 *
	 * @param element Element to be searched
	 * @return Whether tree contains the parameter
	 */
	public boolean contains(T element) {
		ArrayList<Node<T>> array = new ArrayList<>();
		searchElementAndParent(array, root, element);
		if(array.size()>0){
			return true;
		}
		return false;
	}

	/**
	 * Searches an element starting from the given node
	 *
	 * @param node    The node under which the search operation is started
	 * @param element The element to be searched
	 * @return The Node containing element or null if element couldn't found
	 */
	private void searchElementAndParent(ArrayList<Node<T>> array, Node<T> node, T element) {
		if (node == null) {
			array.clear();
			return;
		}
		if (node.data.compareTo(element) == 0) {
			array.clear();
			array.add(node);
			return;
		} else if (node.data.compareTo(element) > 0) {
			searchElementAndParent(array, node.left, element);
		} else {
			searchElementAndParent(array, node.right, element);
		}
		if(array.size()==1){
			array.add(node);
		}
	}
	

	/**
	 * Inserts the element in the parameter to the tree
	 * <p>
	 * If tree already contains the parameter,
	 * no update is done on the tree
	 *
	 * @param element Element to be added
	 */
	public void insert(T element) {
		Node<T> node = insert(root, element);
		if(root == null){
			root = node;
		}
	}

	/**
	 * Inserts the element in the parameter to the subtree of node
	 * <p>
	 * If tree already contains the parameter,
	 * no update is done on the tree
	 *
	 * @param node Parent node of the subtree
	 * @param element Element to be added
	 * @return Inserted node
	 */
	public Node<T> insert(Node<T> node, T element) {
		if (node == null) {
			return new Node<T>(element);
		}
		if (node.data.compareTo(element) == 0) {
			return node;
		} else if (node.data.compareTo(element) > 0) {
			node.left = insert(node.left, element);
		} else {
			node.right = insert(node.right, element);
		}

		node.height = Integer.max(height(node.left), height(node.right)) + 1;

		int balance = height(node.left) - height(node.right);
		if (balance > 1) {
			if (node.left.data.compareTo(element) > 0) {
				return rightRotate(node);
			} else {
				return leftRightRotate(node);
			}
		} else if (balance < -1) {
			if (node.right.data.compareTo(element) < 0) {
				return leftRotate(node);
			} else {
				return rightLeftRotate(node);
			}
		}
		return node;
	}

	private Node<T> rightRotate(Node<T> node) {
		Node<T> leftNode = node.left;
		Node<T> leftRightNode = leftNode.right;
		node.left = leftRightNode;
		leftNode.right = node;
		node.height = Integer.max(height(node.left), height(node.right)) + 1;
		leftNode.height = Integer.max(height(leftNode.left), height(leftNode.right)) + 1;
		return leftNode;
	}

	private Node<T> leftRotate(Node<T> node) {
		Node<T> rightNode = node.right;
		Node<T> rightLeftNode = rightNode.left;
		node.right = rightLeftNode;
		rightNode.left = node;
		node.height = Integer.max(height(node.left), height(node.right)) + 1;
		rightNode.height = Integer.max(height(rightNode.left), height(rightNode.right)) + 1;
		return rightNode;
	}

	private Node<T> leftRightRotate(Node<T> node) {
		Node<T> leftNode = node.left;
		Node<T> leftRightNode = node.left.right;
		node.left = leftRightNode;
		leftNode.right = leftRightNode.left;
		leftRightNode.left = leftNode;
		node.height = Integer.max(height(node.left), height(node.right)) + 1;
		leftNode.height = Integer.max(height(leftNode.left), height(leftNode.right)) + 1;
		leftRightNode.height = Integer.max(height(leftRightNode.left), height(leftRightNode.right)) + 1;
		return rightRotate(node);
	}

	private Node<T> rightLeftRotate(Node<T> node) {
		Node<T> rightNode = node.right;
		Node<T> rightLeftNode = node.right.left;
		node.right = rightLeftNode;
		rightNode.left = rightLeftNode.right;
		rightLeftNode.right = rightNode;
		node.height = Integer.max(height(node.left), height(node.right)) + 1;
		rightNode.height = Integer.max(height(rightNode.left), height(rightNode.right)) + 1;
		rightLeftNode.height = Integer.max(height(rightLeftNode.left), height(rightLeftNode.right)) + 1;
		return leftRotate(node);
	}

	/**
	 * Deletes the element in the parameter
	 * <p>
	 * If tree does not contain the element in the parameter,
	 * no update is done on the tree
	 *
	 * @param element Element to be deleted
	 */
	public void delete(T element) {
		delete(element,root);

	}

	private void delete(T element, Node<T> startingNode){
		ArrayList<Node<T>> array = new ArrayList<>();
		searchElementAndParent(array, startingNode, element);
		Node<T> node = null;
		Node<T> parent = null;
		if(array.size()>0){
			node = array.get(0);
			if(array.size()==2){
				parent = array.get(1);
			}
		}
		if(node == null){
			return;
		}
		if(node.right!=null){
			Node<T> biggerMostLeft = findMostLeftNode(node.right);
			T leftData = biggerMostLeft.data;
			delete(leftData, node);
			node.data = leftData;
			calculateHeightOfSubtree(node);
			updateHeightUntilElement(root, node.data);
		}
		else if(node.left!=null){
			node.data = node.left.data;
			node.left = node.left.left;
			node.right = node.left.right;
			calculateHeightOfSubtree(node);
			updateHeightUntilElement(root, node.data);
		}
		else if(parent == null){
			root = null;
		}
		else{
			if(parent.left == node){
				parent.left = null;
			}
			else{
				parent.right = null;
			}
			calculateHeightOfSubtree(parent);
			updateHeightUntilElement(root, parent.data);
		}
	}

	private Node<T> findMostLeftNode(Node<T> node){
		if(node.left == null){
			return node;
		}
		return findMostLeftNode(node.left);
	}

	private int updateHeightUntilElement(Node<T> startingNode, T element){
		if(startingNode == null){
			return 0;
		}
		if(startingNode.data.compareTo(element)==0){
			return startingNode.height;
		}
		if(startingNode.data.compareTo(element)>0){
			return startingNode.height = Integer.max(updateHeightUntilElement(startingNode.left, element), startingNode.right.height);
		}
		else{
			return startingNode.height = Integer.max(updateHeightUntilElement(startingNode.right, element), startingNode.left.height);
		}
	}

	private int calculateHeightOfSubtree(Node<T> node){
		if(node == null){
			return 0;
		}
		node.height =Integer.max(calculateHeightOfSubtree(node.left),calculateHeightOfSubtree(node.right))+1;
		return node.height;
	}

	/**
	 * The height of a node is defined as the number of edges
	 * from the node to the deepest leaf.
	 *
	 * @param node The node whose height is to be calculated
	 * @return The height of the node
	 */
	private int height(Node<T> node) {
		if (node == null) {
			return 0;
		}
		return node.height;
	}

	/**
	 * The height of a node is defined as the number of edges
	 * from the node to the deepest leaf.
	 * The height of a tree is the height of the root.
	 *
	 * @return The height of the tree
	 */
	public int height() {
		return height(root);
	}

	/**
	 * Traverses the tree "in order".
	 * This means, the left subtree of the node is traversed first.
	 * Then, the node itself is visited.
	 * Then, the the right subtree is traversed.
	 * <p>
	 * The data in each node is added to an ArrayList as the
	 * node is processed. In other words, this ArrayList stores the order of visiting
	 * of the nodes in the tree
	 *
	 * @return An ArrayList that stores the "data" field in nodes visited
	 */
	public ArrayList<T> inOrderTraversal() {
		ArrayList<T> list = new ArrayList<>();
		inOrderTraversal(list, root);
		return list;
	}

	private void inOrderTraversal(ArrayList<T> list, Node<T> node) {
		if (node == null) {
			return;
		}
		inOrderTraversal(list, node.left);
		list.add(node.data);
		inOrderTraversal(list, node.right);
	}

	/**
	 * Visits all the nodes in a breadth first manner.
	 * As nodes are visited, "data" fields are added to an ArrayList.
	 *
	 * @return An ArrayList that stores the "data" field in nodes visited
	 */
	public ArrayList<T> bfTraverse() {
		ArrayList<T> list = new ArrayList<>();
		LinkedList<Node<T>> queue = new LinkedList<>() {{
			add(root);
		}};
		bfTraverse(list, queue);
		return list;
	}

	private void bfTraverse(ArrayList<T> list, LinkedList<Node<T>> queue) {
		Node<T> node = queue.pollFirst();
		if (node == null) {
			return;
		}
		list.add(node.data);
		if (node.left != null) {
			queue.add(node.left);
		}
		if (node.right != null) {
			queue.add(node.right);
		}
		bfTraverse(list, queue);
	}

	/**
	 * For two nodes, if their distances to the root is the same and
	 * their parents are not the same, they are said to be "cousins".
	 * <p>
	 * Of course, if one of the parameters is not in the list, they
	 * cannot be cousins.
	 *
	 * @return true if elements in the parameter are cousins, false otherwise
	 */
	public boolean areCousins(T element1, T element2) {
		Node<T> node = root;
		while (node != null) {
			int comparison1 = node.data.compareTo(element1);
			int comparison2 = node.data.compareTo(element2);
			if (comparison1 != comparison2) {
				break;
			}
			if (comparison1 == 0) {
				break;
			}
			if (comparison1 > 0) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		if (node == null) {
			return false;
		}
		int level1 = elementLevel(node, element1);
		int level2 = elementLevel(node, element2);
		if (level1 != level2) {
			return false;
		}
		// They shouldn't be equal to the each other, their parent must be different and they should appear in the tree.
		if (level1 <= 1) {
			return false;
		}
		return true;
	}

	/**
	 * Searches an element starting from the given node
	 *
	 * @param node    The node under which the search operation is started
	 * @param element The element to be searched
	 * @return The Node containing element or null if element couldn't found
	 */
	private int elementLevel(Node<T> node, T element) throws NullPointerException {
		if (node == null) {
			return Integer.MIN_VALUE;
		}
		if (node.data.compareTo(element) == 0) {
			return 0;
		} else if (node.data.compareTo(element) > 0) {
			return elementLevel(node.left, element) + 1;
		} else {
			return elementLevel(node.right, element) + 1;
		}
	}

	/**
	 * Returns the number of elements in the tree that are
	 * greater than <code>lower</code> and less than <code>upper</code>
	 * (bounds are not inclusive, i.e., not less than or equal to)
	 *
	 * @param lower the lower limit
	 * @param upper the upper limit
	 * @return the number of elements within the range
	 */
	public int numElementsInRange(T lower, T upper) {
		return numElementsInRange(root, lower, upper);
	}

	/**
	 * Returns the number of elements int the subtree of a node that are
	 * greater than <code>lower</code> and less than <code>upper</code>
	 * (bounds are not inclusive, i.e., not less than or equal to)
	 *
	 * @param node  The node determining the subtree
	 * @param lower The lower limit
	 * @param upper The upper limit
	 * @return The number of elements within the range in the subtree
	 */
	private int numElementsInRange(Node<T> node, T lower, T upper) {
		if (node == null) {
			return 0;
		}
		if (node.data.compareTo(lower) <= 0 || node.data.compareTo(upper) >= 0) {
			return 0;
		}
		return numElementsInRange(node.left, lower, upper) + numElementsInRange(node.right, lower, upper) + 1;
	}

	/**
	 * Returns the balance factor of the node that stores the data
	 * given as parameter
	 * BalanceFactor: height(leftSubtree) - height(rightSubtree)
	 * <p>
	 * You may assume that there will be one and only one node
	 * whose balance factor is calculated
	 *
	 * @param data
	 * @return balance factor of the node storing data
	 */
	public int balanceFactor(T data) {
		ArrayList<Node<T>> array = new ArrayList<>();
		searchElementAndParent(array, root, data);
		if (array.size() == 0) {
			return 0;
		}
		return height(array.get(0).left) - height(array.get(0).right);
	}

	// CHANGES END ABOVE THIS LINE	
}
