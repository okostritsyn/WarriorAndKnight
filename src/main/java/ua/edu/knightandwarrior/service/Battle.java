package ua.edu.knightandwarrior.service;

import ua.edu.knightandwarrior.model.Army;
import ua.edu.knightandwarrior.model.HasWarriorBehind;
import ua.edu.knightandwarrior.model.units.IHealer;
import ua.edu.knightandwarrior.model.units.IWarrior;

public class Battle {
    private Battle() {

    }

    public static boolean fight(Army defenderArmy, Army attackerArmy){

        attackerArmy.initSubscribesInArmy();
        defenderArmy.initSubscribesInArmy();

        var it1 = defenderArmy.firstAliveIterator();
        var it2 = attackerArmy.firstAliveIterator();

        while (it1.hasNext() && it2.hasNext()) {
            fight(it1.next(), it2.next());
        }

         return it1.hasNext();

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
