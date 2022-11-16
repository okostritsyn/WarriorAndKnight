package ua.edu.knightandwarrior.tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.edu.knightandwarrior.model.*;
import ua.edu.knightandwarrior.service.Battle;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BattleTest {
    private Warrior warrior;
    private Warrior knight;
    private Defender defender;
    private Rookie rookie;

    @BeforeEach
    public void setupTest() {
        knight = new Knight();
        warrior = new Warrior();
        defender = new Defender();
        rookie = new Rookie();
    }

    @ParameterizedTest(name = "{index}. Fight {0} fight against {1}, expected result = {2}")
    @MethodSource
    @DisplayName("Two warriors fight")
    void testTwoWarriorsFight(Warrior warrior1, Warrior warrior2,boolean warrior2Check, boolean expected){
        //when
        Battle.fight(warrior1,warrior2);
        var test = warrior1.isAlive();
        if (warrior2Check) {
             test = warrior2.isAlive();
        }
        //then
        assertEquals(expected,test);
    }

    static List<Arguments> testTwoWarriorsFight(){
        return List.of(arguments(new Warrior(), new Knight(),false, false),
                arguments(new Knight(), new Warrior(),false, true),
                arguments(new Warrior(), new Warrior(),false, true),
                arguments(new Knight(), new Warrior(),false, true),
                arguments(new Warrior(), new Warrior(),true, false),
                arguments(new Warrior(), new Knight(),true, true));
    }

    @Test
    @DisplayName("Defender vs rookie and warrior expected true")
    void testDefenderAndRookieAndWarriorFight(){
        //when
        Battle.fight(defender,rookie);
        var test = Battle.fight(defender,warrior);

        //then
        assertTrue(test);
    }

    @Test
    @DisplayName("Defender vs rookie expected 60")
    void testDefenderVSRookie_health(){
        //when
        Battle.fight(defender,rookie);

        //then
        assertEquals(defender.getHealth(),60);
    }

    @Test
    @DisplayName("Three warriors fight expected false")
    void testThreeWarriorsFight(){
        //when
        Battle.fight(warrior,knight);
        var test = Battle.fight(knight,new Warrior());

        //then
        assertFalse(test);
    }

    @ParameterizedTest(name = "{index}. Fight {0} fight against {1}, expected result = {2}")
    @MethodSource
    @DisplayName("Two armies fight")
    void testTwoArmiesFight(Army army1, Army army2, boolean expected){
        //when
        var status = Battle.fight(army1,army2);

        //then
        assertEquals(expected,status);
    }

    static List<Arguments> testTwoArmiesFight(){
        return List.of(
                arguments(new Army()
                        .addUnits(Warrior::new, 1),
                        new Army()
                                .addUnits(Warrior::new, 3),
                        false),
                arguments(new Army()
                                .addUnits(Warrior::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 3),
                        false),
                arguments(new Army()
                                .addUnits(Warrior::new, 5),
                        new Army()
                                .addUnits(Warrior::new, 7),
                        false),
                arguments(new Army()
                                .addUnits(Warrior::new, 20),
                        new Army()
                                .addUnits(Warrior::new, 21),
                        true),
                arguments(new Army()
                                .addUnits(Warrior::new, 10),
                        new Army()
                                .addUnits(Warrior::new, 11),
                        true),
                arguments(new Army()
                                .addUnits(Warrior::new, 11),
                        new Army()
                                .addUnits(Warrior::new, 7),
                        true),
                arguments(new Army()
                                .addUnits(Warrior::new, 5)
                                .addUnits(Defender::new, 4)
                                .addUnits(Defender::new, 5),
                        new Army()
                                .addUnits(Warrior::new, 4),
                        true),
                arguments(new Army()
                                .addUnits(Defender::new, 5)
                                .addUnits(Warrior::new, 20)
                                .addUnits(Defender::new, 4),
                        new Army()
                                .addUnits(Warrior::new, 21),
                        true),
                arguments(new Army()
                                .addUnits(Defender::new, 5)
                                .addUnits(Warrior::new, 10)
                                .addUnits(Defender::new, 10),
                        new Army()
                                .addUnits(Warrior::new, 5),
                        true),
                arguments(new Army()
                                .addUnits(Defender::new, 2)
                                .addUnits(Warrior::new, 1)
                                .addUnits(Defender::new, 1),
                        new Army()
                                .addUnits(Warrior::new, 5),
                        false));
    }
}
