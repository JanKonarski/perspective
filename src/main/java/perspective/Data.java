package perspective;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jan Konarski
 */
public class Data {
    public void read(String filePath) throws IOException {
        Path path = Path.of(filePath);
        String jsonText = Files.readString(path, StandardCharsets.UTF_8);
        
        //Create json parser
        JSONObject json = new JSONObject(jsonText);
        
        //
        JSONArray figuresArray = json.getJSONArray("figures");
        for(var figureItem: figuresArray) {
            JSONObject figureObject = (JSONObject)figureItem;
            JSONArray linesArray = figureObject.getJSONArray("lines");
            for(var lineItem: linesArray) {
                JSONObject lineObject = (JSONObject)lineItem;
                
                //TODO: pobieranie koordynatów z lineObject
            }
        }
    }
}
