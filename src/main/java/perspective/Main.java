package perspective;

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
    private String propFilename = "config.properties";
    private Configuration config = new Configuration();
    
    public static void main(String[] args) {
        //TODO: rozwiązać problem static
        getConfiguration();
    }
    
    private void getConfiguration() {
        try {
            InputStream inStream = new FileInputStream(propFilename);
            Properties prop = new Properties();
            prop.load(inStream);
            
            //Load configurate parameters from file
            config.imageWidth = config.parse(prop.getProperty("imageWidth"));
            config.imageHeight = config.parse(prop.getProperty("imageHeight"));
            config.windowWidth = config.parse(prop.getProperty("windowWidth"));
            config.windowHeight = config.parse(prop.getProperty("windowHeight"));
        } catch(IOException e) {
            //If occure problem with saving file
            createProperties();
        } catch(IllegalArgumentException e) {
            //If occure unexpected value in configuration file
            JOptionPane.showMessageDialog(
                new JFrame(),
                e.getMessage(),
                "Value error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void createProperties() {
        try {
            OutputStream outStream = new FileOutputStream(propFilename);
            Properties prop = new Properties();
            
            //Set default configuration values
            prop.setProperty("imageWidth", String.valueOf(config.imageHeight));
            prop.setProperty("imageHeight", String.valueOf(config.imageHeight));
            prop.setProperty("windowWidth", String.valueOf(config.windowWidth));
            prop.setProperty("windowHeight", String.valueOf(config.windowHeight));
            
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
        }
    }
}
