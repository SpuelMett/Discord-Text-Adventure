package CoreGame.Items;

import CoreGame.Items.*;

public class Item implements IItem, java.io.Serializable {

    private int weight;
    private int price;
    private String name;
    private String description;
    private String type;
    private int typeValue;

    public Item(String name,String description, int weight, int price, String type, int typeValue){
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.price = price;
        this.type = type;
        this.typeValue = typeValue;
    }

    public int getWeight(){
        return weight;
    }
    public int getPrice(){
        return price;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public String getType(){
        return type;
    }
    public int getTypeValue(){
        return typeValue;
    }
}
