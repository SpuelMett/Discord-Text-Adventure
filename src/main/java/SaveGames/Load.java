package SaveGames;

import Game.GameFiles;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Load {

    public Load(){

    }

    /**
     * Tries to Load a SaveGameFile. If there is nothing create a new GameFile. Closes fileIn and in automatically.
     * @return
     */
    public GameFiles loadGameFiles(){
        GameFiles gameFiles;

        try(FileInputStream fileIn = new FileInputStream("SavedGameFiles/saveFile1.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);)
        {
            gameFiles = (GameFiles) in.readObject();
        }
        catch (IOException i){
            return new GameFiles();
        }
        catch (ClassNotFoundException c){
            return new GameFiles();
        }
        System.out.println("Loaded a saved GameFile.");
        return gameFiles;
    }
}
