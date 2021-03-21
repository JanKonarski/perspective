package perspective;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author Jan Konarski
 */
//TODO: przytrzymanie przycisku powtarza czynność
public class Window extends JFrame implements KeyListener {
    public Window(Configuration config) {
        //Set close window on exit button
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Window size
        setSize(
            config.windowWidth,
            config.windowHeight
        );
        //Window name
        setName("3Dto2D perspective view");
        //Show gui window
        setVisible(true);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
