package SaveGames;

import Game.GameFiles;

//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Save {

    public Save(){

    }

    public boolean saveGameFile(GameFiles gameFile) {
        try
        {
            FileOutputStream fileOut =new FileOutputStream("SavedGameFiles/saveFile1.ser"); //Dateiname und Pfad
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(gameFile);
            out.close();
            fileOut.close();
        }
        catch (IOException i) {
            i.printStackTrace();
            return false;
        }
        System.out.println("Saved a GameFile.");
        return true;
    }
}
