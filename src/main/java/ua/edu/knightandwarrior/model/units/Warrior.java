package ua.edu.knightandwarrior.model.units;

import ua.edu.knightandwarrior.service.EventManager;

public class Warrior implements IWarrior {
    private static final int ATTACK=5;
    private int health ;
    private final int initialHealth;
    private EventManager events;

    public Warrior() {
        this(50);
    }

    public Warrior(int health) {
        initialHealth = this.health = health;
        this.events = new EventManager("INeedHealth");
    }

    public EventManager getEvents() {
        return events;
    }


    @Override
    public int getAttack() {
        return ATTACK;
    }

    public int getHealth() {
        return health;
    }

    private void setHealth(int health) {
        this.health = Math.min(initialHealth,health);
    }

    public void attack(IWarrior warrior) {
        warrior.receiveDamage(getAttack());
    }

    public void receiveDamage(int attack) {
        setHealth(getHealth() - attack);
        events.notify("INeedHealth",this);
    }

    public void healBy(int healPoints) {
        setHealth(getHealth() + healPoints);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " health " + getHealth();
    }
}
