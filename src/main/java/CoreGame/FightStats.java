package CoreGame;

public class FightStats implements java.io.Serializable{

    int health;
    int maxHealth;

    int attack;
    int defense;

    public FightStats(int health, int attack, int defense){
        this.health = health;
        this.maxHealth = health;
        this.attack = attack;
        this.defense = defense;
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

    /**
     * Return false, if the controller dies
     * @param damage
     * @return
     */
    public int attacked(int damage){
        damage = defense - damage;
        health -= damage;
        return damage;
    }

    public int getDefense() {
        return defense;
    }
    public void removeDefense(int value){
        defense -= value;
    }

    public boolean isDead(){
        return health == 0;
    }
}
