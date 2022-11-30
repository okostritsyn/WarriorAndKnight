package ua.edu.knightandwarrior.model.units;

import ua.edu.knightandwarrior.model.weapons.Weapon;

public class Defender extends Warrior implements IWarrior {
    private static final int DEFENSE=2;
    private static final int ATTACK=3;
    private int defenseByWeapon;

    public Defender() {
        super(60);
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

    @Override
    public void equipWeapon(Weapon weapon) {
        super.equipWeapon(weapon);
        setDefenseByWeapon(getDefenseByWeapon()+weapon.getDefensePoints());
    }

    private int getDefenseByWeapon() {
        return defenseByWeapon;
    }

    private void setDefenseByWeapon(int defensePoints) {
        this.defenseByWeapon = defensePoints;
    }
}
