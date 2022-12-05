package ua.edu.knightandwarrior.model.weapons;

public class Sword extends Weapon{
    public Sword(){
        super(5,2,0,0,0,0,0);
    }

    @Override
    public String toString() {
        return "Sword {" +
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
