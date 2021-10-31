package CoreGame.Inventory;

import CoreGame.Items.IItem;
import CoreGame.Items.Item;
import CoreGame.Items.ItemJSONReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class InventoryJsonReader {

    JSONParser parser = new JSONParser();

    public Inventory readInventory(String inventoryName) {
        String fileName = "SavedGameFiles/Inventory/" + inventoryName + ".json";

        JSONObject jsonObject = (JSONObject) readJson(fileName);

        //Values
        Inventory inventory = new Inventory();

        List<JSONObject> itemObjects = (List<JSONObject>) jsonObject.get("items");
        for (JSONObject itemObject: itemObjects){
            IItem item = new ItemJSONReader().convertJsonToItem(itemObject);
            inventory.addItem(item);
        }

        return inventory;
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
}
