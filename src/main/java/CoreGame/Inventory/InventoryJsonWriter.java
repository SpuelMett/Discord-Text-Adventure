package CoreGame.Inventory;

import CoreGame.Items.IItem;
import CoreGame.Items.ItemJSONWriter;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class InventoryJsonWriter {

    public void writeInventory(Inventory inventory, String inventoryName){

        String fileName = "SavedGameFiles/Inventory/" + inventoryName + ".json";

        JSONObject object = convertToJson(inventory, inventoryName);

        try {
            Files.write(Paths.get(fileName), object.toJSONString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject convertToJson(Inventory inventory, String inventoryName){
        JSONObject object = new JSONObject();

        JSONArray items = new JSONArray();
        for(IItem item : inventory.getItemList()){
            items.add(new ItemJSONWriter().convertToJson(item)); //.toJSONString()
        }
        object.put("items", items);

        return object;
    }

}
