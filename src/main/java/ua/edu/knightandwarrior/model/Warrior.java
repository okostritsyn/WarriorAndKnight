package ua.edu.knightandwarrior.model;

public class Warrior {
    private static final int ATTACK=5;
    private int health ;
    private final int initialHealth;
    private Army army;

    public Warrior() {
        this(50);
    }

    public Warrior(int health) {
        initialHealth = this.health = health;
    }

    public int getAttack() {
        return ATTACK;
    }

    public int getHealth() {
        return health;
    }

    private void setHealth(int health) {
        this.health = Math.min(initialHealth,health);
    }

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    public void attack(Warrior warrior) {
        warrior.receiveDamage(getAttack());
    }

    protected void receiveDamage(int attack) {
        setHealth(getHealth() - attack);
    }

    protected void healBy(int healPoints) {
        setHealth(getHealth() + healPoints);
    }

    public boolean isAlive() {
        return getHealth()>0;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " health " + getHealth();
    }
}
