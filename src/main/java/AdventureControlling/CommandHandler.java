package AdventureControlling;

import CoreGame.Enemy.*;
import CoreGame.*;
import CoreGame.Items.*;
import CoreGame.Npc.INpc;
import CoreGame.Player.Player;
import CoreGame.Room.Room;
import CoreGame.Trader.Trader;
import Parsing.*;

public class CommandHandler {

    private final Player currentPlayer;
    private final Command command;

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
        if(commandString.equals("die")) return die();
        if(commandString.equals("read")) return read();
        if(commandString.equals("trade")) return trade();

        return "I don't know what you want.";
    }

    /**
     * processes the Command go.
     * @return
     */
    private String go(){
        //Check if command has second word
        if(!command.hasSecondWord()) return "Where do you want to go?";
        //get the next room
        Room currentRoom = currentPlayer.getRoom();
        //Changing the room is handled in the Room class
        return currentRoom.changeRoom(currentPlayer, command.getSecondWord());
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
        if(!food.getType().equals("food")) return "You can't eat this.";


        int hunger = food.getTypeValue();

        currentPlayer.addSatiation(hunger);
        currentPlayer.dropItem(itemName);

        return "You eat " + itemName + " and restored " + hunger + " satiation.";
    }

    private String drink(){
        if(!command.hasSecondWord()){
            return "What do you want to drink?";
        }
        String itemName = command.getSecondWord();
        IItem drink = currentPlayer.getItem(itemName);
        if(drink == null) return "You don't have this Item in your bag.";
        if(!drink.getType().equals("drink")) return "You can't drink this.";

        int thirst = drink.getTypeValue();

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
        currentPlayer.removeSatiation(1);

        //start result String
        StringBuilder sb = new StringBuilder();
        sb.append("You attacked the ").append(enemy.getName()).append(" and made ");

        //Player attacks
        int attackValue = currentPlayer.getFightStatsModule().getAttack();
        int damage = enemy.getFightStatsModule().attacked(attackValue);
        sb.append(damage).append(" damage.").append("\n");

        //Check if enemy is dead
        if(enemy.getFightStatsModule().isDead()){
            sb.append("You killed the ").append(enemy.getName()).append(". ");
            sb.append(enemy.getDeathSound());
            currentRoom.removeEnemy(enemy);
            return sb.toString();
        }

        //Attack from Enemy
        attackValue = enemy.getFightStatsModule().getAttack();
        damage = currentPlayer.getFightStatsModule().attacked(attackValue);
        sb.append(enemy.getAttackSound()).append(" and made ").append(damage).append(" damage.");

        //Check if Player is Dead
        if(currentPlayer.getFightStatsModule().isDead()){
            sb.append("The ").append(enemy.getName()).append(" killed you.");
        }

        return sb.toString();
    }

    private String equip(){
        if(!command.hasSecondWord()) return "What do you want to equip?";

        String itemName = command.getSecondWord();
        IItem item = currentPlayer.getItem(itemName);

        if(item == null) return "You don't seem to have this Item.";

        //check if Player can equip the item
        return currentPlayer.getEquipmentModule().equip(item);


    }

    private String unequip(){
        if(!command.hasSecondWord()) return "What do you want to unequip?";

        String itemName = command.getSecondWord();
        //IItem item = currentPlayer.getItem(itemName);

        return currentPlayer.getEquipmentModule().unequip(itemName);
    }

    private String die(){
        currentPlayer.removeHydration(200);
        return "You died.";
    }

    private String read(){
        if(!command.hasSecondWord()) return "What do you want to read?";
        String itemName = command.getSecondWord();

        IItem book = currentPlayer.getItem(itemName);
        if(book == null) return "You don't have this book";
        if(!book.getType().equals("book")) return "You cant read this.";
        return book.getDescription();

    }

    private String trade(){
        if(!command.hasSecondWord()) return "With which person do you want to trade?";

        Room room = currentPlayer.getRoom();
        String traderName = command.getSecondWord();
        Trader trader = room.getTrader(traderName);
        if(trader == null){
            return "You cant find a trader with this name here.";
        }

        String itemName = command.getThirdWord();
        if(itemName == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Here is a list of items you can buy. You can also sell something.");
            sb.append("\n");
            sb.append(trader.getItemListDescription());
            return sb.toString();
        }

        //Check if player has this item
        if(currentPlayer.hasItem(itemName)){
            return currentPlayer.sellItem(itemName, trader);
        }
        else{
            return trader.sell(itemName, currentPlayer);
        }

    }

}
