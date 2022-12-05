package ua.edu.knightandwarrior.model.units;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lancer extends Warrior implements CanLancer {
    private static final int ATTACK=6;
    private static final int QUANTITY_UNITS_ATTACK_BY_UNIT=1;
    private static final int PIERCING_POWER_BY_UNIT=50;

    public Lancer() {
        super(50);
    }

    @Override
    public int getAttack() {
        return Math.max(0,ATTACK + getAttackByWeapon());
    }

    @Override
    public int getQuantityUnitsAttackByUnit() {
        return QUANTITY_UNITS_ATTACK_BY_UNIT;
    }

    @Override
    public int getPiercingPowerByUnit() {
        return PIERCING_POWER_BY_UNIT;
    }
}
