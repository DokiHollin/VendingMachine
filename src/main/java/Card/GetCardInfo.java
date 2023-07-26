package Card;

import App.App;
import Roles.Customer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GetCardInfo {

    public void parse(String path) {
        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(new FileReader(path));
            JSONArray jsonArray = (JSONArray) object;
            for (Object o: jsonArray) {
                JSONObject card = (JSONObject) o;
                App.cardInfo.put((String) card.get("name"),card.get("number").toString());
            }
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
