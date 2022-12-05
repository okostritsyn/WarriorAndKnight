package ua.edu.knightandwarrior.model.units;

public class Healer extends Warrior implements CanHeal {
    private static final int ATTACK=0;
    private static final int HEALTH_POINTS_BY_UNIT=2;

    public Healer() {
        super(60);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    @Override
    public int getHealthPointsByUnit() {
        return HEALTH_POINTS_BY_UNIT;
    }
}
