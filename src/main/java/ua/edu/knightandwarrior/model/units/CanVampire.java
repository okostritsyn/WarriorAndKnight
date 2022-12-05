package ua.edu.knightandwarrior.model.units;

import ua.edu.knightandwarrior.model.weapons.Weapon;

public interface CanVampire extends IWarrior {
    default int getVampirism() {
        return Math.max(0,getVampirismByUnit() + getVampirismByWeapon());
    }

    int getVampirismByUnit();

    private int getVampirismByWeapon() {
        return getWeapons().stream().mapToInt(Weapon::getVampirismPoints).sum();
    }

    @Override
    default void attack(IWarrior warrior) {
        var healthBeforeHit = warrior.getHealth();
        IWarrior.super.attack(warrior);
        var healthAfterHit = warrior.getHealth();
        var healthDamage = healthBeforeHit-healthAfterHit;
        int healPoints = healthDamage*getVampirism()/100;
        healBy(healPoints);
    }
}
