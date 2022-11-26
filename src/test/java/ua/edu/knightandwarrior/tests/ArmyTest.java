package ua.edu.knightandwarrior.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.edu.knightandwarrior.model.Army;
import ua.edu.knightandwarrior.model.units.*;
import ua.edu.knightandwarrior.service.Battle;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ArmyTest {
    @ParameterizedTest(name = "{index}. Fight {0} fight against {1}, expected result = {2}")
    @MethodSource
    @DisplayName("Two armies straight fight")
    void testTwoArmiesStraightFight(Army army1, Army army2, boolean expected){
        //when
        var status = Battle.straightFight(army1,army2);

        //then
        assertEquals(expected,status);
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
                        false),
                arguments(new Army()
                                .addUnits(Defender::new, 5)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Warrior::new, 7),
                        new Army()
                                .addUnits(Warrior::new, 6)
                                .addUnits(Defender::new, 6)
                                .addUnits(Vampire::new, 6),
                        false),
                arguments(new Army()
                                .addUnits(Defender::new, 2)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 3),
                        false),
                arguments(new Army()
                                .addUnits(Defender::new, 11)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 13),
                        true),
                arguments(new Army()
                                .addUnits(Defender::new, 9)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 8),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 13),
                        true),
                arguments(new Army()
                                .addUnits(Lancer::new, 5)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 5),
                        false),
                arguments(new Army()
                                .addUnits(Lancer::new, 7)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 4),
                        true),
                arguments(new Army()
                                .addUnits(Warrior::new, 2),
                        new Army()
                                .addUnits(Lancer::new, 1)
                                .addUnits(Warrior::new, 1),
                        false),
                arguments(new Army()
                                .addUnits(Lancer::new, 7)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Healer::new, 1)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Defender::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 4),
                        true));
    }

    static List<Arguments> testTwoArmiesStraightFight(){
        return List.of(
                arguments(new Army()
                                .addUnits(Lancer::new, 5)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 5),
                        false),
                arguments(new Army()
                                .addUnits(Lancer::new, 7)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 4),
                        true),
                arguments(new Army()
                                .addUnits(Lancer::new, 7)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Healer::new, 1)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Defender::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 4),
                        false),
                arguments(new Army()
                                .addUnits(Lancer::new, 4)
                                .addUnits(Warrior::new, 3)
                                .addUnits(Healer::new, 1)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Knight::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Vampire::new, 2)
                                .addUnits(Lancer::new, 4),
                        true));
    }
}
