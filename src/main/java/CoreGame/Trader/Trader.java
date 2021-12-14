package CoreGame.Trader;

import CoreGame.Inventory.Inventory;
import CoreGame.Items.IItem;
import CoreGame.Player.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class Trader implements java.io.Serializable {

    private Inventory inventory;
    private String name;

    public Trader(String name){
        inventory = new Inventory();
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void addItem(IItem item){
        inventory.addItem(item);
    }

    /**
     * Merchant sells Item to player
     */
    public String sell(String itemName, Player player){
        //try and get Item from Inventory
        IItem item = inventory.getItem(itemName);
        if(item == null) return "This is not for sale.";

        //check if player can buy the item
        int price = item.getPrice();
        if(player.buyItem(price)){
            inventory.removeItem(itemName);
            return "You bought " + itemName + " and payed " + price + " gold.";
        }

        //check player weight limit (not implemented yet)
        return "Something went wrong :( ";
    }

    /**
     * Merchant buys Item from player
     * @param item
     */
    public int buy(IItem item){
        inventory.addItem(item);
        return item.getPrice();
    }

    /**
     * Returns a Text with all available items and there prices
     * @return
     */
    public String getItemListDescription(){
        StringBuilder sb = new StringBuilder();
        ArrayList<IItem> itemList = inventory.getItemList();
        for(IItem item:itemList){
            sb.append(item.getName()).append(": ").append(item.getPrice()).append("\n");
        }
        return sb.toString();
    }
}
