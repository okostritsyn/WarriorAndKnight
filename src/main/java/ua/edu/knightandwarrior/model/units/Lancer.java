package ua.edu.knightandwarrior.model.units;

import ua.edu.knightandwarrior.model.HasWarriorBehind;

public class Lancer extends Warrior{
    private static final int ATTACK=6;
    private static final int QUANTITY_UNITS_ATTACK=2;
    private static final int PIERCING_POWER=50;

    public Lancer() {
        super(50);
    }

    @Override
    public int getAttack() {
        return ATTACK;
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

        for (int i = 0; i < getQuantityUnitsAttack(); i++) {
            if (warrior instanceof HasWarriorBehind warriorInArmy) {
                var nextWarrior = warriorInArmy.getWarriorBehind();
                if (nextWarrior != null) {
                    var healthAfterHit = warrior.getHealth();
                    var damageToNext = (healthBeforeHit - healthAfterHit) * getPiercingPower() / 100;
                    nextWarrior.receiveDamage(damageToNext);
                    warrior = nextWarrior;
                } else {
                    break;
                }
            }
        }
    }
}
