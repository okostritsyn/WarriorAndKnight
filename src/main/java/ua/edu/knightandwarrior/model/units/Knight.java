package ua.edu.knightandwarrior.model.units;

public class Knight extends Warrior {
    private static final int ATTACK=7;

    @Override
    public int getAttack() {
        return Math.max(0,ATTACK + getAttackByWeapon());
    }
}
