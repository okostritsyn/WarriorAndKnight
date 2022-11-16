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
    }
}
