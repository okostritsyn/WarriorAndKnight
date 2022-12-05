package ua.edu.knightandwarrior.model.units;

public class Vampire extends Warrior implements CanVampire {
    private static final int VAMPIRISM_BY_UNIT=50;
    private static final int ATTACK=4;

    public Vampire() {
        super(40);
    }

    @Override
    public int getAttack() {
        return Math.max(0,ATTACK + getAttackByWeapon());
    }

    @Override
    public int getVampirismByUnit() {
        return VAMPIRISM_BY_UNIT;
    }
}
