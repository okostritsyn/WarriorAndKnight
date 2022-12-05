package ua.edu.knightandwarrior.model.weapons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Weapon {
    private int healthPoints;
    private int attackPoints;
    private int defensePoints;
    private int vampirismPoints;
    private int healPowerPoints;
    private int piercingPower;
    private int quantityUnitsAttack;

    @Override
    public String toString() {
        return "Weapon {" +
                "" + getHealthPoints() +
                "," + getAttackPoints() +
                "," + getDefensePoints() +
                "," + getVampirismPoints() +
                "," + getHealPowerPoints() +
                "," + getPiercingPower() +
                "," + getQuantityUnitsAttack() +
                '}';
    }
}
