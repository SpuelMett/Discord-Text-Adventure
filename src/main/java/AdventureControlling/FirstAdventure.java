package AdventureControlling;

import CoreGame.Door;
import CoreGame.Enemy.Breed;
import CoreGame.Enemy.Enemy;
import CoreGame.Enemy.IEnemy;
import CoreGame.IDoor;
import CoreGame.Items.IItem;
import CoreGame.Items.Item;
import CoreGame.Npc.INpc;
import CoreGame.Npc.Npc;
import CoreGame.Player.Player;
import CoreGame.Room.Room;
import Parsing.Parser;

import java.util.ArrayList;

public class FirstAdventure implements IAdventure{

    Room valleyEntranceSouth, valleySouth, tavernBottom, tavernUpstairs, tavernUpstairsRoom1, tavernUpstairsRoom2;
    IDoor tavernBottom_tavernUpstairs;
    IItem apple, water, beer;
    IItem woodSword,ironSword, armor1;
    IEnemy dragon1;
    INpc npc1;

    //Server server;
    ArrayList<Player> playerList;

    Parser parser;


    //standard values
    Room startRoom;


    public FirstAdventure(){
        //Rooms
        valleyEntranceSouth = new Room("Valley Entrance", "You are standing in front of a small valley.");
        valleySouth = new Room("Room1", "This is the first Room.");
        tavernBottom = new Room("Room2", "This is the tavern of the village. There is a stair upwards.");
        tavernUpstairs = new Room("Room1", "This is ... Th");
        tavernUpstairsRoom1 = new Room("RoomNr", "This is ");
        tavernUpstairsRoom2 = new Room("RoomNr", "This is ");

        //Doors
        new Door(valleyEntranceSouth,valleySouth,"north","south");
        new Door(valleySouth,tavernBottom,"east","west");
        new Door(tavernBottom,tavernUpstairs,"upstairs","downstairs");
        new Door(tavernUpstairs,tavernUpstairsRoom1,"room 01","hallway");
        new Door(tavernUpstairs,tavernUpstairsRoom1,"room 02","hallway");

        //Items
        woodSword = new Item("WoodenSword","This is a normal wooden sword.",1,1,"weapon", 1);
        ironSword = new Item("IronSword","This is a normal iron sword.",1,1,"weapon", 2);

        apple = new Item("Apple", "A normal Apple", 1, 1, "food", 10);
        water = new Item("Water", "A bottle of water", 1, 1,"drink",  20);
        beer = new Item("Beer", "A mug of beer", 1, 1,"drink",  20);

        //place Items
        tavernUpstairsRoom1.addItem(woodSword);
        tavernUpstairsRoom2.addItem(ironSword);
        tavernUpstairsRoom2.addItem(apple);
        tavernBottom.addItem(water);

        //Enemies
        Breed troll = new Breed(null, "Troll", "A normal troll.", "The troll hit you with his big fist", "The troll falls backwards.",10, 5, 10);
        Breed trollArcher = new Breed(troll, "Troll Archer", "A troll with a bow.","The troll hit you with an arrow of his bow", null,  0, 7, 0);
        Breed dragon = new Breed(null, "Dragon", "A terrifying Dragon.", "The dragon hit you with his sharp claws", "The dragon died with a loud roar.", 10, 10, 1);

        IEnemy troll1 = new Enemy(troll);
        IEnemy dragon1 = new Enemy(dragon);

        valleySouth.addEnemy(dragon1);

        //NPCs
        npc1 = new Npc("Asmus","Normal NPC", "Did you know, that you can equip a weapon to fight with?");
        valleyEntranceSouth.addNpc(npc1);

        //standard values
        startRoom = valleyEntranceSouth;
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
