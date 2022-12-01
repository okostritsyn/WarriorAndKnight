package ua.edu.knightandwarrior.model.units;

import ua.edu.knightandwarrior.model.weapons.Weapon;
import ua.edu.knightandwarrior.service.EventManager;

import java.util.List;

public interface IWarrior {
    default boolean isAlive() {
        return getHealth()>getZeroHealth();
    }

    int getZeroHealth();

    int getHealth();

    void attack(IWarrior warrior);

    int getAttack();

    void receiveDamage(int attack);

    void healBy(int healPoints);

    List<Weapon> getWeapons();

    void equipWeapon(Weapon weapon);

    EventManager getEvents();

    default void killUnit(){
        healBy(-1*getHealth());
    }
}
