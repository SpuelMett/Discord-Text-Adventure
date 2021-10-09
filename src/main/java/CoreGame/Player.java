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
        fightStats = new FightStats(100, 10, 10);

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
        sb.append("\n");

        if(weapon != null) sb.append("Weapon: ").append(weapon.getName()).append("\n");
        if(armor != null)sb.append("Armor: ").append(armor.getName()).append("\n");

        sb.append(getStatDescription());
        sb.append("\n");
        sb.append(inventory.getDescription());
        return  sb.toString();
    }

    public String getStatDescription(){
        StringBuilder sb = new StringBuilder();
        sb.append("Attack: ").append(fightStats.getAttack()).append("\n");
        sb.append("Defence: ").append(fightStats.getDefence()).append("\n");;
        return sb.toString();
    }

    public void death(String reason){

    }

    /**
     * Return imput Item if Item cant be equiped
     * @param newEquipment
     * @return
     */
    public String equip(IItem newEquipment){
        //check item Type
        if(newEquipment == null) return "You don't have this item in your inventory";
        //Check for Armor and Weapon
        if(newEquipment.getClass().getName().equals(ItemArmor.class.getName())) return equipArmor((ItemArmor) newEquipment);
        if(newEquipment.getClass().getName().equals(ItemWeapon.class.getName())) return equipWeapon((ItemWeapon) newEquipment);
        else return "You cant equip " + newEquipment.getName() + ".";
    }
    public String unequip(String equipmentName){
        if(weapon !=null && weapon.getName().equals(equipmentName)) return unequipWeapon();
        if(armor !=null && armor.getName().equals(equipmentName)) return unequipArmor();
        
        return "You don't have this item quipped.";
    }

    /**
     * Returns old weapon if changed, null if it worked
     * @param newWeapon
     * @return
     */
    public String equipWeapon(ItemWeapon newWeapon){
        //check if player has a weapon equiped
        if(weapon == null){
            weapon = newWeapon;
            fightStats.addAttack(weapon.getDamage());
            inventory.removeItem(newWeapon);
            return "You equipped " + weapon.getName() + ".";
        }
        else return "You cant equip " + weapon.getName() + ".";
    }
    public String equipArmor(ItemArmor newArmor){
        //check if player has a weapon equipped
        if(armor == null){
            armor = newArmor;
            fightStats.addDefence(armor.getDefence());
            inventory.removeItem(newArmor);
            return "You equipped " + armor.getName() + ".";
        }
        else return "You cant equip " + armor.getName() + ".";
    }
    public String unequipWeapon(){
        if(weapon == null) return "You don't have this weapon equipped";
        else{
            fightStats.removeAttack(weapon.getDamage());
            inventory.addItem(weapon);
            weapon = null;
            return "You unequipped your Weapon";
        }
    }
    public String unequipArmor(){
        if(armor == null) return "You don't have this armor equipped";
        else{
            fightStats.removeDefence(armor.getDefence());
            inventory.addItem(armor);
            armor = null;
            return "You Unequipped your armor";
        }
    }


}
