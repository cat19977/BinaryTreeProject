import student.TestCase;

/**
 * Test class for the rectangle class.
 * 
 * @author catsquillante, ramyanandigam
 *
 */
public class RectangleTest extends TestCase {
	private Rectangle rectangle;

	public void setUp() {
		rectangle = new Rectangle("rec", 1, 2, 5, 100);
	}
	
	public void testGetName() {
		assertEquals("rec", rectangle.getName());
	}
	
	public void testSetName() {
		rectangle.setName("name2");
		assertEquals("name2", rectangle.getName());
	}
	
	public void testGetX() {
		assertEquals(1, rectangle.getX());
	}
	
	public void testSetX() {
		rectangle.setX(4);
		assertEquals(4, rectangle.getX());
	}
	
	public void testGetY() {
		assertEquals(2, rectangle.getY());
	}
	
	public void testSetY() {
		rectangle.setY(10);
		assertEquals(10, rectangle.getY());
	}
	
	public void testGetW() {
		assertEquals(5, rectangle.getW());
	}
	
	public void testSetW() {
		rectangle.setW(3);
		assertEquals(3, rectangle.getW());
	}
	
	public void testGetH() {
		assertEquals(100, rectangle.getH());
	}
	
	public void testSetH() {
		rectangle.setH(20);
		assertEquals(20, rectangle.getH());
	}
	
	public void testToString() {
		String expected = "(rec, 1, 2, 5, 100)";
		assertEquals(expected, rectangle.toString());
	}
	
	public void testIsOverlapping() {
		Rectangle rec2 = new Rectangle("rec_2", 1, 2, 5, 50);
		assertEquals(true, rectangle.isOverlapping(rec2));
		Rectangle rec3 = new Rectangle("rec03", 100, 2000, 2000, 1000);
		assertEquals(false, rectangle.isOverlapping(rec3));
	}
	
	public void testCompareTo() {
		Rectangle rec2 = new Rectangle("rec", 1, 2, 5, 100);
		assertEquals(0, rectangle.compareTo(rec2));
		
		Rectangle rec3 = new Rectangle("rec3", 1, 2, 10, 100);
		assertEquals(-1, rectangle.compareTo(rec3));
		
		Rectangle rec4 = new Rectangle("___", 1, 2, 10, 100);
		Rectangle rec5 = new Rectangle("rec3", 0, 0, 0, 0);
		Rectangle rec6 = new Rectangle("rec3", 1, 2, 0, 0);
		assertEquals(0, rec4.compareTo(rec3));
		assertEquals(-19, rec4.compareTo(rec2));
		assertEquals(-19, rec4.compareTo(rec2));
		assertEquals(-19, rec4.compareTo(rec5));
		assertEquals(-19, rec4.compareTo(rec6));

	}

}