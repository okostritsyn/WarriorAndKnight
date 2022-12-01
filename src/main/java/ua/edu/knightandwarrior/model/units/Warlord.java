package ua.edu.knightandwarrior.model.units;

public class Warlord extends Defender {
    private static final int ATTACK=4;

    public Warlord() {
        super(100);
    }

    @Override
    public int getAttack() {
        return Math.max(0,ATTACK + getAttackByWeapon());
    }

}
