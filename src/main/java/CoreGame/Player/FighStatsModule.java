package CoreGame.Player;

import CoreGame.Player.IFightStatsModule;

public class FighStatsModule implements IFightStatsModule, java.io.Serializable{
    int health;
    int maxHealth;

    int attack;
    int defence;

    public FighStatsModule(int health, int attack, int defense){
        this.health = health;
        this.maxHealth = health;
        this.attack = attack;
        this.defence = defense;
    }

    public int getHealth(){
        return health;
    }

    public int getAttack() {
        return attack;
    }
    public void removeAttack(int value){
        attack -= value;
    }
    public void addAttack(int value){
        attack += value;
    }
    public void addDefence(int value) {
        defence += value;
    }
    public void removeDefence(int value){
        defence -= value;
    }

    /**
     * Return false, if the controller dies
     * @param damage
     * @return
     */
    public int attacked(int damage){
        int hit = damage - defence;
        if(hit < 0) hit = 0;
        health -= hit;
        return hit;
    }

    public int getDefence() {
        return defence;
    }
    public void removeDefense(int value){
        defence -= value;
    }

    public boolean isDead(){
        return health <= 0;
    }
}
