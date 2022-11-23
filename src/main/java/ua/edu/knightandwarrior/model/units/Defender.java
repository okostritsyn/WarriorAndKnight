package ua.edu.knightandwarrior.model.units;

public class Defender extends Warrior {
    private static final int DEFENSE=2;
    private static final int ATTACK=3;

    public Defender() {
        super(60);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    public int getDefense() {
        return DEFENSE;
    }

    @Override
    public void receiveDamage(int attack) {
        if (attack <= DEFENSE) {
            return;
        }
        super.receiveDamage(Math.max(0,attack-getDefense()));
    }
}
