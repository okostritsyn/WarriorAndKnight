package ua.edu.knightandwarrior.model.weapons;

public class Shield extends Weapon{
    public Shield(){
        super(20,-1,2,0,0,0,0);
    }

    @Override
    public String toString() {
        return "Shield {" +
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
