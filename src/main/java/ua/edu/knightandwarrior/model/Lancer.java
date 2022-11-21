package ua.edu.knightandwarrior.model;

import java.util.List;

public class Lancer extends Warrior{
    private static final int ATTACK=6;
    private static final int QUANTITY_UNITS_ATTACK=2;
    private int attackNextWarrior;

    public Lancer() {
        super(50);
        attackNextWarrior = ATTACK;
    }

    @Override
    public int getAttack() {
        return attackNextWarrior;
    }

    private void setAttack(int attack) {
        this.attackNextWarrior = attack;
    }

    public int getQuantityUnitsAttack() {
        return Math.max(0,QUANTITY_UNITS_ATTACK);
    }

    @Override
    public void attack(Warrior warrior) {
        var healthBeforeHit = warrior.getHealth();
        super.attack(warrior);
        var healthAfterHit = warrior.getHealth();

        if (warrior.getArmy()!=null){
            List<Warrior> armyContent = warrior.getArmy().getUnitsBehind(warrior);
            for (int i = 0;i<Math.min(armyContent.size(),getQuantityUnitsAttack());i++){
                var newAttack = (healthBeforeHit-healthAfterHit)/2;
                Warrior nextWarrior = armyContent.get(i);
                setAttack(newAttack);
                healthBeforeHit = nextWarrior.getHealth();
                super.attack(nextWarrior);
                healthAfterHit = nextWarrior.getHealth();
            }
            setAttack(ATTACK);
        }
    }
}
