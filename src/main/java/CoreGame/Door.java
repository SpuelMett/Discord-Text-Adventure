package CoreGame;

import CoreGame.Items.*;
import CoreGame.Room.Room;

public class Door implements IDoor, java.io.Serializable{

    Room room1;
    Room room2;
    String direction1;
    String direction2;
    IItem key;
    boolean locked;

    public Door(Room room1, Room room2, String direction1, String direction2){
        this.room1 = room1;
        this.room2 = room2;
        this.direction1 = direction1;
        this.direction2 = direction2;

        setDoors();

    }
    public Door(Room room1, Room room2, String direction1, String direction2, IItem key){
        this.room1 = room1;
        this.room2 = room2;
        this.direction1 = direction1;
        this.direction2 = direction2;

        this.key = key;
        locked = true;

        setDoors();
    }
    private void setDoors(){
        //set Doors in Rooms
        room1.addDoor(this);
        room2.addDoor(this);
    }


    public boolean lock(IItem key) {
        if(isKey(key)){
            locked = true;
            return true;
        }
        return false;
    }
    public boolean unlock(IItem key){
        if(isKey(key)){
            locked = false;
            return true;
        }
        return false;
    }
    public void unlock(){
        locked = false;
    }
    public boolean isLocked(){
        return locked;
    }
    public IItem getKey(){
        return key;
    }

    private boolean isKey(IItem testKey){
        return (key.equals(testKey) && !testKey.equals(null));
    }

    /**
     * Return the opposite Room of the current one.
     * @param currentRoom
     * @return
     */
    public Room open(Room currentRoom){
        if(locked) return null;

        if(currentRoom.equals(room1)) return room2;
        else if (currentRoom.equals(room2)) return  room1;
        else return null;
    }

    public String getDirection(Room currentRoom){
        if(currentRoom.equals(room1)) return direction1;
        else if (currentRoom.equals(room2)) return  direction2;
        else return null;
    }

}
