package CoreGame.Enemy;

import CoreGame.*;

public interface IEnemy {
    String getName();
    String getDescription();
    String getDeathSound();
    String getAttackSound();

    int getAttack();
    //FightStats getFightStats();
    int attacked(int damage);
    boolean isDead();
}
