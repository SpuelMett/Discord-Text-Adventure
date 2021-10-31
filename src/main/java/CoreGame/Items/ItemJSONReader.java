package CoreGame.Items;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ItemJSONReader {

    JSONParser parser = new JSONParser();

    public IItem readItem(String itemName) {
        String fileName = "SavedGameFiles/Items/" + itemName + ".json";

        JSONObject jsonObject = (JSONObject) readJson(fileName);

        return convertJsonToItem(jsonObject);
    }

    private Object readJson(String filename) {
        try {
            FileReader reader = new FileReader(filename);
            JSONParser jsonParser = new JSONParser();
            return jsonParser.parse(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public IItem convertJsonToItem(JSONObject jsonObject){
        String name = (String) jsonObject.get("name");
        String description = (String) jsonObject.get("description");
        int weight = Integer.valueOf(jsonObject.get("weight").toString());
        int price = Integer.valueOf(jsonObject.get("price").toString());
        String type = (String) jsonObject.get("type");
        int typeValue = Integer.valueOf(jsonObject.get("typeValue").toString());

        return new Item(name, description, weight, price, type, typeValue);
    }
}
