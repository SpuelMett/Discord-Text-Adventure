package CoreGame.Player;

import CoreGame.Items.IItem;

public class EquipmentModule implements IEquipmentModule, java.io.Serializable{

    private Player player;

    //Equipment
    IItem weapon;
    IItem armor;

    public EquipmentModule(Player player){
        this.player = player;
    }

    public String getDescription(){
        StringBuilder sb = new StringBuilder();
        if(weapon != null) sb.append("Weapon: ").append(weapon.getName()).append("\n");
        if(armor != null)sb.append("Armor: ").append(armor.getName());
        return sb.toString();
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
        String itemType = newEquipment.getType();

        switch (itemType) {
            case "armor":
                return equipArmor(newEquipment);
            case "weapon":
                return equipWeapon(newEquipment);
            default:
                return "You cant equip " + newEquipment.getName() + ".";
        }

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
    public String equipWeapon(IItem newWeapon){
        //check if player has a weapon equiped
        if(weapon == null){
            weapon = newWeapon;
            player.getFightStatsModule().addAttack(weapon.getTypeValue());
            player.dropItem(newWeapon.getName());
            return "You equipped " + weapon.getName() + ".";
        }
        else return "You cant equip " + weapon.getName() + ".";
    }
    public String equipArmor(IItem newArmor){
        //check if player has a weapon equipped
        if(armor == null){
            armor = newArmor;
            player.getFightStatsModule().addDefence(armor.getTypeValue());
            player.dropItem(newArmor.getName());
            return "You equipped " + armor.getName() + ".";
        }
        else return "You cant equip " + armor.getName() + ".";
    }
    public String unequipWeapon(){
        if(weapon == null) return "You don't have this weapon equipped";
        else{
            player.getFightStatsModule().removeAttack(weapon.getTypeValue());
            player.takeItem(weapon);
            weapon = null;
            return "You unequipped your Weapon";
        }
    }
    public String unequipArmor(){
        if(armor == null) return "You don't have this armor equipped";
        else{
            player.getFightStatsModule().removeDefence(armor.getTypeValue());
            player.takeItem(armor);
            armor = null;
            return "You Unequipped your armor";
        }
    }
}
