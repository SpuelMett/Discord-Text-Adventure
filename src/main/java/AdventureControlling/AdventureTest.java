package AdventureControlling;

import CoreGame.*;
import CoreGame.Items.*;
import CoreGame.Enemy.*;
import CoreGame.Npc.INpc;
import CoreGame.Npc.Npc;
import CoreGame.Room.Room;
import Parsing.*;


import java.util.ArrayList;


public class AdventureTest implements IAdventure, java.io.Serializable{

    Room room1,room2;
    IDoor door12;
    IItem food1, drink1;
    IItem woodSword,ironSword, armor1;
    IEnemy dragon1;
    INpc npc1;

    //Server server;
    ArrayList<Player> playerList;

    Parser parser;


    //standard values
    Room startRoom;


    public AdventureTest(){
        room1 = new Room("Room1", "This is the first Room.");
        room2 = new Room("Room2", "This is the second Room.");

        door12 = new Door(room1,room2,"north","south");

        woodSword = new Item("WoodenSword","This is a normal wooden sword.",1,1,"weapon", 1);
        ironSword = new Item("IronSword","This is a normal iron sword.",1,1,"weapon", 2);

        food1 = new Item("Apple", "A normal Apple", 1, 1, "food", 10);
        drink1 = new Item("Water", "A bottle of water", 1, 1,"drink",  20);

        //place Items
        room1.addItem(woodSword);
        room2.addItem(ironSword);
        room1.addItem(food1);
        room2.addItem(drink1);

        //Enemies
        Breed troll = new Breed(null, "Troll", "A normal troll.", "The troll hit you with his big fist", "The troll falls backwards.",10, 5, 10);
        Breed trollArcher = new Breed(troll, "Troll Archer", "A troll with a bow.","The troll hit you with an arrow of his bow", null,  0, 7, 0);
        Breed dragon = new Breed(null, "Dragon", "A terrifying Dragon.", "The dragon hit you with his sharp claws", "The dragon died.", 10, 10, 1);

        IEnemy troll1 = new Enemy(troll);
        IEnemy dragon1 = new Enemy(dragon);

        room1.addEnemy(dragon1);

        //NPCs
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
