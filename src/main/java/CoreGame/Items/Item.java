package CoreGame.Items;

import CoreGame.Items.*;

public class Item implements IItem, java.io.Serializable {

    int weight;
    int price;
    String name;

    public Item(String name,String description, int weight, int price){
        this.name = name;
        this.weight = weight;
        this.price = price;
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
}
