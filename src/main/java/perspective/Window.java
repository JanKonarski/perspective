package perspective;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Jan Konarski
 */
//TODO: przytrzymanie przycisku powtarza czynność
public class Window extends JFrame implements KeyListener, ActionListener {
    private Color backgroundColor = Color.DARK_GRAY;
    private Color textColor = Color.WHITE;
    
    public Window(Configuration config) {
        //Set close window on exit button
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Window size
        setSize(
            config.windowWidth,
            config.windowHeight
        );
        //Add keys operation support
        addKeyListener(this);
        //Window name
        setTitle("3Dto2D perspective view");
        //Show gui window
        setVisible(true);
        
        //Window menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(backgroundColor);
        setJMenuBar(menuBar);
        
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        menuBar.add(file);
        
        JMenuItem inFile = new JMenuItem("Load file");
        inFile.setMnemonic(KeyEvent.VK_L);
        file.add(inFile);
        
        JMenu help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);
        menuBar.add(help);
        
        JMenuItem about = new JMenuItem("About");
        about.setMnemonic(KeyEvent.VK_A);
        help.add(about);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("test");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("test2");
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
