package ua.edu.knightandwarrior.model;

public class Knight extends Warrior {
    private static final int ATTACK=7;

    @Override
    public int getAttack() {
        return ATTACK;
    }
}
