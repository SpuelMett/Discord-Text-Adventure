package AdventureControlling;


import CoreGame.Player.Player;
import CoreGame.Room.Room;
import Parsing.*;

public interface IAdventure {

    void startGame();
    boolean addPlayer(Player newPlayer);
    Parser getParser();
    Room getStartRoom();
}
