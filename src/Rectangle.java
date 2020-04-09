/**
 * Rectangle object class
 * 
 * @author ramyanandigam, catherinesquillante
 */
public class Rectangle implements Comparable<Rectangle>{
	
	String name;
	private int x;
	private int y;
	private int w;
	private int h;
	
	/**
	 * Creates a new rectangle object
	 * 
	 * @param name: name of the rectangle
	 * @param x: x coordinate of the rectangle
	 * @param y: y coordinate of the rectangle
	 * @param w: width of the rectangle
	 * @param h: height of the rectangle
	 */
	Rectangle(String name, int x, int y, int w, int h) 
    { 
        this.name = name; 
        this.x = x; 
        this.y = y;
        this.w = w;
        this.h = h;
    }
	
	/**
	 * @return String representation of the Rectangle object
	 */
	public String toString() {
		String result = "(" + this.name + ", " + this.x + ", " + this.y + ", " + this.w + ", " +  this.h + ")";
		return result;
		}

	/**
	 * Gets name of Rectangle
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name of Rectangle
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets x coordinate of Rectangle
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets x coordinate of Rectangle
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets y coordinate of Rectangle
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets y coordinate of Rectangle
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets width of Rectangle
	 */
	public int getW() {
		return w;
	}

	/**
	 * Sets width of Rectangle
	 */
	public void setW(int w) {
		this.w = w;
	}

	/**
	 * Gets height of Rectangle
	 */
	public int getH() {
		return h;
	}

	/**
	 * Sets height of Rectangle
	 */
	public void setH(int h) {
		this.h = h;
	}
	
	/**
	 * @Return true if rectangle is overlapping with parameter rectangle r
	 */
	public boolean isOverlapping (Rectangle r) {
	    return this.x < r.x + r.w && this.x + this.w > r.x && this.y < r.y + r.h && this.y + this.h > r.y;
	}

	/**
	 * Compares two rectangles
	 * @return 0 if rectangles are the same, other integer if they're  not
	 */
	@Override
	public int compareTo(Rectangle o) {
		//if name is ___ then compare the coordinates, used in manager's remove method
		if(this.name == "___") {
			if(this.x == o.x && this.y == o.y && this.w == o.w && this.h == o.h) {
				return 0;
			}
		}
		int result = this.name.compareTo(o.name);
		return result;
	}
	
	

}
