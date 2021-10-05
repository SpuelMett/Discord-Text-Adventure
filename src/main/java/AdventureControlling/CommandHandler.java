package AdventureControlling;

import CoreGame.Enemy.*;
import CoreGame.*;
import CoreGame.Items.*;
import Parsing.*;

public class CommandHandler {

    private Player currentPlayer;
    private Command command;

    public CommandHandler(Player player, Command command){
        this.command = command;
        this.currentPlayer = player;
    }

    /**
     * redirects the process to the right method.
     * @return
     */
    public String processCommand(){
        if(command == null){
            return "I don't know what you want.";
        }
        String commandString = command.getCommand();
        if(commandString.equals("go")) return go();
        if(commandString.equals("pick")) return pick();
        if(commandString.equals("drop")) return drop();
        if(commandString.equals("info")) return generalInfo();
        if(commandString.equals("help")) return help();
        if(commandString.equals("eat")) return eat();
        if(commandString.equals("drink")) return drink();
        if(commandString.equals("secretCommand")) return secretCommand();
        if(commandString.equals("speak")) return speak();
        if(commandString.equals("attack")) return attack();
        if(commandString.equals("equip")) return equip();
        if(commandString.equals("unequip")) return unequip();

        return "I don't know what you want.";
    }

    /**
     * processes the Command go.
     * @return
     */
    private String go(){
        String result = "";

        //Check if command has second word
        if(!command.hasSecondWord()) return "Where do you want to go?";

        //get the next room
        Room currentRoom = currentPlayer.getRoom();
        IDoor door = currentRoom.getDoor(command.getSecondWord());

        //if there is no Room
        if(door == null){
            return "There is nothing in this direction.";
        }

        //check if the door is locked. If yes check if the player has the key to open it
        if(door.isLocked()){
            if(currentPlayer.hasItem(door.getKey())){
                door.unlock();
                result += "You open the Door with " + door.getKey().getName();
            }
            else {
                return "The door is locked.";
            }
        }

        //change room in player object
        Room newRoom = door.open(currentRoom);

        //If there is a new Room change the player Room and paste the Room description
        if(newRoom != null){
            currentPlayer.changeRoom(newRoom);
            result += "\n" + newRoom.getDescription();

            //remove saturation
            currentPlayer.removeSaturation(1);
            return result;
        }
        //If there is no new room
        else{
            result += "\n" + "There is no Room in this direction.";
            return result;
        }
    }

    /**
     * let the player pick up an Item from the Room
     * @return
     */
    private String pick(){
        //no item specified:
        if(!command.hasSecondWord()) return "What do you want do pick up?";

        //get the Room
        Room currentRoom = currentPlayer.getRoom();
        String itemName = command.getSecondWord();

        IItem item = currentRoom.removeItem(itemName);
        if(item == null) return "There is nothing like that in this room.";

        //add item to player inventory
        if(currentPlayer.takeItem(item)) return "You picked up the Item.";
        else return "You were not able to pick this up.";
    }
    private String drop(){
        //no item specified:
        if(!command.hasSecondWord()) return "What do you want to drop?";

        //remove the item from the player
        String itemName = command.getSecondWord();
        IItem item = currentPlayer.dropItem(itemName);

        if(item == null) return "You don't have this in your Inventory.";

        //add Item to the Room
        Room currentRoom = currentPlayer.getRoom();
        currentRoom.addItem(item);
        return "You drop the Item on the floor.";
    }
    private String roomInfo(){
        Room currentRoom = currentPlayer.getRoom();
        return currentRoom.getDescription();
    }
    private String playerInfo(){
        return currentPlayer.getDescription();
    }
    private String generalInfo(){
        if(command.hasSecondWord()){
            String secondWord = command.getSecondWord().toLowerCase();
            if(secondWord.equals("player")) return playerInfo();
            if(secondWord.equals("stats")) return playerInfo();
            if(secondWord.equals("room")) return roomInfo();
            return "What information do you want?";
        }
        else{
            return roomInfo() + "\n" + "\n" + playerInfo();
        }

    }

