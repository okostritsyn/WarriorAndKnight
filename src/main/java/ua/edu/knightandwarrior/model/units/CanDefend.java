package ua.edu.knightandwarrior.model.units;

import ua.edu.knightandwarrior.model.weapons.Weapon;

public interface CanDefend extends IWarrior {

    default int getDefense() {
        return Math.max(0,getDefenseByUnit() + getDefenseByWeapon());
    }

    int getDefenseByUnit();

    private int getDefenseByWeapon() {
        return getWeapons().stream().mapToInt(Weapon::getDefensePoints).sum();
    }

    @Override
    default void receiveDamage(int attack) {
        if (attack <= getDefense()) {
            return;
        }
        IWarrior.super.receiveDamage(Math.max(0,attack-getDefense()));
    }
}
