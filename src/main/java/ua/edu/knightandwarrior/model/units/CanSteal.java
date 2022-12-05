package ua.edu.knightandwarrior.model.units;

import ua.edu.knightandwarrior.model.weapons.Weapon;

public interface CanSteal extends IWarrior{
    default void stealWeapons(IWarrior unit) {
        for (Weapon weapon : unit.getWeapons()){
            equipWeapon(weapon);
        }
        unit.getWeapons().clear();
    }
}
