package AdventureControlling;


import Parsing.*;
import CoreGame.*;

public interface IAdventure {

    void startGame();
    boolean addPlayer(Player newPlayer);
    Parser getParser();
    Room getStartRoom();
}
