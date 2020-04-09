import java.util.Iterator;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Manager class that implements all of the project specified methods. It uses a
 * BST instance of type Rectangle to store and retrieve Rectangle data. It then
 * performs the necessary operations on the data and outputs correctly formatted
 * results.
 * 
 * @author ramyanandigam, catherinesquillante
 */
public class Manager {
	private BST<Rectangle> bst;

	/**
	 * creates new manager instance with an empty BST of type Rectangle
	 */
	public Manager() {
		this.bst = new BST<Rectangle>();
	}

	/**
	 * Inserts new Rectangle into the BST. Checks if the coordinates are in the
	 * valid range and that the name of the rectangle contains valid characters and
	 * starts with a letter. If the Rectangle does not fit into the specified
	 * parameters it prints out Rectangle rejected along with the data. If it is
	 * valid then it inserts it into the tree at the correct position and prints
	 * Rectangle accepted along with the data.
	 */
	public void insert(String name, int x, int y, int w, int h) {
		// check if in bounds and parameters >0
		if (h <= 0 || w <= 0 || x < 0 || y < 0 || x + w > 1024 || y + h > 1024) {
			System.out.print("Rectangle rejected: (" + name + ", " + x + ", " + y + ", " + w + ", " + h + ")\n");
			return;
		}
		// check if name is valid
		String regex = "^[a-zA-Z0-9||_]*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(name);
		char first = name.charAt(0);
		if (!Character.isLetter(first) || !matcher.matches()) {
			System.out.print("Rectangle rejected: (" + name + ", " + x + ", " + y + ", " + w + ", " + h + ")\n");
			return;
		}

		// insert valid rectangle
		Rectangle ins = new Rectangle(name, x, y, w, h);
		bst.insert(ins);
		System.out.print("Rectangle accepted: " + ins.toString() + "\n");
	}

	/**
	 * Removes first occurrence of a Rectangle in the tree that has coordinates that
	 * match the parameters. If the rectangle is not found it outputs "Rectangle
	 * rejected along with the coordinates. If it is found it is deleted from the
	 * tree and nothing is printed.
	 */
	public void remove(int x, int y, int w, int h) {
		Rectangle rem = new Rectangle("___", x, y, w, h);

		try {
			bst.delete(rem);

		} catch (RuntimeException e) {
			System.out.print("Rectangle rejected (" + x + ", " + y + ", " + w + ", " + h + ")\n");
		}

	}

	/**
	 * Removes the first occurrence of a Rectangle with the given name. If no
	 * rectangle is found it prints "rectangle rejected" along with the name. If it
	 * is found it removes it from the tree and prints nothing.
	 */
	public void remove(String name) {
		Rectangle rem = new Rectangle(name, 1, 1, 1, 1);
		try {
			bst.delete(rem);

		} catch (RuntimeException e) {
			System.out.print("Rectangle rejected " + name + "\n");
		}
	}

	/**
	 * Traverses the tree in its current state and prints the data and level of each
	 * node in order.
	 */
	public void dump() {
		System.out.print("BST dump:\n");
		bst.inOrderTraversal();
		System.out.print("BST size is: " + bst.getSize() + "\n");

	}

	/**
	 * Searches the tree for rectangle(s) with the given name and prints the data of
	 * all of the rectangles whose names that match the given name. Uses an iterator
	 * to traverse the tree since multiple rectangles may need to be found. If none
	 * are found then prints "Rectangle not found: (name)".
	 */
	public void search(String name) {
		ArrayList<Rectangle> results = new ArrayList<Rectangle>();
		Iterator<Rectangle> iter = bst.iterator();
		while (iter.hasNext()) {
			Rectangle curr = iter.next();
			if (curr.getName().contentEquals(name)) {
				results.add(curr);
			}
		}
		if (results.isEmpty()) {
			System.out.print("Rectangle not found: " + name + "\n");
		} else {
			for (Rectangle i : results) {
				System.out.print("Rectangle found: " + i.toString() + "\n");
			}
		}

	}

	/**
	 * Locates all of the pairs of rectangles that have coordinates that intersect
	 * each other and prints their data. Uses an iterator to traverse the tree and
	 * then an inner iterator to check if any nodes match the data of the outer
	 * iterator
	 */
	public void intersections() {
		// list of rectangles already checked for matches to avoid duplicate pairs
		ArrayList<Rectangle> checked = new ArrayList<Rectangle>();
		System.out.print("Intersection Pairs:\n");
		Iterator<Rectangle> iter = bst.iterator();
		// outer iterator
		while (iter.hasNext()) {
			Rectangle one = iter.next();
			Iterator<Rectangle> inner = bst.iterator();
			// inner iterator checks for matches of the node currently held in the outer
			// iterator
			while (inner.hasNext()) {
				boolean skip = false;
				Rectangle two = inner.next();
				for (Rectangle i : checked) {
					if (two == i) {
						// skip printing this rectangle if the pair was already checked
						skip = true;
					}
				}
				if (one.isOverlapping(two) && one != two && !skip) {
					System.out.print(one.toString() + " : " + two.toString() + "\n");
				}
			}
			checked.add(one);

		}

	}

	/**
	 * Outputs the data of all rectangles that intersect the area given in the
	 * parameter coordinates. Uses an iterator so multiple rectangles can be found.
	 * If none are found output nothing.
	 */
	public void regionsearch(int x, int y, int w, int h) {
		ArrayList<Rectangle> found = new ArrayList<Rectangle>();
		Rectangle comp = new Rectangle("___", x, y, w, h);
		Iterator<Rectangle> iter = bst.iterator();
		while (iter.hasNext()) {
			Rectangle curr = iter.next();
			if (comp.isOverlapping(curr) && h > 0 && w > 0) {
				found.add(curr);
			}
		}
		if (!found.isEmpty()) {
			System.out.print("Rectangles intersecting region (" + x + ", " + y + ", " + w + ", " + h + "):\n");
			for (Rectangle i : found) {
				System.out.print(i.toString() + "\n");
			}
		}

	}

	/**
	 * get size(number of nodes) of BST for testing purposes
	 */
	public int getSize() {
		return bst.getSize();
	}

}
