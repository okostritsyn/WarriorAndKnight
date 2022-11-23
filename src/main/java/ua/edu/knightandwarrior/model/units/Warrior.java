package ua.edu.knightandwarrior.model.units;

public class Warrior implements IWarrior {
    private static final int ATTACK=5;
    private static final int ZERO_HEALTH=0;

    private int health ;
    private final int initialHealth;

    public Warrior() {
        this(50);
    }

    public Warrior(int health) {
        initialHealth = this.health = health;
    }

    public int getAttack() {
        return ATTACK;
    }

    @Override
    public int getZeroHealth() {
        return ZERO_HEALTH;
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
