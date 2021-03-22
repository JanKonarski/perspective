package perspective.geometry;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPanel;
import perspective.Configuration;

/**
 *
 * @author Jan Konarski
 */
public class Camera extends JPanel {
    public final double SHIFT = 0.25;
    public final double TURN = 0.1;
    
    private int cameraDieWidth = 7; //6.75
    private int cameraDieHeight = 12;
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
    
    //TODO ten override nie daje koloru tła
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        Graphics2D graphics2D = (Graphics2D)graphics;
        
        if(world != null) {
            boolean[] visible = new boolean[world.size()];
            for(int i=0; i<visible.length; i++)
                visible[i]  = true;

            //TODO zmienić nieeleganckie rozwiązanie
            for(int i = 0; i<world.size(); i++)
                if(world.get(i).point1().get()[2] < 0 || world.get(i).point2().get()[2] < 0)
                    visible[i] = false;

            for(int i=0; i<world.size(); i++)
                if(visible[i]) {
                    double x1 = ((world.get(i).point1().get()[0]+xPosition)*focalLength)/(world.get(i).point1().get()[2]+focalLength+zPosition);
                    double y1 = ((world.get(i).point1().get()[1]+yPosition)*focalLength)/(world.get(i).point1().get()[2]+focalLength+zPosition);
                    double x2 = ((world.get(i).point2().get()[0]+xPosition)*focalLength)/(world.get(i).point2().get()[2]+focalLength+zPosition);
                    double y2 = ((world.get(i).point2().get()[1]+yPosition)*focalLength)/(world.get(i).point2().get()[2]+focalLength+zPosition);
                    
                    int scaleX = getSize().width / cameraDieWidth;
                    int scaleY = getSize().height / cameraDieHeight;
                    
                    int centerX = getSize().width / 2;
                    int centerY = getSize().height / 2;
                    
                    int px1 = (int)Math.ceil(x1 * scaleX + centerX);
                    int py1 = (int)Math.ceil(y1 * scaleY + centerY);
                    int px2 = (int)Math.ceil(x2 * scaleX + centerX);
                    int py2 = (int)Math.ceil(y2 * scaleY + centerY);
                    
                    graphics2D.setStroke(new BasicStroke(2));
                    graphics2D.setColor(new Color(204, 204, 204));
                    graphics2D.drawLine(px1, py1, px2, py2);
                    
                    //System.out.println(String.format("%f %f - %f %f", x1, y1, x2, y2));
                }
            //System.out.println(String.format("%dx%d", getSize().width, getSize().height));
        }
    }
}
