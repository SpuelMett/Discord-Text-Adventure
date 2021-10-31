package CoreGame.Inventory;

import CoreGame.Items.*;

import java.util.ArrayList;

public class Inventory implements java.io.Serializable{

    ArrayList<IItem> itemList;

    public Inventory(){
        itemList = new ArrayList<>();
    }

    public ArrayList<IItem> getItemList(){
        return itemList;
    }


    public int getSize(){
        return itemList.size();
    }

    public boolean addItem(IItem newItem){
        return itemList.add(newItem);
    }
    public boolean removeItem(IItem oldItem){
        return itemList.remove(oldItem);
    }
    public IItem removeItem(String itemName){
        IItem removedItem = nameToObject(itemName);
        removeItem(removedItem);
        return removedItem;
    }


    private IItem nameToObject(String itemName){
        for(IItem item : itemList){
            if(item.getName().equals(itemName)){
                return item;
            }
        }
        return null;
    }

    /**
     * Checks if the Item is in the Inventory by Item Object
     * @param checkItem
     * @return
     */
    public boolean hasItem(IItem checkItem){
        for(IItem item : itemList){
            if(item.equals(checkItem)) return true;
        }
        return false;
    }

    /**
     * Checks if the Item is in the Inventory by the Item Name
     * @param itemName
     * @return
     */
    public boolean hasItem(String itemName){
        IItem item = nameToObject(itemName);
        return item != null;
    }

    /**
     * Public Variant of nameToObject Method
     * @param itemName
     * @return
     */
    public IItem getItem(String itemName){
        return nameToObject(itemName);
    }

    /**
     * Returns all Item names in a comma seperated List
     * @return
     */
    public String getDescription(){
        StringBuilder sb = new StringBuilder();
        sb.append("Items: ");

        int size = itemList.size();
        for(int i=0;i<size;i++){
            if(i == size -1) sb.append(itemList.get(i).getName());
            else sb.append(itemList.get(i).getName()).append(", ");
        }

        return sb.toString();
    }
}
