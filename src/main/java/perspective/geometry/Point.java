package perspective.geometry;

/**
 *
 * @author Jan Konarski
 */
public class Point {
    private double x;
    private double y;
    private double z;
    
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void set(double[] values) {
        if(values.length != 3)
            throw new RuntimeException("Invalid input vector");
        
        x = values[0];
        y = values[1];
        z = values[2];
    }
    
    public double[] get() {
        return new double[] {x, y, z};
    }
    
    public void X(double value) {
        x = value;
    }
    
    public double X() {
        return x;
    }
    
    public void Y(double value) {
        y = value;
    }
    
    public double Y() {
        return y;
    }
    
    public void Z(double value) {
        z = value;
    }
    
    public double Z() {
        return z;
    }
}
