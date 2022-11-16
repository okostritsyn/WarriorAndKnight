package ua.edu.knightandwarrior.model;

public class Defender extends Warrior {
    private static final int DEFENSE=2;
    private static final int ATTACK=3;
    private int health = 60;

    @Override
    public int getAttack() {
        return ATTACK;
    }

    public int getDefense() {
        return DEFENSE;
    }

    @Override
    protected void receiveDamage(int attack) {
        if (attack <= DEFENSE) {
            return;
        }
        super.receiveDamage(attack-getDefense());
        setHealth(super.getHealth());
    }

    @Override
    public int getHealth() {
        return health;
    }

    private void setHealth(int health) {
        this.health = health;
    }
}
