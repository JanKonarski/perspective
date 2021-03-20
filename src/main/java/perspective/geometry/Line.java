package perspective.geometry;

/**
 *
 * @author Jan Konarski
 */
public class Line {
    private Point point1;
    private Point point2;
    
    public Line(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }
    
    public void point1(Point point) {
        point1 = point;
    }
    
    public Point point1() {
        return point1;
    }
    
    public void point2(Point point) {
        point2 = point;
    }
    
    public Point point2() {
        return point2;
    }
    
    public Point[] get() {
        return new Point[] {point1, point2};
    }
}
