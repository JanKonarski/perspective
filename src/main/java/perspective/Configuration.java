package perspective;

/**
 *
 * @author Jan Konarski
 */
public class Configuration {
    public int imageWidth = 1280;
    public int imageHeight = 800;
    public int windowWidth = 1280;
    public int windowHeight = 800;
    //TODO: image background color
    //TODO: image line color
    
    public int parse(String stringValue) throws IllegalArgumentException{
        int value = Integer.parseInt(stringValue);
        if(value < 1)
            throw new IllegalArgumentException("Value couldn't be negative value");
        return value;
    }
}
