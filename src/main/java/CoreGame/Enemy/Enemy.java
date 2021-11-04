package CoreGame.Enemy;

import CoreGame.Player.FighStatsModule;
import CoreGame.Player.IFightStatsModule;

public class Enemy implements IEnemy, java.io.Serializable{

    private Breed breed;
    private IFightStatsModule fightStatsModule;

    public Enemy(Breed breed){
        fightStatsModule = new FighStatsModule(breed.getHealth(), breed.getAttack(), breed.getDefence());
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

    public IFightStatsModule getFightStatsModule(){
        return fightStatsModule;
    }
}
