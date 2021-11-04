package IOHandling;


import AdventureControlling.CommandHandler;
import CoreGame.Player.Player;
import Parsing.*;
import Game.*;

import SaveGames.*;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;


public class BasicInputHandling {

    private GameFiles gameFile;

    public BasicInputHandling(){
        Load load = new Load();
        gameFile = load.loadGameFiles();

        //gameFile = new GameFiles();
    }


    /**
     * Handles the input Message. Checks server and Player and redirects command to the command handler. Returns the answer.
     * @param msg
     * @return
     */
    public String handleInput(Message msg){

        //get Server
        Guild guild = msg.getGuild();

        //get the Game Object of the Server
        Game currentGame = gameFile.getGame(guild);

        if(currentGame == null){
            //create new Game
            gameFile.addGame(guild);
            return "There was no Game running on this server. I created a new Game for you.";
        }
        else{
            //
            Player player;

            //if the Player is not on the Server create a new Player Object
            if(!isPlayerOnServer(msg, currentGame)){
                createPlayerOnServer(msg, currentGame);
                return "You seem new to this world. I created some stats for you and gave you basic gear.";
            }

            //get the player Object
            player = getPlayer(msg, currentGame);

            //check if the player is dead. If yes create a new Player
            if(isPlayerDead(player)){
                createPlayerOnServer(msg, currentGame);
                return "You seem new to world. I created some stats for you and gave you basic gear.";
            }

            //create Command and remove !! from input
            Parser parser = new Parser();
            String playerInput = msg.getContentRaw().substring(2);
            Command command = parser.createCommand(playerInput);

            //run the command and return Answer after a Save
            CommandHandler commandHandler = new CommandHandler(player, command);
            String result = commandHandler.processCommand();

            //Save the GameFile after each user Command with Guild and Player objects
            Save save= new Save();
            if(!save.saveGameFile(gameFile)) System.out.println("GameFile could not be saved!");

            return result;
        }
    }


    /**
     * Checks if the author is a Player on the Server.
     * @param msg
     * @param currentGame
     * @return
     */
    private boolean isPlayerOnServer(Message msg, Game currentGame){
        int author = msg.getAuthor().hashCode();
        Player player = currentGame.isPlayer(author);
        return player!=null;
    }

    /**
     * Checks if the Player is Dead
     * @param player
     * @return
     */
    private boolean isPlayerDead(Player player){
        return  player.checkDead();
    }
    private Player getPlayer(Message msg, Game currentGame){
        int author = msg.getAuthor().hashCode();
        return currentGame.isPlayer(author);
    }

    /**
     * create a new Player on the Server
     * @param msg
     * @param currentGame
     * @return
     */
    private Player createPlayerOnServer(Message msg, Game currentGame){
        int author = msg.getAuthor().hashCode();
        return currentGame.addPlayer(author);
    }
}
