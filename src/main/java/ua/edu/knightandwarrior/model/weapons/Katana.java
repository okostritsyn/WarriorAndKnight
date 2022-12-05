package ua.edu.knightandwarrior.model.weapons;

public class Katana extends Weapon{
    public Katana(){
        super(-20,6,-5,50,0,0,0);
    }

    @Override
    public String toString() {
        return  "Katana {" +
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
