import student.TestCase;

/**
 * Test cases for the manager class
 * 
 * @author ramyanandigam, catherinesquillante
 */
public class ManagerTest extends TestCase {

	private Manager manager;

	public void setUp() {
		manager = new Manager();
	}

	public void testInsert() {
		manager.insert("rec", 1, 2, 2, -1);
		assertEquals(0, manager.getSize());
		manager.insert("rec", 1, 2, -2, 1);
		assertEquals(0, manager.getSize());
		manager.insert("rec", -1, 2, 2, 1);
		assertEquals(0, manager.getSize());
		manager.insert("rec", 1, -2, 2, 1);
		assertEquals(0, manager.getSize());
		manager.insert("rec", 1023, 2, 2, 1);
		assertEquals(0, manager.getSize());
		manager.insert("rec", 1, 2000, 2, 1);
		assertEquals(0, manager.getSize());

		manager.insert("rec", 1, 2, 1, 1);
		assertEquals(1, manager.getSize());
		manager.insert("_rec", 1, 2, 1, 1);
		assertEquals(1, manager.getSize());
		manager.insert("re&c", 1, 2, 1, 1);
		assertEquals(1, manager.getSize());
		manager.insert("rec2", 1, 2, 1, 1);
		assertEquals(2, manager.getSize());
		manager.insert("rec2", 1, 2, 1, 1);
		assertEquals(3, manager.getSize());
		manager.insert("zec2", 1, 2, 1, 1);
		assertEquals(4, manager.getSize());
		manager.insert("rec", 0, 0, 1, 1);
		assertEquals(5, manager.getSize());
		manager.insert("rec", 1, 1, 0, 0);
		assertEquals(5, manager.getSize());
		manager.insert("rec", 1, 1, 3000, 2);
		assertEquals(5, manager.getSize());

		systemOut().clearHistory();

	}

	public void testRemove() {
		manager.remove("Blah");
		assertEquals("Rectangle rejected Blah\n", systemOut().getHistory());
		manager.insert("rec", 1, 2, 1, 1);
		manager.remove("rec");
		assertEquals(0, manager.getSize());

		manager.insert("rec", 1, 2, 1, 1);
		assertEquals(1, manager.getSize());
		manager.insert("rec2", 1, 2, 1, 1);
		assertEquals(2, manager.getSize());
		manager.remove("rec");
		assertEquals(1, manager.getSize());

		manager.insert("rec", 1, 2, 1, 1);
		manager.remove(1, 2, 1, 1);
		assertEquals(1, manager.getSize());

		systemOut().clearHistory();

	}

	public void testDump() {
		manager.dump();
		String expect = "BST dump: \n Node has depth 0, Value (null) " + "\n BST size is: 0";
		assertFuzzyEquals(expect, systemOut().getHistory());
		systemOut().clearHistory();

		manager.insert("rec", 1, 2, 1, 1);
		manager.insert("rec2", 1, 2, 1, 1);
		manager.insert("rec2", 1, 2, 1, 1);
		manager.insert("zec2", 1, 2, 1, 1);

		systemOut().clearHistory();

		manager.dump();
		String expected = "BST dump: \n Node has depth 0, Value (rec, 1, 2, 1, 1) \n"
				+ "Node has depth 2, Value (rec2, 1, 2, 1, 1) \n Node has depth 1, Value"
				+ " (rec2, 1, 2, 1, 1) \n Node has depth 2, Value (zec2, 1, 2, 1, 1) \n" + "BST size is: 4";

		assertFuzzyEquals(expected, systemOut().getHistory());
		systemOut().clearHistory();

	}

	public void testSearch() {
		manager.search("rec");
		String expected = "Rectangle not found: rec";
		assertFuzzyEquals(expected, systemOut().getHistory());
		systemOut().clearHistory();

		manager.insert("rec", 1, 2, 1, 1);
		manager.insert("rec", 500, 2, 30, 42);
		systemOut().clearHistory();
		manager.search("rec");
		String expected2 = "Rectangle found: (rec, 500, 2, 30, 42)\nRectangle found: (rec, 1, 2, 1, 1)";
		assertFuzzyEquals(expected2, systemOut().getHistory());
		systemOut().clearHistory();

	}

