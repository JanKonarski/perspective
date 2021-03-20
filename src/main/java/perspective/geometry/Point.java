package perspective.geometry;

/**
 *
 * @author Jan Konarski
 */
public class Point {
    private float x;
    private float y;
    private float z;
    
    public Point(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void set(float[] values) {
        if(values.length != 3)
            throw new RuntimeException("Invalid input vector");
        
        x = values[0];
        y = values[1];
        z = values[2];
    }
    
    public float[] get() {
        return new float[] {x, y, z};
    }
    
    public void X(float value) {
        x = value;
    }
    
    public float X() {
        return x;
    }
    
    public void Y(float value) {
        y = value;
    }
    
    public float Y() {
        return y;
    }
    
    public void Z(float value) {
        z = value;
    }
    
    public float Z() {
        return z;
    }
}