    private String help(){
        if(!command.hasSecondWord()){
            return "You found a Book in your bag that looks like it could help you. " +
                    "There are pages about commands and other helpful things in there. " +
                    "Type help and the page name to read a page.";
        }
        String helpString = command.getSecondWord();

        if(helpString.equals("commands")){
            return "Try one of these possible commands: " + CommandChanger.returnAllCommands();
        }
        return "Sorry, but there is nothing like this in the helping book.";
    }

    private String eat(){
        if(!command.hasSecondWord()){
            return "What do you want to eat?";
        }
        String itemName = command.getSecondWord();
        IItem food = currentPlayer.getItem(itemName);
        if(food == null) return "You don't have this Item in your bag.";
        if(!food.getClass().getName().equals(ItemFood.class.getName())) return "You can't eat this.";


        ItemFood itemFood = (ItemFood) food;

        int hunger = itemFood.getHunger();

        currentPlayer.addSaturation(hunger);
        currentPlayer.dropItem(itemName);

        return "You eat " + itemName + " and restored " + hunger + " saturation.";
    }

    private String drink(){
        if(!command.hasSecondWord()){
            return "What do you want to drink?";
        }
        String itemName = command.getSecondWord();
        IItem drink = currentPlayer.getItem(itemName);
        if(drink == null) return "You don't have this Item in your bag.";
        if(!drink.getClass().getName().equals(ItemDrink.class.getName())) return "You can't drink this.";

        ItemDrink itemDrink = (ItemDrink) drink;

        int thirst = itemDrink.getThirst();

        currentPlayer.addHydration(thirst);
        currentPlayer.dropItem(itemName);

        return "You drank " + itemName + " and restored " + thirst + " thirst.";
    }

    private String secretCommand(){
        return "You found Albert the unfindable Crab. Now time will collapse.";
    }

    private String speak(){
        if(!command.hasSecondWord()) return "Who do you want to speak to?";
        String npcName = command.getSecondWord();

        Room currentRoom = currentPlayer.getRoom();
        INpc npc = currentRoom.getNpc(npcName);

        if(npc == null) return "There is nobody named like this here.";
        return npc.getAnswer();
    }

    private String attack(){
        if(!command.hasSecondWord()) return "Who do you want to attack?";
        Room currentRoom = currentPlayer.getRoom();
        String npcName = command.getSecondWord();
        IEnemy enemy = currentRoom.getEnemy(npcName);

        if(enemy == null) return "There is nothing like this here.";

        //Change fix values
        currentPlayer.removeSaturation(1);

        //get fight controller
        FightStats playerFightStats = currentPlayer.getFightStats();
        FightStats enemyFightStats = enemy.getFightStats();

        StringBuilder sb = new StringBuilder();
        sb.append("You attacked ").append(npcName).append(" and made ");

        int attackValue = playerFightStats.getAttack();
        int damage = enemyFightStats.attacked(attackValue);

        sb.append(damage).append(" damage.").append("\n");

        //Check if enemy is dead
        if(enemyFightStats.isDead()){
            sb.append("You killed ").append(npcName).append(".");
            currentRoom.removeEnemy(enemy);
            return sb.toString();
        }

        //Attack from Enemy
        attackValue = enemyFightStats.getAttack();
        damage = playerFightStats.attacked(attackValue);
        sb.append("The ").append(npcName).append(" attacked you and made ").append(damage).append(" damage.");

        //Check if Player is Dead
        if(playerFightStats.isDead()){
            sb.append("The ").append(npcName).append(" killed you.");
        }

        return sb.toString();
    }

    private String equip(){
        if(!command.hasSecondWord()) return "What do you want to equip?";

        String itemName = command.getSecondWord();
        IItem item = currentPlayer.getItem(itemName);

        if(item == null) return "You don't seem to have this Item.";

        //check if Player can equip the item
        return currentPlayer.equip(item);


    }

    private String unequip(){
        if(!command.hasSecondWord()) return "What do you want to deequip?";

        String itemName = command.getSecondWord();
        IItem item = currentPlayer.getItem(itemName);

        return currentPlayer.unequip(item);
    }

}
