package ua.edu.knightandwarrior.model.units;

public class Vampire extends Warrior {
    private static final int VAMPIRISM=50;
    private static final int ATTACK=4;

    public Vampire() {
        super(40);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    public int getVampirism() {
        return VAMPIRISM;
    }

    @Override
    public void attack(IWarrior warrior) {
        var healthBeforeHit = warrior.getHealth();
        super.attack(warrior);
        var healthAfterHit = warrior.getHealth();
        var healthDamage = healthBeforeHit-healthAfterHit;
        int healPoints = healthDamage*getVampirism()/100;
        healBy(healPoints);
    }
}
