package ua.edu.knightandwarrior.model.weapons;

public class GreatAxe extends Weapon{
    @Override
    public String toString() {
        return  "GreatAxe {" +
                "" + getHealthPoints() +
                "," + getAttackPoints() +
                "," + getDefensePoints() +
                "," + getVampirismPoints() +
                "," + getHealPowerPoints() +
                "," + getPiercingPower() +
                "," + getQuantityUnitsAttack() +
                '}';
    }

    public GreatAxe(){
        super(-15,5,2,10,0,0,0);
    }
}
