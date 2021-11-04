package CoreGame.Enemy;

import CoreGame.Player.IFightStatsModule;

public interface IEnemy {
    String getName();
    String getDescription();
    String getDeathSound();
    String getAttackSound();

    IFightStatsModule getFightStatsModule();
}
