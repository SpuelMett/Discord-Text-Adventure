package CoreGame.Player;

import CoreGame.Inventory.Inventory;
import CoreGame.Items.*;
import CoreGame.Room.Room;
import CoreGame.Trader.Trader;


public class Player implements java.io.Serializable{

    //stats
    int satiation;
    final int maxSatiation = 100;
    int hydration;
    final int maxHydration = 100;

    private int money;

    //fighting stats
    IFightStatsModule fightStatsModule;

    //Equipment Modules
    IEquipmentModule equipmentModule;

    Inventory inventory;
    Room room;





    public Player(Room room, int health, int attack, int defense){

        fightStatsModule = new FighStatsModule(health, attack, defense);
        equipmentModule = new EquipmentModule(this);

        this.satiation = 10;
        this.hydration = 10;

        this.money = 5;

        this.room = room;
        inventory = new Inventory();
    }

    public boolean takeItem(IItem newItem){
        return inventory.addItem(newItem);
    }
    public IItem dropItem(String itemName){
        return inventory.removeItem(itemName);
    }

    public Room getRoom(){
        return room;
    }

    /**
     * changes the room of the Player
     * @param newRoom
     */
    public void changeRoom(Room newRoom){
        room = newRoom;
    }

    /**
     * checks if the player has a specific Item
     * @param item
     * @return
     */
    public boolean hasItem(IItem item){
        return inventory.hasItem(item);
    }
    public boolean hasItem(String item){
        return inventory.hasItem(item);
    }
    public IItem getItem(String itemName){
        return inventory.getItem(itemName);
    }

    /*
    public FightStats getFightStats() {
        return fightStats;
    }

     */

    public void addSatiation(int newHunger){
        satiation += newHunger;
        if(satiation > maxSatiation) satiation = maxSatiation;
    }
    public void removeSatiation(int deltaHunger){
        satiation -= deltaHunger;
        if(satiation < 1) death("hunger");
    }

    public void addHydration(int newThirst){
        hydration += newThirst;
        if(hydration > maxHydration) hydration = maxHydration;
    }
    public void removeHydration(int deltaThirst){
        hydration -= deltaThirst;
        if(hydration < 1) death("thirst");
    }

    public String getDescription(){
        StringBuilder sb = new StringBuilder();
        sb.append("You: ").append("\n");
        sb.append("Health: ").append(fightStatsModule.getHealth()).append("\n");
        sb.append("Satiation: ").append(satiation).append("\n");
        sb.append("Hydration: ").append(hydration).append("\n");
        sb.append("\n");

        //Description from Equipment
        sb.append(equipmentModule.getDescription());
        sb.append("\n");

        //StatDescription
        sb.append(getStatDescription());
        sb.append("\n");

        //Inventory Description
        sb.append(inventory.getDescription());
        return  sb.toString();
    }

    public String getStatDescription(){
        StringBuilder sb = new StringBuilder();
        sb.append("Attack: ").append(fightStatsModule.getAttack()).append("\n");
        sb.append("Defence: ").append(fightStatsModule.getDefence()).append("\n");;
        return sb.toString();
    }

    public void death(String reason){

    }

    public boolean checkDead(){
        if(satiation < 1) return true;
        if(hydration < 1) return true;
        if(fightStatsModule.getHealth() < 1) return true;

        return false;
    }

    public void die(){

    }

    public IFightStatsModule getFightStatsModule(){
        return fightStatsModule;
    }
    public IEquipmentModule getEquipmentModule(){
        return equipmentModule;
    }

    /**
     * Checks if player has enough money. If yes remove the money;
     * @param value
     * @return
     */
    public boolean removeMoney(int value){
        if(money >= value){
            money -= value;
            return true;
        }
        else return false;
    }

    private void addMoney(int value){
        money += value;
    }

    /**
     * Tries to sell the item to a trader.
     * @param itemName
     * @param trader
     * @return
     */
    public String sellItem(String itemName, Trader trader){
        IItem item = getItem(itemName);
        if(item == null) return "You don't have this.";
        int value = trader.buy(item);
        addMoney(value);
        inventory.removeItem(item);
        return "You sold " + itemName + " and got " + value + " gold for it.";
    }

    /**
     * Checks if the player can buy the item.
     * Currently check only if enough money.
     * Not added weight limit yet.
     * @param price
     * @return
     */
    public boolean buyItem(int price){
        return removeMoney(price);
    }

}
