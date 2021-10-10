package CoreGame.Enemy;

import CoreGame.*;

public class Enemy implements IEnemy, java.io.Serializable{

    private FightStats fightStats;
    private String name;
    private String description;

    public Enemy(String name, String description, int health, int attack, int defence){
        this.name = name;
        this.description = description;
        fightStats = new FightStats(health, attack, defence);
    }
    public Enemy(String name, String description, FightStats fightStats){
        this.description = description;
        this.name = name;
        this.fightStats = fightStats;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    public FightStats getFightStats() {
        return fightStats;
    }
}
