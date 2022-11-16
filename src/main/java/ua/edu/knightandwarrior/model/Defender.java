package ua.edu.knightandwarrior.model;

public class Defender extends Warrior {
    private static final int DEFENSE=2;
    private static final int ATTACK=3;
    private int health = 60;

    @Override
    public int getAttack() {
        return ATTACK;
    }

    @Override
    protected void receiveDamage(int attack) {
        if (attack <= DEFENSE) {
            return;
        }
        super.receiveDamage(attack);
        setHealth(super.getHealth()+DEFENSE);
    }

    @Override
    public int getHealth() {
        return health;
    }

    private void setHealth(int health) {
        this.health = health;
    }
}
