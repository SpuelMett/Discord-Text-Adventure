package CoreGame.Items;

import CoreGame.Inventory.Inventory;
import CoreGame.Inventory.InventoryJsonReader;
import CoreGame.Inventory.InventoryJsonWriter;

public class MainTest {

    public static void main(String[] args) {
        IItem woodenSword = new Item("WoodenSword", "A small Sword made from wood.", 1, 1, "weapon", 1);
        new ItemJSONWriter().writeItem(woodenSword);

        IItem ironSword = new Item("IronSword","This is a normal iron sword.",1,1, "weapon", 2);
        new ItemJSONWriter().writeItem(ironSword);

        IItem food1 = new Item("Apple", "A normal Apple.", 1, 1, "food", 10);
        new ItemJSONWriter().writeItem(food1);

        IItem drink1 = new Item("Water", "A bottle of water.", 1, 1, "drink", 20);
        new ItemJSONWriter().writeItem(drink1);

        //item = new JSONReader().readItem("WoodenSword");


        Inventory inventory = new Inventory();
        inventory.addItem(woodenSword);
        inventory.addItem(food1);
        inventory.addItem(drink1);
        new InventoryJsonWriter().writeInventory(inventory,"testInventory");

        inventory = new InventoryJsonReader().readInventory("testInventory");
        System.out.println(inventory.getItem("Apple").getName());
    }
}
