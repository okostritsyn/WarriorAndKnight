package ua.edu.knightandwarrior.model.units;

import ua.edu.knightandwarrior.model.weapons.Weapon;
import ua.edu.knightandwarrior.service.EventManager;
import ua.edu.knightandwarrior.service.EventType;

import java.util.ArrayList;
import java.util.List;

public class Warrior implements IWarrior {
    private static final int ATTACK=5;
    private int health ;
    private final int initialHealth;
    private final EventManager events;
    private final List<Weapon> weapons = new ArrayList<>();

    public Warrior() {
        this(50);
    }

    public Warrior(int health) {
        initialHealth = this.health = health;
        this.events = new EventManager(EventType.I_NEED_HEALTH);
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

    private int getInitialHealth() {
        return Math.max(0,initialHealth + getHealthByWeapon());
    }

    public int getAttackByWeapon() {
        return getWeapons().stream().mapToInt(Weapon::getAttackPoints).sum();
    }

    private int getHealthByWeapon() {
        return getWeapons().stream().mapToInt(Weapon::getHealthPoints).sum();
    }

    private void setHealth(int health) {
        this.health = Math.min(getInitialHealth(),health);
    }

    @Override
    public void attack(IWarrior warrior) {
        if (getInitialHealth()>getHealth()){
            getEvents().notify(EventType.I_NEED_HEALTH,this);
        }
        warrior.receiveDamage(getAttack());
    }

    public void receiveDamage(int attack) {
        setHealth(getHealth() - attack);
    }

    public void healBy(int healPoints) {
        setHealth(getHealth() + healPoints);
    }

    @Override
    public List<Weapon> getWeapons() {
        return weapons;
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        if (getInitialHealth() != getHealth() || getInitialHealth() == 0) {
            return;
        }
        weapons.add(weapon);
        setHealth(getHealth()+getHealthByWeapon());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " health " + getHealth();
    }
}
