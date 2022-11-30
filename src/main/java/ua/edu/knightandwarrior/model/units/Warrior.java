package ua.edu.knightandwarrior.model.units;

import ua.edu.knightandwarrior.model.weapons.Weapon;
import ua.edu.knightandwarrior.service.EventManager;
import ua.edu.knightandwarrior.service.EventType;

import java.util.ArrayList;
import java.util.List;

public class Warrior implements IWarrior {
    private static final int ATTACK=5;
    private static final int ZERO_HEALTH=0;
    private int health ;
    private final int initialHealth;
    private final EventManager events;
    private List<Weapon> weapons = new ArrayList<>();
    private int attackByWeapon;
    private int healthByWeapon;

    public Warrior() {
        this(50);
    }

    public Warrior(int health) {
        initialHealth = this.health = health;
        this.events = new EventManager(EventType.I_NEED_HEALTH,EventType.I_DIE);
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
    public int getZeroHealth() {
        return ZERO_HEALTH;
    }

    @Override
    public int getAttack() {
        return Math.max(0,ATTACK+getAttackByWeapon());
    }

    private int getInitialHealth() {
        return Math.max(0,initialHealth + getHealthByWeapon());
    }

    public int getAttackByWeapon() {
        return attackByWeapon;
    }

    private int getHealthByWeapon() {
        return healthByWeapon;
    }

    private void setAttackByWeapon(int attack) {
        this.attackByWeapon = attack;
    }

    private void setHealthByWeapon(int health) {
        this.healthByWeapon = getHealthByWeapon() + health;
    }

    private void setHealth(int health) {
        this.health = Math.min(getInitialHealth(),health);
        if (getHealth() <= 0){
            events.notify(EventType.I_DIE,this);
        }
    }

    @Override
    public void attack(IWarrior warrior) {
        if (getInitialHealth()>getHealth()){
            events.notify(EventType.I_NEED_HEALTH,this);
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
    public void equipWeapon(Weapon weapon) {
        if (getInitialHealth() != getHealth() || getInitialHealth() == 0) {
            return;
        }
        weapons.add(weapon);

        var healthPoints = weapon.getHealthPoints();
        setHealthByWeapon(getHealthByWeapon()+healthPoints);
        setHealth(getHealth()+healthPoints);
        setAttackByWeapon(getAttackByWeapon()+weapon.getAttackPoints());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " health " + getHealth();
    }
}
