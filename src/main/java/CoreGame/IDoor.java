package CoreGame;

import CoreGame.Items.*;
import CoreGame.Room.Room;

public interface IDoor {
    boolean lock(IItem key);
    boolean unlock(IItem key);
    void unlock();
    Room open(Room room);
    //CoreGame.IDoor getDoor(String direction);
    String getDirection(Room currentRoom);
    boolean isLocked();
    IItem getKey();
}
