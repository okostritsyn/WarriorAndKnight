package ua.edu.knightandwarrior.model.units;

import ua.edu.knightandwarrior.model.weapons.Weapon;

public class Vampire extends Warrior implements IWarrior {
    private static final int VAMPIRISM=50;
    private static final int ATTACK=4;

    public Vampire() {
        super(40);
    }

    @Override
    public int getAttack() {
        return Math.max(0,ATTACK + getAttackByWeapon());
    }

    public int getVampirism() {
        return Math.max(0,VAMPIRISM + getVampirismByWeapon());
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

    private int getVampirismByWeapon() {
        return getWeapons().stream().mapToInt(Weapon::getVampirismPoints).sum();
    }

}
