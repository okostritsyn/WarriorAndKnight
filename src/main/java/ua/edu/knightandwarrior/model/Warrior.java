package ua.edu.knightandwarrior.model;

public class Warrior {
    private static final int ATTACK=5;
    private int health = 50;

    public int getAttack() {
        return ATTACK;
    }

    public int getHealth() {
        return health;
    }

    private void setHealth(int health) {
        this.health = health;
    }

    public void attack(Warrior warrior) {
        warrior.receiveDamage(getAttack());
    }

    protected void receiveDamage(int attack) {
        setHealth(getHealth() - attack);
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
