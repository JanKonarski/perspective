package perspective.geometry;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;
import perspective.Configuration;

/**
 *
 * @author Jan Konarski
 */
public class Camera extends JPanel {
    public final double SHIFT = 0.25;
    public final double TURN = 1.0;
    
    private final int cameraDieWidth = 7; //6.75
    private final int cameraDieHeight = 12;
    private final double focalLength;
    
    //World position
    private double xPosition = 0.0;
    private double yPosition = 0.0;
    private double zPosition = 0.0;
    //World rotation
    private double xRotation = 0.0;
    private double yRotation = 0.0;
    private double zRotation = 0.0;
    
    private List<Line> world = null;
    
    public Camera(Configuration config) {
        focalLength = config.cameraFocalLength / 10;
    }
    
    public void moveX(double value) {
        xPosition += value;
    }
    
    public void moveY(double value) {
        yPosition += value;
    }
    
    public void moveZ(double value) {
        zPosition += value;
    }
    
    public void rotateX(double value) {
        xRotation += value;
    }
    
    public void rotateY(double value) {
        yRotation += value;
    }
    
    public void rotateZ(double value) {
        zRotation += value;
    }
    
    public void setNewWorld(List<Line> world) {
        this.world = world;
        
        xPosition = 0.0;
        yPosition = 0.0;
        zPosition = 0.0;
        
        xRotation = 0.0;
        yRotation = 0.0;
        zRotation = 0.0;
    }
    
    public void getView() {
        
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        Graphics2D graphics2D = (Graphics2D)graphics;
        
        if(world != null) {
            for(int i=0; i<world.size(); i++) {
                Line newLine = transform(world.get(i));
                
                if(newLine.point1().get()[2] > 0 || newLine.point2().get()[2] > 0) {
                    double x1 = (newLine.point1().get()[0]*focalLength)/(newLine.point1().get()[2]+focalLength);
                    double y1 = -(newLine.point1().get()[1]*focalLength)/(newLine.point1().get()[2]+focalLength);
                    double x2 = (newLine.point2().get()[0]*focalLength)/(newLine.point2().get()[2]+focalLength);
                    double y2 = -(newLine.point2().get()[1]*focalLength)/(newLine.point2().get()[2]+focalLength);
                    
                    int scaleX = getSize().width / cameraDieWidth;
                    int scaleY = getSize().height / cameraDieHeight;
                    
                    int centerX = getSize().width / 2;
                    int centerY = getSize().height / 2;
                    
                    int px1 = (int)Math.ceil(x1 * scaleX + centerX);
                    int py1 = (int)Math.ceil(y1 * scaleY + centerY);
                    int px2 = (int)Math.ceil(x2 * scaleX + centerX);
                    int py2 = (int)Math.ceil(y2 * scaleY + centerY);
                    
                    graphics2D.setStroke(new BasicStroke(1));
                    graphics2D.setColor(new Color(204, 204, 204));
                    graphics2D.drawLine(px1, py1, px2, py2);
                }
            }
        }
    }
    
    private Line transform(Line line) {
        //Get co-ordinates of ferst 3D point
        double newX1 = line.point1().X() + xPosition;
        double newY1 = line.point1().Y() + yPosition;
        double newZ1 = line.point1().Z() + zPosition;
        
        //get co-ordinates of second 3D point
        double newX2 = line.point2().X() + xPosition;
        double newY2 = line.point2().Y() + yPosition;
        double newZ2 = line.point2().Z() + zPosition;
        
        //X rotation
        double xRadians = (xRotation * Math.PI) / 180;
        newY1 = newY1*Math.cos(xRadians) - newZ1*Math.sin(xRadians);
        newZ1 = newY1*Math.sin(xRadians) + newZ1*Math.cos(xRadians);
        newY2 = newY2*Math.cos(xRadians) - newZ2*Math.sin(xRadians);
        newZ2 = newY2*Math.sin(xRadians) + newZ2*Math.cos(xRadians);
        
        //Y rotation
        double yRadians = (yRotation * Math.PI) / 180;
        newX1 = newZ1*Math.sin(yRadians) + newX1*Math.cos(yRadians);
        newZ1 = newZ1*Math.cos(yRadians) - newX1*Math.sin(yRadians);
        newX2 = newZ2*Math.sin(yRadians) + newX2*Math.cos(yRadians);
        newZ2 = newZ2*Math.cos(yRadians) - newX2*Math.sin(yRadians);
        
        //Z rotation
        double zRadians = (zRotation * Math.PI) / 180;
        newX1 = newX1*Math.cos(zRadians) - newY1*Math.sin(zRadians);
        newY1 = newX1*Math.sin(zRadians) + newY1*Math.cos(zRadians);
        newX2 = newX2*Math.cos(zRadians) - newY2*Math.sin(zRadians);
        newY2 = newX2*Math.sin(zRadians) + newY2*Math.cos(zRadians);
        
        return new Line(
            new Point(newX1, newY1, newZ1),
            new Point(newX2, newY2, newZ2)
        );
    }
}
