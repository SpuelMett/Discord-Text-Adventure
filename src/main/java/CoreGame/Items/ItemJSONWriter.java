package CoreGame.Items;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ItemJSONWriter {

    public void writeItem(IItem item){

        String fileName = "SavedGameFiles/Items/" + item.getName() + ".json";

        JSONObject object = convertToJson(item);

        try {
            Files.write(Paths.get(fileName), object.toJSONString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject convertToJson(IItem item){
        JSONObject object = new JSONObject();
        object.put("name", item.getName());
        object.put("description", item.getDescription());
        object.put("weight", item.getPrice());
        object.put("price", item.getPrice());
        object.put("type", item.getType());
        object.put("typeValue", item.getTypeValue());

        System.out.println(object.toJSONString());

        return object;
    }
}
