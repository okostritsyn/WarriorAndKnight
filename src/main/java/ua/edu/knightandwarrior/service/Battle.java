package ua.edu.knightandwarrior.service;

import ua.edu.knightandwarrior.model.Army;
import ua.edu.knightandwarrior.model.ArmyType;
import ua.edu.knightandwarrior.model.units.IWarrior;

public class Battle {
    private Battle() {

    }

    public static boolean fight(Army defenderArmy, Army attackerArmy){

        attackerArmy.initArmy(ArmyType.TROOP);
        defenderArmy.initArmy(ArmyType.TROOP);

        var it1 = defenderArmy.firstAliveIterator();
        var it2 = attackerArmy.firstAliveIterator();

        while (it1.hasNext() && it2.hasNext()) {
            fight(it1.next(), it2.next());
        }

         return it1.hasNext();

    }

    public static boolean straightFight(Army defenderArmy, Army attackerArmy){
        attackerArmy.initArmy(ArmyType.LINE);
        defenderArmy.initArmy(ArmyType.LINE);
        var maxRounds = attackerArmy.size()+ defenderArmy.size();
        while(maxRounds > 0){
            var it1 = defenderArmy.firstAliveIterator();
            var it2 = attackerArmy.firstAliveIterator();

            if (!it1.hasNext()) return false;
            if (!it2.hasNext()) return true;

            while (it1.hasNext() && it2.hasNext()) {
                fight(it1.next(), it2.next());
                maxRounds --;
            }
        }
        return defenderArmy.size()>0;// case when attacker army is empty
    }

    public static boolean fight(IWarrior defender, IWarrior attacker){

        if (defender.getAttack()==0){
            defender.killUnit();
        }
        if (attacker.getAttack()==0){
            attacker.killUnit();
        }

        while (defender.isAlive() && attacker.isAlive()) {
            defender.attack(attacker);
            if(attacker.isAlive()){
                attacker.attack(defender);
            }
        }
        return defender.isAlive();
    }
}
