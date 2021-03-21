package perspective;

/**
 *
 * @author Jan Konarski
 */
public class Configuration {
    public int imageWidth = 720;
    public int imageHeight = 480;
    public int windowWidth = 720;
    public int windowHeight = 480;
    //TODO: image background color
    //TODO: image line color
    
    public int parse(String stringValue) throws IllegalArgumentException{
        int value = Integer.parseInt(stringValue);
        if(value < 1)
            throw new IllegalArgumentException("Value couldn't be negative value");
        return value;
    }
}
