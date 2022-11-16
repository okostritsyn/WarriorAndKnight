package ua.edu.knightandwarrior.service;

import ua.edu.knightandwarrior.model.Army;
import ua.edu.knightandwarrior.model.Warrior;

public class Battle {

    public static boolean fightOld(Army defenderArmy, Army attackerArmy){
        int currDefenderNum = 0;
        int currAttackerNum = 0;

        while (true){
            if (currDefenderNum >= defenderArmy.size() || currAttackerNum >= attackerArmy.size()) break;
            Warrior currDefender = defenderArmy.getWarriorByIndex(currDefenderNum);
            Warrior currAtacker = attackerArmy.getWarriorByIndex(currAttackerNum);

            if (!fight(currDefender,currAtacker)) {
                currDefenderNum++;
            } else {
                currAttackerNum++;
            }
        }
        return defenderArmy.isAlive();
    }

    public static boolean fight(Army defenderArmy, Army attackerArmy){

        var it1 = defenderArmy.firstAliveIterator();
        var it2 = attackerArmy.firstAliveIterator();

        while (it1.hasNext() && it2.hasNext()){
            fight(it1.next(), it2.next());
        }

        return it1.hasNext();

    }
    public static boolean fight(Warrior defender, Warrior attacker){
        while (defender.isAlive() && attacker.isAlive()) {
            defender.attack(attacker);
            if(attacker.isAlive()){
                attacker.attack(defender);
            }
        }
        return defender.isAlive();


    }
}
