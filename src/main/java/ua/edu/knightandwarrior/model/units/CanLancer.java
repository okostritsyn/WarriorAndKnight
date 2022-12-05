package ua.edu.knightandwarrior.model.units;

import ua.edu.knightandwarrior.model.HasWarriorBehind;
import ua.edu.knightandwarrior.model.weapons.Weapon;

public interface CanLancer extends IWarrior {

    int getQuantityUnitsAttackByUnit();

    int getPiercingPowerByUnit();

    private int getQuantityUnitsAttackByWeapon() {
        return getWeapons().stream().mapToInt(Weapon::getQuantityUnitsAttack).sum();
    }

    private int getPiercingPowerByWeapon() {
        return getWeapons().stream().mapToInt(Weapon::getPiercingPower).sum();
    }

    default int getQuantityUnitsAttack() {
        return Math.max(0,getQuantityUnitsAttackByUnit() + getQuantityUnitsAttackByWeapon());
    }

    default int getPiercingPower() {
        return Math.max(0,getPiercingPowerByUnit() + getPiercingPowerByWeapon());
    }

    @Override
    default void attack(IWarrior warrior) {
        var healthBeforeHit = warrior.getHealth();
        IWarrior.super.attack(warrior);
        var healthAfterHit = warrior.getHealth();

        int i = 0;
        while (i < getQuantityUnitsAttack()) {
            if (warrior instanceof HasWarriorBehind warriorInArmy) {
                var nextWarrior = warriorInArmy.getWarriorBehind();
                if (nextWarrior != null && nextWarrior.isAlive()) {
                    int damageToNext = (healthBeforeHit - healthAfterHit) * getPiercingPower() / 100;
                    healthBeforeHit = nextWarrior.getHealth();
                    nextWarrior.receiveDamage(damageToNext);
                    healthAfterHit = nextWarrior.getHealth();
                    warrior = nextWarrior;
                } else if (nextWarrior != null) {
                    warrior = nextWarrior;
                    i--;
                } else {
                    break;
                }
            }
            i++;
        }
    }
}