	public void testIntersections() {
		manager.insert("rec0", 1, 2, 1, 2);
		manager.insert("rec_0", 100, 200, 1190, 1024);
		systemOut().clearHistory();
		
		manager.intersections();
		String expected = "Intersection Pairs:";
		assertFuzzyEquals(expected, systemOut().getHistory());
		
		manager.remove("rec0");
		manager.remove("rec_0");
		systemOut().clearHistory();
		
		
		manager.insert("rec", 10, 10, 5, 5);
		manager.insert("rec2", 11, 11, 1, 1);
		manager.insert("rec3", 11, 10, 1, 1);
		manager.insert("rec4", 10, 11, 5, 5);
		systemOut().clearHistory();
		
		manager.intersections();
		String expected2 = "Intersection Pairs:\n(rec, 10, 10, 5, 5) : "
				+ "(rec2, 11, 11, 1, 1)\n(rec, 10, 10, 5, 5) : "
				+ "(rec3, 11, 10, 1, 1)\n(rec, 10, 10, 5, 5) : "
				+ "(rec4, 10, 11, 5, 5)\n(rec2, 11, 11, 1, 1) : "
				+ "(rec4, 10, 11, 5, 5)\n";
		
		assertEquals(expected2, systemOut().getHistory());
		systemOut().clearHistory();

	}

	public void testRegionSearch() {
		manager.regionsearch(5, 5, 5, 5);
		String expected = "";
		assertFuzzyEquals(expected, systemOut().getHistory());
		systemOut().clearHistory();
		
		manager.insert("rec", 10, 10, 5, 5);
		manager.insert("rec2", 11, 11, 1, 1);
		systemOut().clearHistory();
		manager.regionsearch(5, 5, -5, 5);
		String expected2 = "";
		assertFuzzyEquals(expected2, systemOut().getHistory());
		systemOut().clearHistory();
		
		manager.remove("rec");
		manager.remove("rec2");
		systemOut().clearHistory();
		manager.insert("rec", 10, 10, 5, 5);
		manager.insert("rec2", 11, 11, 1, 1);
		systemOut().clearHistory();
		manager.regionsearch(5, 5, 5, -5);
		String expected3 = "";
		assertFuzzyEquals(expected3, systemOut().getHistory());
		systemOut().clearHistory();
		
		manager.remove("rec");
		manager.remove("rec2");
		systemOut().clearHistory();
		manager.insert("rec", 10, 10, 5, 5);
		manager.insert("rec2", 11, 11, 1, 1);
		systemOut().clearHistory();
		manager.regionsearch(5, 5, -5, -5);
		String expected4 = "";
		assertFuzzyEquals(expected4, systemOut().getHistory());
		systemOut().clearHistory();
		
		manager.remove("rec");
		manager.remove("rec2");
		systemOut().clearHistory();
		manager.insert("rec", 9, 9, 5, 5);
		manager.insert("rec2", 8, 8, 1, 1);
		systemOut().clearHistory();
		manager.regionsearch(5, 5, 5, 5);
		String expected5 = "Rectangles intersecting region (5, 5, 5, 5):\n"
				+ "(rec, 9, 9, 5, 5)\n(rec2, 8, 8, 1, 1)";
		assertFuzzyEquals(expected5, systemOut().getHistory());
		systemOut().clearHistory();
		
		manager.insert("rec3", 11, 10, 1, 1);
		manager.insert("rec4", 6, 6, 1, 1);
		systemOut().clearHistory();
		manager.regionsearch(5, 5, 5, 5);
		String expected6 = "Rectangles intersecting region (5, 5, 5, 5):\n"
				+ "(rec, 9, 9, 5, 5)\n(rec2, 8, 8, 1, 1)\n(rec4, 6, 6, 1, 1)\n";
		assertFuzzyEquals(expected6, systemOut().getHistory());
		systemOut().clearHistory();

	}


}