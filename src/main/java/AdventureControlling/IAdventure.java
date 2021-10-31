package AdventureControlling;


import CoreGame.Room.Room;
import Parsing.*;
import CoreGame.*;

public interface IAdventure {

    void startGame();
    boolean addPlayer(Player newPlayer);
    Parser getParser();
    Room getStartRoom();
}
