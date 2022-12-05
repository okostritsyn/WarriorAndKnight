package ua.edu.knightandwarrior.model.units;

import ua.edu.knightandwarrior.model.weapons.Weapon;
import ua.edu.knightandwarrior.service.EventManager;
import ua.edu.knightandwarrior.service.EventType;

import java.util.List;

public interface IWarrior {
    int ZERO = 0;

    default boolean isAlive() {
        return getHealth()>getZeroHealth();
    }

    private int getZeroHealth() { return  ZERO; }

    int getHealth();

    int getAttack();

    default void receiveDamage(int attack) {
        receiveDamageToUnit(attack);
    }

    void receiveDamageToUnit(int attack);

    void healBy(int healPoints);

    EventManager getEvents();

    default void killUnit(){
        healBy(-1*getHealth());
    }

    int getInitialHealthByUnit();

    default int getInitialHealth() {
        return Math.max(0,getInitialHealthByUnit() + getHealthByWeapon());
    }

    List<Weapon> getWeapons();

    void equipWeapon(Weapon weapon);

    private int getHealthByWeapon() {
        return getWeapons().stream().mapToInt(Weapon::getHealthPoints).sum();
    }

    default void attack(IWarrior warrior) {
        if (getInitialHealth()>getHealth()){
            getEvents().notify(EventType.I_NEED_HEALTH,this);
        }
        warrior.receiveDamage(getAttack());
    }
}
