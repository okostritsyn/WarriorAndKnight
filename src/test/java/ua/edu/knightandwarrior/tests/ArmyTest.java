package ua.edu.knightandwarrior.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.edu.knightandwarrior.model.Army;
import ua.edu.knightandwarrior.model.units.*;
import ua.edu.knightandwarrior.model.weapons.*;
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

    @ParameterizedTest(name = "{index}. Fight {0} fight against {1}, expected result = {2}")
    @MethodSource
    @DisplayName("Two armies fight with weapons")
    void testTwoArmiesFightWithWeapons(Army army1, Army army2, Weapon[] weapon1, Weapon[] weapon2, boolean expected){
        //when
        for (int i = 0; i < weapon1.length; i++) {
            army1.equipWarriorAtPosition(i,weapon1[i]);
        }
        for (int j = 0; j < weapon2.length; j++) {
            army2.equipWarriorAtPosition(j,weapon2[j]);
        }

        var status = Battle.fight(army1,army2);

        //then
        assertEquals(expected,status);
    }

    static List<Arguments> testTwoArmiesFightWithWeapons(){
        return List.of(
                arguments(new Army()
                                .addUnits(Knight::new, 1)
                                .addUnits(Lancer::new, 1),
                        new Army()
                                .addUnits(Vampire::new, 1)
                                .addUnits(Healer::new, 1),
                        new Weapon[]{new MagicWand(),new GreatAxe()},
                        new Weapon[]{new MagicWand(),new GreatAxe()},
                        true),
                arguments(new Army()
                                .addUnits(Defender::new, 1)
                                .addUnits(Warrior::new, 1),
                        new Army()
                                .addUnits(Knight::new, 1)
                                .addUnits(Healer::new, 1),
                        new Weapon[]{new Sword(),new Sword()},
                        new Weapon[]{new GreatAxe(),new GreatAxe()},
                        true),
                arguments(new Army()
                                .addUnits(Defender::new, 2),
                        new Army()
                                .addUnits(Knight::new, 1)
                                .addUnits(Vampire::new, 1),
                        new Weapon[]{new Katana(),new Katana()},
                        new Weapon[]{new Katana(),new Katana()},
                        false),
                arguments(new Army()
                                .addUnits(Knight::new, 3),
                        new Army()
                                .addUnits(Warrior::new, 1)
                                .addUnits(Defender::new, 2),
                        new Weapon[]{new Weapon(-20, 6, 1, 40, -2),new Weapon(-20, 6, 1, 40, -2),new Weapon(20, -2, 2, -55, 3)},
                        new Weapon[]{new Weapon(-20, 6, 1, 40, -2),new Weapon(20, -2, 2, -55, 3),new Weapon(20, -2, 2, -55, 3)},
                        true));
    }

    @ParameterizedTest(name = "{index}. Fight {0} fight against {1}, expected result = {2}")
    @MethodSource
    @DisplayName("Two armies straight fight with weapons")
    void testTwoArmiesStraightFightWithWeapons(Army army1, Army army2,Weapon[] weapon1, Weapon[] weapon2, boolean expected){
        //when
        for (int i = 0; i < weapon1.length; i++) {
            army1.equipWarriorAtPosition(i,weapon1[i]);
        }
        for (int j = 0; j < weapon2.length; j++) {
            army2.equipWarriorAtPosition(j,weapon2[j]);
        }

        var status = Battle.straightFight(army1,army2);

        //then
        assertEquals(expected,status);
    }

    static List<Arguments> testTwoArmiesStraightFightWithWeapons(){
        return List.of(
                arguments(
                        new Army()
                                .addUnits(Vampire::new, 3),
                        new Army()
                                .addUnits(Warrior::new, 1)
                                .addUnits(Defender::new, 2),
                        new Weapon[]{new Weapon(-20, 1, 1, 40, -2),new Weapon(-20, 1, 1, 40, -2),new Weapon(20, 2, 2, -55, 3)},
                        new Weapon[]{new Weapon(-20, 1, 1, 40, -2),new Weapon(20, 2, 2, -55, 3),new Weapon(20, 2, 2, -55, 3)},
                        false),
                arguments(
                        new Army()
                                .addUnits(Vampire::new, 2)
                                .addUnits(Rookie::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 1)
                                .addUnits(Defender::new, 2),
                        new Weapon[]{new Katana(),new Katana(),new Shield()},
                        new Weapon[]{new Katana(),new Shield(),new Shield()},
                        true),
                arguments(
                        new Army()
                                .addUnits(Vampire::new, 3),
                        new Army()
                                .addUnits(Warrior::new, 1)
                                .addUnits(Defender::new, 1),
                        new Weapon[]{new GreatAxe(),new GreatAxe(),new GreatAxe()},
                        new Weapon[]{new Sword(),new Sword()},
                        true),
                arguments(
                        new Army()
                                .addUnits(Rookie::new, 3),
                        new Army()
                                .addUnits(Defender::new, 1)
                                .addUnits(Healer::new, 1),
                        new Weapon[]{new Katana(),new Katana(),new Katana()},
                        new Weapon[]{new MagicWand(),new MagicWand()},
                        false));
    }

}
