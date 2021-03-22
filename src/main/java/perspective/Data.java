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
    public List<Line> read(String filePath) throws IOException, JSONException {
        List<Line> world = new ArrayList<>();
        
        Path path = Path.of(filePath);
        final String jsonText = Files.readString(path, StandardCharsets.UTF_8);
        
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
                    lineObject.getDouble("x1"),
                    lineObject.getDouble("y1"),
                    lineObject.getDouble("z1")
                );
                
                //Create second line point
                Point point2 = new Point(
                    lineObject.getDouble("x2"),
                    lineObject.getDouble("y2"),
                    lineObject.getDouble("z2")
                );
                
                //Add line to world
                world.add(new Line(point1, point2));
            }
        }
        
        return world;
    }
}
