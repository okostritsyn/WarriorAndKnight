package ua.edu.knightandwarrior.model.units;

public interface IWarrior {
    default boolean isAlive() {
        return getHealth()>0;
    }

    int getHealth();

    void attack(IWarrior warrior);

    void receiveDamage(int attack);

    void healBy(int healPoints);

}
