package AdventureControlling;

import CoreGame.*;
import CoreGame.Items.*;
import CoreGame.Enemy.*;
import Parsing.*;


import java.util.ArrayList;


public class AdventureTest implements IAdventure, java.io.Serializable{

    Room room1,room2;
    IDoor door12;
    IItem food1, drink1;
    ItemWeapon woodSword,ironSword;
    ItemArmor armor1;
    IEnemy dragon1;
    INpc npc1;

    //Server server;
    ArrayList<Player> playerList;

    Parser parser;
    CommandHandler commandHandler;

    //standard values
    Room startRoom;


    public AdventureTest(){
        room1 = new Room("Room1", "This is the first Room.");
        room2 = new Room("Room2", "This is the second Room.");

        door12 = new Door(room1,room2,"north","south");

        woodSword = new ItemWeapon("WoodenSword","This is a normal wooden sword.",1,1,1);
        ironSword = new ItemWeapon("IronSword","This is a normal iron sword.",1,1,2);

        food1 = new ItemFood("Apple", "A normal Apple", 1, 1, 10);
        drink1 = new ItemDrink("Water", "A bottle of water", 1, 1, 20);

        //place Items
        room1.addItem(woodSword);
        room2.addItem(ironSword);
        room1.addItem(food1);
        room2.addItem(drink1);

        //Enemies
        FightStats dragonFightStats = new FightStats(10, 11, 5);
        dragon1 = new Enemy("Dragon", "This is a terrifying dragon.", dragonFightStats);
        room1.addEnemy(dragon1);

        //Enemies
        npc1 = new Npc("Asmus","Normal NPC", "Did you know, that you can equip a weapon to fight with?");
        room2.addNpc(npc1);

        //standard values
        startRoom = room1;
    }

    @Override
    public void startGame() {

    }

    @Override
    public boolean addPlayer(Player newPlayer) {
        return playerList.add(newPlayer);
    }

    public Parser getParser(){
        return parser;
    }

    public Room getStartRoom(){
        return startRoom;
    }
}
