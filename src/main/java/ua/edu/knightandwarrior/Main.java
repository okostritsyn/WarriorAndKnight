package ua.edu.knightandwarrior;

import ua.edu.knightandwarrior.model.Army;
import ua.edu.knightandwarrior.model.Knight;
import ua.edu.knightandwarrior.model.Warrior;
import ua.edu.knightandwarrior.service.Battle;

public class Main {
    public static void main(String[] args) {
        Army myArmy = new Army();
        myArmy.addUnits(Knight::new, 3);

        Army enemyArmy = new Army();
        enemyArmy.addUnits(Warrior::new, 3);

        System.out.println("Fight myArmy vs enemyArmy "+Battle.fight(myArmy, enemyArmy));
        System.out.println("myArmy is alive "+myArmy.isAlive());
        System.out.println("enemyArmy is alive "+enemyArmy.isAlive());

        Army army3 = new Army();
        army3.addUnits(Knight::new, 3);

        Army army4 = new Army();
        army4.addUnits(Warrior::new, 3);

        System.out.println("Fight myArmy vs enemyArmy "+Battle.fightOld(army3, army4));
        System.out.println("myArmy is alive "+army3.isAlive());
        System.out.println("enemyArmy is alive "+army4.isAlive());
    }
}
