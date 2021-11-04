package CoreGame.Enemy;

public class Breed implements java.io.Serializable {

    private final String name;
    private String description;
    private String attackSound;
    private String deathSound;
    private int health;
    private int attack;
    private int defence;

    public Breed(Breed parent, String name, String description, String attackSound, String deathSound, int health, int attack, int defence){
        if(parent != null){
            if(description == null) this.description = parent.getDescription();
                else this.description = description;
            if(attackSound == null) this.attackSound = parent.getAttackSound();
                else this.attackSound = attackSound;
            if(deathSound == null) this.deathSound = parent.getDeathSound();
                else this.deathSound = deathSound;
            if(health == 0) this.health = parent.getHealth();
                else this.health = health;
            if(attack == 0) this.attack = parent.getAttack();
                else this.attack = attack;
            if(defence == 0) this.defence = parent.getDefence();
                else this.defence = defence;
        }
        else {
            this.health = health;
            this.attack = attack;
            this.defence = defence;
            this.description = description;
            this.attackSound = attackSound;
            this.deathSound = deathSound;
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getAttackSound(){
        return attackSound;
    }
    public String getDeathSound(){
        return deathSound;
    }

    public int getHealth(){
        return health;
    }
    public int getAttack(){
        return attack;
    }
    public int getDefence(){
        return defence;
    }
}
