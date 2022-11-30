package ua.edu.knightandwarrior.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.edu.knightandwarrior.model.Army;
import ua.edu.knightandwarrior.model.units.*;
import ua.edu.knightandwarrior.service.Battle;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HealerTest {
    @Test
    @DisplayName("Army with Healers vs Army without Healers expected true")
    void testDefenderAndRookieAndWarriorFight(){
        //when
        Army myArmy = new Army();
        myArmy.addUnits(Warrior::new, 1);
        myArmy.addUnits(Healer::new, 1);
        myArmy.addUnits(Healer::new, 1);
        myArmy.addUnits(Defender::new, 1);
        myArmy.addUnits(Healer::new, 1);
        myArmy.addUnits(Knight::new, 1);

        Army enemyArmy = new Army();
        enemyArmy.addUnits(Lancer::new, 1);
        enemyArmy.addUnits(Warrior::new, 4);

        var test = Battle.fight(myArmy, enemyArmy);
        //then
        assertTrue(test);
    }

}
