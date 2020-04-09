import java.util.*;

/**
 * Generic binary search tree that uses in order traversal
 * 
 * @author ramyanandigam, catherinesquillante
 */
public class BST<T extends Comparable<T>> implements Iterable<T> {
	private Node<T> root;
	private Comparator<T> comparator;

	/**
	 * Creates an empty BST with empty root and comparator
	 */
	public BST() {
		root = null;
		comparator = null;
	}

	/**
	 * Compares the contents of two BST nodes
	 * 
	 * @return 0 if nodes are same, else a positive or negative integer
	 */
	private int compare(T x, T y) {
		if (comparator == null)
			return x.compareTo(y);
		else
			return comparator.compare(x, y);
	}

	/**
	 * Insert method called by BST starting at root
	 */
	public void insert(T data) {
		root = insert(root, data);
	}

	/**
	 * Insert helper. Parses right and left subtrees and inserts node at correct
	 * position in tree by comparing the data in the node to be inserted to the
	 * previously inserted nodes' data. If the BST is empty, inserts new data into
	 * the root node.
	 * 
	 * @return node with toInsert data that was successfully inserted
	 */
	private Node<T> insert(Node<T> p, T toInsert) {
		if (p == null)
			return new Node<T>(toInsert);

		if (compare(toInsert, p.data) <= 0)
			p.left = insert(p.left, toInsert);
		else
			p.right = insert(p.right, toInsert);

		return p;
	}

	/**
	 * Delete method called by the BST starting at root.
	 */
	public void delete(T toDelete) {
		root = delete(root, toDelete);
	}

	/**
	 * Locate and delete node containing the data in the "toDelete" parameter. Uses
	 * in order traversal to parse the tree and compares each node it passes using
	 * the comparator. The first node that contains the "toDelete" data is removed
	 * from the tree.
	 * 
	 * @return deleted node
	 */
	private Node<T> delete(Node<T> p, T toDelete) {
		if (p == null)
			throw new RuntimeException("cannot delete.");
		else if (compare(toDelete, p.data) < 0)
			p.left = delete(p.left, toDelete);
		else if (compare(toDelete, p.data) > 0)
			p.right = delete(p.right, toDelete);
		else {
			if (p.left == null)
				return p.right;
			else if (p.right == null)
				return p.left;
			else {
				// get data from the leftmost node in the left subtree
				p.data = retrieveData(p.left);
				// delete the leftmost node in the left subtree
				p.left = delete(p.left, p.data);
			}
		}
		return p;
	}

	/**
	 * @return the data of parameter Node p
	 */
	private T retrieveData(Node<T> p) {
		while (p.left != null)
			p = p.left;

		return p.data;
	}

	/**
	 * The inOrder traversal that BST calls starting with the root node
	 */
	public void inOrderTraversal() {
		inOrderHelper(root, 0);
	}

	/**
	 * InOrder traversal method helper. This is called by the dump method in the
	 * manager class. Traverses the tree in order and prints the level of and data
	 * held by each node as it parses through the tree.
	 */
	private void inOrderHelper(Node<T> r, int level) {
		if (root == null) {
			System.out.print("Node has depth 0, Value (null)\n");
		} else if (r != null) {
			// traverse left subtree
			inOrderHelper(r.left, level + 1);
			// print node data and level
			System.out.print("Node has depth " + level + ", Value " + r.toString() + "\n");
			// traverse right subtree
			inOrderHelper(r.right, level + 1);
		}
	}

	/**
	 * Get size called by the BST starting at the root
	 */
	public int getSize() {
		return getSize(root);
	}

	/**
	 * Get size helper
	 * 
	 * @return the size of the tree (number of nodes in the tree)
	 */
	private int getSize(Node<T> root) {
		if (root == null) {
			return 0;
		}
		return 1 + getSize(root.left) + getSize(root.right);
	}

	/**
	 * Method used to instantiate the tree iterator
	 */
	public Iterator<T> iterator() {
		return new MyIterator();
	}

	/**
	 * BST iterator class. Keeps track of the nodes using a stack instance as it
	 * parses through the tree.
	 */
	private class MyIterator implements Iterator<T> {
		private Stack<Node<T>> stk;

		/**
		 * Creates a new iterator with an initially empty stack and then recursively
		 * pushes all the nodes currently in the tree to the stack starting from the
		 * left subtree (in order traversal)
		 */
		public MyIterator() {
			if (root != null) {
				stk = new Stack<Node<T>>();
				goLeftFrom(root);
			}
		}

		/**
		 * Used in the constructor to push all of the nodes to the empty stack starting
		 * from the left subtree.
		 */
		private void goLeftFrom(Node<T> t) {
			while (t != null) {
				stk.push(t);
				t = t.left;
			}
		}

		/**
		 * @return true if there are more nodes to parse, false if the stack is empty.
		 */
		@Override
		public boolean hasNext() {
			if (stk == null) {
				return false;
			}

			if (stk.empty()) {
				return false;
			}
			return true;
		}

		/**
		 * Main method called to iterate through the tree.
		 * 
		 * @return the data contained in the node next up in the stack.
		 */
		@Override
		public T next() {
			Node<T> current = null;
			if (!stk.empty()) {
				current = stk.peek();
				stk.pop();
				if (current.right != null) {
					goLeftFrom(current.right);
				}
				return (current.data);
			}
			return null;
		}
	}
	// end of MyIterator

	/**
	 * Node Class used by BST to store generic data T
	 */
	private class Node<T extends Comparable<T>> {
		private T data;
		private Node<T> left, right;

		/**
		 * creates new node with generic data d and empty left and right subtrees. Those
		 * are set as needed later by the BST.
		 */
		public Node(T d) {
			data = d;
			left = null;
			right = null;
		}

		/**
		 * @return the data held by the nodes in String form.
		 */
		public String toString() {
			return data.toString();
		}

	} // end of Node

}// end of BST}// end of BST