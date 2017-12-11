package mars.coordinate;

/**
 * Wrapper class for an (X, Y) coordinate.
 *
 * TODO: make this abstract and extend for different units?
 */
public class Coordinate {
    private int x;
    private int y;
    private String units = "pixels";

    /**
     * Constructor for Coordinate class
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //----Getter/Setter Methods----------------------------------------------------------------------------------------

    public int getX() {
        return x;
    }

    public void setX(int x) { this.x = x; }

    public int getY() {
        return y;
    }

    public void setY(int y) { this.y = y; }

    public String getUnits() {
        return units;
    }
}
