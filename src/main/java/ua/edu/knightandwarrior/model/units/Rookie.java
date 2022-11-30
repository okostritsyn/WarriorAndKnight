package ua.edu.knightandwarrior.model.units;

public class Rookie extends Warrior{
    private static final int ATTACK=1;

    @Override
    public int getAttack() {
        return Math.max(0,ATTACK + getAttackByWeapon());
    }
}
