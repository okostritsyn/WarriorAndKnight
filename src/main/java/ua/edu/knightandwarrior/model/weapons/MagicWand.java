package ua.edu.knightandwarrior.model.weapons;

public class MagicWand extends Weapon {
    public MagicWand() {
        super(30, 3, 0, 0, 3,0,0);
    }

    @Override
    public String toString() {
        return "MagicWand {" +
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
