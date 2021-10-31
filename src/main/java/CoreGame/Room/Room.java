package CoreGame.Room;

import CoreGame.Enemy.IEnemy;
import CoreGame.IDoor;
import CoreGame.INpc;
import CoreGame.Inventory.Inventory;
import CoreGame.Items.*;

import java.util.ArrayList;

public class Room implements java.io.Serializable{

    String name;
    String description;
    Inventory inventory;
    ArrayList<INpc> npcList;
    ArrayList<IEnemy> enemyList;
    ArrayList<IDoor> doorList;

    public Room(String name, String description){
        this.name = name;
        this.description = description;
        this.enemyList = new ArrayList<>();
        this.npcList = new ArrayList<>();
        this.doorList = new ArrayList<>();

        inventory = new Inventory();
    }
    public Room(String name, String description, ArrayList<IEnemy> enemyList, ArrayList<INpc> npcList, ArrayList<IDoor> doorList){
        this.name = name;
        this.description = description;
        this.enemyList = enemyList;
        this.npcList = npcList;
        this.doorList = doorList;

        inventory = new Inventory();
    }

    public String getName(){
        return name;
    }

    public void addDoor(IDoor newDoor){
        doorList.add(newDoor);
    }

    /**
     * Return the CoreGame.Door, that is located in that direction. If there is no Door return null.
     * @param direction
     * @return
     */
    public IDoor getDoor(String direction){
        int size = doorList.size();
        for(int i=0;i<size;i++){
            String possibleDirection = doorList.get(i).getDirection(this);
            if(possibleDirection.equals(direction)) return doorList.get(i);
        }
        return null;
    }

    public void addItem(IItem newItem){
        inventory.addItem(newItem);
    }
    public IItem removeItem(String itemName){
        return inventory.removeItem(itemName);
    }
    public void addNpc(INpc newNpc){
        npcList.add(newNpc);
    }
    public void removeNpc(INpc oldNpc){
        npcList.remove(oldNpc);
    }
    public INpc getNpc(String npcName){
        for (INpc npc: npcList){
            if(npc.getName().equals(npcName)) return npc;
        }
        return null;
    }
    public void addEnemy(IEnemy enemy){
        enemyList.add(enemy);
    }
    public IEnemy getEnemy(String name){
        for(IEnemy enemy:enemyList){
            if(enemy.getName().equals(name)) return enemy;
        }
        return null;
    }
    public boolean removeEnemy(IEnemy enemy){
        return enemyList.remove(enemy);
    }


    /**
     * Return the full Description of the Room. Includes description, items, enemies and npcs.
     * @return
     */
    public String getDescription(){
        StringBuilder sb = new StringBuilder();
        sb.append(description).append("\n");
        sb.append(directionDescription()).append("\n");
        sb.append(itemDescription()).append("\n");
        sb.append(enemyDescription()).append("\n");
        sb.append(npcDescription());

        return sb.toString();
    }

    private String itemDescription(){
        return inventory.getDescription();
    }
    private String npcDescription(){
        StringBuilder sb = new StringBuilder();
        sb.append("NPCs in the room: ");
        int size = npcList.size();
        for(int i=0;i<size;i++){
            if(i == size -1) sb.append(npcList.get(i).getName());
            else sb.append(npcList.get(i).getName()).append(", ");
        }
        return sb.toString();
    }
    private String enemyDescription(){
        StringBuilder sb = new StringBuilder();
        sb.append("Enemies: ");
        int size = enemyList.size();
        for(int i=0;i<size;i++){
            if(i == size -1) sb.append(enemyList.get(i).getName());
            else sb.append(enemyList.get(i).getName()).append(", ");
        }
        return sb.toString();
    }

    /*
    private String descriptionGenerator(String initialString, ArrayList<E> list){
        StringBuilder sb = new StringBuilder();
        sb.append(initialString);
        int size = list.size();
        for(int i=0;i<size;i++){
            if(i == size -1) sb.append(list.get(i).getName());
            else sb.append(enemyList.get(i).getName()).append(", ");
        }
    }
    */


    /**
     * returns all possible directions
     * @return
     */
    private String directionDescription(){
        StringBuilder sb = new StringBuilder();
        sb.append("Directions: ");

        int size = doorList.size();
        for(int i = 0;i<size;i++){
            if(i == size -1) sb.append(doorList.get(i).getDirection(this));
            else sb.append(doorList.get(i).getDirection(this)).append(", ");
        }
        return sb.toString();
    }
}
