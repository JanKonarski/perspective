package perspective;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import perspective.geometry.Line;
import perspective.geometry.Point;

/**
 *
 * @author Jan Konarski
 */
public class Data {
    private String jsonText;
    private List<Line> world;
    
    public List<Line> read(String filePath) throws IOException, JSONException {
        world = new ArrayList<>();
        
        Path path = Path.of(filePath);
        jsonText = Files.readString(path, StandardCharsets.UTF_8);
        
        //Create json parser
        JSONObject json = new JSONObject(jsonText);
        
        //Create array of figure objects
        JSONArray figuresArray = json.getJSONArray("figures");
        for(var figureItem: figuresArray) {
            JSONObject figureObject = (JSONObject)figureItem;
            //Creae array od line objects
            JSONArray linesArray = figureObject.getJSONArray("lines");
            for(var lineItem: linesArray) {
                JSONObject lineObject = (JSONObject)lineItem;
                
                //Create first line point
                Point point1 = new Point(
                    lineObject.getFloat("x1"),
                    lineObject.getFloat("y1"),
                    lineObject.getFloat("z1")
                );
                
                //Create second line point
                Point point2 = new Point(
                    lineObject.getFloat("x2"),
                    lineObject.getFloat("y2"),
                    lineObject.getFloat("z2")
                );
                
                //Add line to world
                world.add(new Line(point1, point2));
            }
        }
        
        return world;
    }
}
