package ua.edu.knightandwarrior.model.units;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Stealer extends Warrior implements CanSteal,CanDefend,CanLancer,CanVampire{
    private static final int ATTACK=5;

    public Stealer() {
        super(60);
    }

    @Override
    public int getAttack() {
        return Math.max(0,ATTACK + getAttackByWeapon());
    }

    @Override
    public void attack(IWarrior warrior) {
        super.attack(warrior);
        if (!warrior.isAlive()){
            stealWeapons(warrior);
            log.atDebug().log(" steal weapons from unit "+warrior+" to "+this);
        }
    }

    @Override
    public int getDefenseByUnit() {
        return 0;
    }

    @Override
    public int getQuantityUnitsAttackByUnit() {
        return 0;
    }

    @Override
    public int getPiercingPowerByUnit() {
        return 0;
    }

    @Override
    public int getVampirismByUnit() {
        return 0;
    }
}
