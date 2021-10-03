package Game;

import AdventureControlling.*;
import Game.*;
import net.dv8tion.jda.api.entities.Guild;

import java.util.ArrayList;
import java.util.HashMap;

public class GameFiles implements java.io.Serializable{

    //
    private HashMap<String, Game> gameList;

    public GameFiles(){
        gameList = new HashMap<>();
    }

    /**
     * Adds the standard Game to the List
     * @param server
     */
    public void addGame(Guild server){
        String serverId = server.getId();


        Game newGame = new Game(serverId);
        addGame(serverId,newGame);
    }
    public void addGame(String serverID, Game game){
        gameList.put(serverID, game);
    }

    public Game getGame(Guild guild){
        String serverId = guild.getId();
        return gameList.get(serverId);
    }


}
