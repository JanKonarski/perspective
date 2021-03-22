package perspective;

import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Jan Konasrki
 */
public class Main {
    public static void main(String[] args) {
        String propFileName = "config.properties";
        Configuration config = new Configuration();
        
        //Get or set configuration
        getConfiguration(propFileName, config);
        
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                new Window(config);
            }
        });
    }
    
    private static void getConfiguration(String propFileName, Configuration config) {
        try {
            InputStream inStream = new FileInputStream(propFileName);
            Properties prop = new Properties();
            prop.load(inStream);
            
            //Load configurate parameters from file
            config.windowWidth = config.parse(prop.getProperty("windowWidth"));
            config.windowHeight = config.parse(prop.getProperty("windowHeight"));
            config.cameraFocalLength = config.parse(prop.getProperty("cameraFocalLength"));
        } catch(IOException e) {
            //If occure problem with saving file
            createProperties(propFileName, config);
        } catch(IllegalArgumentException e) {
            //If occure unexpected value in configuration file
            JOptionPane.showMessageDialog(
                new JFrame(),
                e.getMessage(),
                "Value error",
                JOptionPane.ERROR_MESSAGE
            );
            System.exit(0);
        }
    }
    
    private static void createProperties(String propFileName, Configuration config) {
        try {
            OutputStream outStream = new FileOutputStream(propFileName);
            Properties prop = new Properties();
            
            //Set default configuration values
            prop.setProperty("windowWidth", String.valueOf(config.windowWidth));
            prop.setProperty("windowHeight", String.valueOf(config.windowHeight));
            prop.setProperty("cameraFocalLength", String.valueOf(config.cameraFocalLength));
            
            //Save into file
            prop.store(outStream, "Perspective program configuration");
        } catch(IOException e) {
            //If occure problem with saving file
            JOptionPane.showMessageDialog(
                new JFrame(),
                "Program couldn't save configuration into file",
                "Configuration save error",
                JOptionPane.ERROR_MESSAGE
            );
            System.exit(0);
        }
    }
}
