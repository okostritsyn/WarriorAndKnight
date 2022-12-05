package ua.edu.knightandwarrior.model.weapons;

public class SuperSpear extends Weapon {
    @Override
    public String toString() {
        return  "Super spear {" +
                "" + getHealthPoints() +
                "," + getAttackPoints() +
                "," + getDefensePoints() +
                "," + getVampirismPoints() +
                "," + getHealPowerPoints() +
                "," + getPiercingPower() +
                "," + getQuantityUnitsAttack() +
                '}';
    }

    public SuperSpear(){
        super(5,0,0,0,0,30,1);
    }
}
