package ua.edu.knightandwarrior.model.units;

import lombok.extern.slf4j.Slf4j;
import ua.edu.knightandwarrior.model.weapons.Weapon;
import ua.edu.knightandwarrior.service.EventManager;
import ua.edu.knightandwarrior.service.EventType;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Warrior implements IWarrior {
    private static final int ATTACK=5;
    private int health ;
    private final int initialHealthByUnit;
    private final EventManager events;
    private final List<Weapon> weapons = new ArrayList<>();
    private static int numId;

    public Warrior() {
        this(50);
    }

    public Warrior(int health) {
        initialHealthByUnit = this.health = health;
        this.events = new EventManager(EventType.I_NEED_HEALTH);
        numId++;
    }

    @Override
    public int getInitialHealthByUnit() {
        return initialHealthByUnit;
    }

    @Override
    public EventManager getEvents() {
        return events;
    }


    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getAttack() {
        return Math.max(0,ATTACK+getAttackByWeapon());
    }


    public int getAttackByWeapon() {
        return getWeapons().stream().mapToInt(Weapon::getAttackPoints).sum();
    }

    private void setHealth(int health) {
        this.health = Math.min(getInitialHealth(),health);
    }

    @Override
    public void receiveDamageToUnit(int attack) {
        setHealth(getHealth() - attack);
    }

    @Override
    public void healBy(int healPoints) {
        setHealth(getHealth() + healPoints);
        log.atDebug().log(" heal unit "+this+" to "+healPoints+" health points");
    }

    @Override
    public List<Weapon> getWeapons() {
        return weapons;
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        weapons.add(weapon);
        setHealth(getHealth()+weapon.getHealthPoints());
    }

    @Override
    public String toString() {
        StringBuilder weaponString = new StringBuilder();
        getWeapons().forEach(weaponString::append);
        String weaponsWarrior = getWeapons().isEmpty()?"":" weapons "+weaponString;
        return getClass().getSimpleName() +
                " #"+numId+" "+
                getHealth()+"hp " +
                weaponsWarrior;
    }
}
