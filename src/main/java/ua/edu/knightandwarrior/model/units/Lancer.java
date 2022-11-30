package ua.edu.knightandwarrior.model.units;

import ua.edu.knightandwarrior.model.HasWarriorBehind;

public class Lancer extends Warrior implements IWarrior {
    private static final int ATTACK=6;
    private static final int QUANTITY_UNITS_ATTACK=2;
    private static final int PIERCING_POWER=50;

    public Lancer() {
        super(50);
    }

    @Override
    public int getAttack() {
        return Math.max(0,ATTACK + getAttackByWeapon());
    }

    public int getQuantityUnitsAttack() {
        return Math.max(0,QUANTITY_UNITS_ATTACK);
    }

    public int getPiercingPower() {
        return PIERCING_POWER;
    }

    @Override
    public void attack(IWarrior warrior) {
        var healthBeforeHit = warrior.getHealth();
        super.attack(warrior);
        var healthAfterHit = warrior.getHealth();

        for (int i = 0; i < getQuantityUnitsAttack(); i++) {
            if (warrior instanceof HasWarriorBehind warriorInArmy) {
                var nextWarrior = warriorInArmy.getWarriorBehind();
                if (nextWarrior != null) {
                    int damageToNext = (healthBeforeHit - healthAfterHit) * getPiercingPower() / 100;
                    healthBeforeHit = nextWarrior.getHealth();
                    nextWarrior.receiveDamage(damageToNext);
                    healthAfterHit = nextWarrior.getHealth();
                    warrior = nextWarrior;
                } else {
                    break;
                }
            }
        }
    }
}
