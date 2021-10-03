package CoreGame;

import CoreGame.Items.*;



public class Player implements java.io.Serializable{

    //stats
    int saturation;
    final int maxSaturation = 100;
    int hydration;
    final int maxHydration = 100;

    //fighting stats
    FightStats fightStats;

    Inventory inventory;
    Room room;

    ItemWeapon weapon;
    ItemArmor armor;



    public Player(Room room){
        fightStats = new FightStats(100, 20, 20);

        this.saturation = 10;
        this.hydration = 10;

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

    public FightStats getFightStats() {
        return fightStats;
    }

    public void addSaturation(int newHunger){
        saturation += newHunger;
        if(saturation > maxSaturation) saturation = maxSaturation;
    }
    public void removeSaturation(int deltaHunger){
        saturation -= deltaHunger;
        if(saturation < 1) death("hunger");
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
        sb.append("Health: ").append(fightStats.getHealth()).append("\n");
        sb.append("Saturation: ").append(saturation).append("\n");
        sb.append("Hydration: ").append(hydration).append("\n");
        sb.append(inventory.getDescription());
        return  sb.toString();
    }

    public void death(String reason){

    }

    public void equipWeapon(ItemWeapon newWeapon){
        fightStats.removeAttack(weapon.getDamage());
        weapon = newWeapon;
        fightStats.addAttack(weapon.getDamage());

    }

}
