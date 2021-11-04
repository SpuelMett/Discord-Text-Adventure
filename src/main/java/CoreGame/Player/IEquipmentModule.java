package CoreGame.Player;

import CoreGame.Items.IItem;

public interface IEquipmentModule {

    String equip(IItem newEquipment);
    String unequip(String equipmentName);
    String getDescription();
}
