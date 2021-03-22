package perspective;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import org.json.JSONException;
import perspective.geometry.Camera;
import perspective.geometry.Line;

/**
 *
 * @author Jan Konarski
 */
//TODO: przytrzymanie przycisku powtarza czynność
public class Window extends JFrame implements KeyListener, ActionListener {
    private final Color windowBackgroundColor = new Color(43, 43, 43);
    private final Color backgroundColor = new Color(60, 63, 65);
    private final Color textColor = new Color(133, 129, 124);
    
    private final JMenuBar menuBar;
    private final JMenu file;
    private final JMenuItem inFile;
    private final JMenu help;
    private final JMenuItem about;
    
    private final Camera view;
    
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
        
        //Menu bar
        menuBar = new JMenuBar();
        menuBar.setBackground(backgroundColor);
        //Add menu bar to window
        setJMenuBar(menuBar);
        
        file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        file.setForeground(textColor);
        menuBar.add(file);
        
        inFile = new JMenuItem("Load file");
        inFile.addActionListener(this);
        inFile.setMnemonic(KeyEvent.VK_L);
        inFile.setBackground(backgroundColor);
        inFile.setForeground(textColor);
        file.add(inFile);
        
        help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);
        help.setForeground(textColor);
        menuBar.add(help);
        
        about = new JMenuItem("About");
        about.addActionListener(this);
        about.setMnemonic(KeyEvent.VK_A);
        about.setBackground(backgroundColor);
        about.setForeground(textColor);
        help.add(about);
        
        view = new Camera(config);
        view.setBackground(windowBackgroundColor);
        add(view);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        //TODO 
        //Forward move
        if(e.getKeyCode() == KeyEvent.VK_W) {
            view.moveZ(-view.SHIFT);
        }
        //Backword move
        if(e.getKeyCode() == KeyEvent.VK_S) {
            view.moveZ(+view.SHIFT);
        }
        //Left move
        if(e.getKeyCode() == KeyEvent.VK_A) {
            view.moveX(+view.SHIFT);
        }
        //Right move
        if(e.getKeyCode() == KeyEvent.VK_D) {
            view.moveX(-view.SHIFT);
        }
        //Up move
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            view.moveY(+view.SHIFT);
        }
        //Down move
        if(e.getKeyCode() == KeyEvent.VK_Z) {
            view.moveY(-view.SHIFT);
        }
        //Forward rotation
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            view.rotateX(+view.TURN);
        }
        //Backword rotation
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            view.rotateX(-view.TURN);
        }
        //Left rotation
        if(e.getKeyCode() == KeyEvent.VK_Q) {
            view.rotateY(+view.TURN);
        }
        //Right rotation
        if(e.getKeyCode() == KeyEvent.VK_E) {
            view.rotateY(-view.TURN);
        }
        //Left rotation down
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            view.rotateZ(+view.TURN);
        }
        //Right rotation down
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            view.rotateZ(-view.TURN);
        }
        
        view.repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == inFile) {
            JFileChooser chooser = new JFileChooser(
                FileSystemView.getFileSystemView().getHomeDirectory()
            );
            int isChoosed = chooser.showOpenDialog(this);
            if(isChoosed == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = chooser.getSelectedFile();
                    List<Line> world = new Data().read(file.getPath());
                    view.setNewWorld(world);
                    view.repaint();
                } catch(IOException | JSONException ex) {
                    //
                    JOptionPane.showMessageDialog(
                        new JFrame(),
                        "Occurred problem with file syntax or with file itself",
                        "Input file error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
        if(e.getSource() == about) {
            //Show about window
            JOptionPane.showMessageDialog(
                new JFrame(),
                "Perspective projection view program (3Dto2D) version: 1.0",
                "About",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
