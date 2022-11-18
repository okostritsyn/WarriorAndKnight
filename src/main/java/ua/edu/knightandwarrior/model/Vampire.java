package ua.edu.knightandwarrior.model;

public class Vampire extends Warrior {
    private static final int VAMPIRISM=50;
    private static final int ATTACK=4;
    private static final int INITHEALTH=40;
    private int health = INITHEALTH;

    @Override
    public int getAttack() {
        return ATTACK;
    }

    public int getVampirism() {
        return VAMPIRISM;
    }

    @Override
    public void attack(Warrior warrior) {
        int healthBeforeHit = warrior.getHealth();
        warrior.receiveDamage(getAttack());
        int healthAfterHit = warrior.getHealth();
        int koef = 100/getVampirism();
        int vampire = (healthBeforeHit-healthAfterHit)/koef;
        if ((getHealth()+vampire)<=INITHEALTH) {
            setHealth(getHealth() + vampire);
        }
    }

    @Override //?
    protected void receiveDamage(int attack) {
        setHealth(getHealth() - attack);
    }

    @Override
    public int getHealth() {
        return health;
    }

    private void setHealth(int health) {
        this.health = health;
    }
}
