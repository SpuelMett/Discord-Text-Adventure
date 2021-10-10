package SaveGames;

import Game.GameFiles;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Load {

    public Load(){

    }

    /**
     * Tries to Load a SaveGameFile. If there is nothing create a new GameFile
     * @return
     */
    public GameFiles loadGameFiles(){
        GameFiles gameFiles;

        try{
            FileInputStream fileIn = new FileInputStream("SavedGameFiles/saveFile2.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            gameFiles = (GameFiles) in.readObject();
            in.close();
            fileIn.close();
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
