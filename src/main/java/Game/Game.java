package Game;

import AdventureControlling.AdventureTest;
import AdventureControlling.*;
import CoreGame.*;
import CoreGame.Room.Room;

import java.util.HashMap;

public class Game implements java.io.Serializable{

    private String guildID;
    //private CommandHandler commandHandler;
    private IAdventure adventure;
    private HashMap<Integer, Player> playerList;

    public Game(String guildID){
        this.guildID = guildID;
        this.adventure = new AdventureTest();
        this.playerList = new HashMap<>();
    }

    public Game(String guildID, String adventureName){
        this.guildID = guildID;
        //this.commandHandler = new CommandHandler();
        this.adventure = new AdventureTest();
    }

    public String getGuild(){
        return guildID;
    }

    /**
     * Checks if a Player is in the Game and returns the player if it exists
     * @param playerID
     * @return
     */
    public Player isPlayer(int playerID){
        return playerList.get(playerID);
    }

    /**
     * Create a new standard Player. Should override old player
     * @param playerID
     * @return
     */
    public Player addPlayer(int playerID){
        //create standard Player
        Room startRoom = adventure.getStartRoom();
        Player newPlayer = new Player(startRoom);

        //put player and id in the List
        playerList.put(playerID, newPlayer);
        return newPlayer;
    }

//
//    public CommandHandler getCommandHandler(){
//        return commandHandler;
//    }
}
