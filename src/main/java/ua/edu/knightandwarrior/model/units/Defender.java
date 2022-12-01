package ua.edu.knightandwarrior.model.units;

import ua.edu.knightandwarrior.model.weapons.Weapon;

public class Defender extends Warrior implements IWarrior {
    private static final int DEFENSE = 2;
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

    public int getDefense() {
        return Math.max(0,DEFENSE + getDefenseByWeapon());
    }

    @Override
    public void receiveDamage(int attack) {
        if (attack <= getDefense()) {
            return;
        }
        super.receiveDamage(Math.max(0,attack-getDefense()));
    }

    private int getDefenseByWeapon() {
        return getWeapons().stream().mapToInt(Weapon::getDefensePoints).sum();
    }

}
