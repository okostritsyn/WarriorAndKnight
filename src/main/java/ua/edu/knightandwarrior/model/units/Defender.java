package ua.edu.knightandwarrior.model.units;

public class Defender extends Warrior implements CanDefend {
    private static final int DEFENSE_BY_UNIT = 2;
    private static final int ATTACK=3;

    public Defender() {
        super(60);
    }

    public Defender(int health) {
        super(health);
    }

    @Override
    public int getAttack() {
        return Math.max(0,ATTACK + getAttackByWeapon());
    }

    @Override
    public int getDefenseByUnit() {
        return DEFENSE_BY_UNIT;
    }

}
