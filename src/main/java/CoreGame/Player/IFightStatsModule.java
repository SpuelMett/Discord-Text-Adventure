package CoreGame.Player;

public interface IFightStatsModule {

    int getHealth();
    int getAttack();
    void addAttack(int value);
    void addDefence(int value);
    void removeAttack(int value);
    void removeDefence(int value);
    int attacked(int damage);
    int getDefence();

    boolean isDead();
}
