package ua.edu.knightandwarrior.service;

import lombok.extern.slf4j.Slf4j;
import ua.edu.knightandwarrior.model.Army;
import ua.edu.knightandwarrior.model.ArmyType;
import ua.edu.knightandwarrior.model.units.IWarrior;

@Slf4j
public class Battle {

    private Battle() {

    }

    public static boolean fight(Army defenderArmy, Army attackerArmy){

        attackerArmy.initArmy(ArmyType.TROOP);
        defenderArmy.initArmy(ArmyType.TROOP);

        defenderArmy.moveUnits();
        attackerArmy.moveUnits();

        log.atDebug().log("Troops fight");
        log.atDebug().log("{} vs {}",defenderArmy,attackerArmy );

        var it1 = defenderArmy.firstAliveIterator();
        var it2 = attackerArmy.firstAliveIterator();

        while (it1.hasNext() && it2.hasNext()) {
            var firstFighterWins = fight(it1.next(), it2.next());

            if (firstFighterWins) {
                var status = attackerArmy.moveUnits();
                if (status) it2 = attackerArmy.firstAliveIterator();
            } else {
                var status =defenderArmy.moveUnits();
                if (status) it1 = defenderArmy.firstAliveIterator();
            }
        }

        return it1.hasNext();
    }

    public static boolean straightFight(Army defenderArmy, Army attackerArmy){
        attackerArmy.initArmy(ArmyType.LINE);
        defenderArmy.initArmy(ArmyType.LINE);

        log.atDebug().log("Straight fight");
        log.atDebug().log("{} vs {}",defenderArmy,attackerArmy );

        defenderArmy.moveUnits();
        attackerArmy.moveUnits();

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
        return defenderArmy.isAlive();
    }

    public static boolean fight(IWarrior defender, IWarrior attacker){

        log.atDebug().log(" Fight before {} vs {}",defender,attacker );

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
            log.atDebug().log("  {} vs {}",defender,attacker );
        }
        log.atDebug().log(" Fight after {} vs {}",defender,attacker );

        return defender.isAlive();
    }
}
