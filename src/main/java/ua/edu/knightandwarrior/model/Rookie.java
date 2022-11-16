package ua.edu.knightandwarrior.model;

public class Rookie extends Warrior{
    private static final int ATTACK=1;

    @Override
    public int getAttack() {
        return ATTACK;
    }
}
