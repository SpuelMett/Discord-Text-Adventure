package CoreGame.Enemy;

import CoreGame.Character;

public class Enemy extends Character implements IEnemy, java.io.Serializable{

    private Breed breed;

    public Enemy(Breed breed){
        super(breed.getHealth(), breed.getAttack(), breed.getDefence());
        this.breed = breed;
    }


    public String getName() {
        return breed.getName();
    }
    public String getDescription() {
        return breed.getDescription();
    }
    public String getDeathSound(){
        return breed.getDeathSound();
    }
    public String getAttackSound(){
        return breed.getAttackSound();
    }
}
