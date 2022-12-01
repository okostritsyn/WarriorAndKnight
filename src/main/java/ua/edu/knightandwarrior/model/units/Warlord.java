package ua.edu.knightandwarrior.model.units;

import ua.edu.knightandwarrior.service.EventType;

import ua.edu.knightandwarrior.service.EventListener;

public class Warlord extends Defender {
    private static final int ATTACK=4;

    public Warlord() {
        super(100);
    }

    @Override
    public int getAttack() {
        return Math.max(0,ATTACK + getAttackByWeapon());
    }

    public void moveUnits(Iterable<IWarrior> army){

    }
}
