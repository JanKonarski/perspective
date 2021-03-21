package perspective;

import javax.swing.JPanel;

/**
 *
 * @author Jan Konarski
 */
//TODO: przytrzymanie przycisku powtarza czynność
public class Window extends JPanel {
    public Window(Configuration config) {
        //Window size
        setSize(
            config.windowWidth,
            config.windowHeight
        );
        //Window name
        setName("3Dto2D perspective view");
    }
}
