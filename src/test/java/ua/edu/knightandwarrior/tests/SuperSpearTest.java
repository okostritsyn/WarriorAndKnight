package ua.edu.knightandwarrior.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.edu.knightandwarrior.model.Army;
import ua.edu.knightandwarrior.model.units.*;
import ua.edu.knightandwarrior.model.weapons.SuperSpear;
import ua.edu.knightandwarrior.model.weapons.Sword;
import ua.edu.knightandwarrior.model.weapons.Weapon;
import ua.edu.knightandwarrior.service.Battle;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class SuperSpearTest {
    @ParameterizedTest(name = "{index}. Fight {0} with{2} fight against {1} with {3}, expected result = {4} and expected result = {5}")
    @MethodSource
    @DisplayName("Two warriors fight with weapons")
    void testTwoWarriorsFight(Warrior warrior1, Warrior warrior2, Weapon[] weapon1, Weapon[] weapon2,int expectedHeatlhUnit1, int expectedHeatlhUnit2){
        for (Weapon weapon : weapon1) {
            if (weapon!=null) warrior1.equipWeapon(weapon);
        }
        for (Weapon weapon : weapon2) {
            if (weapon!=null) warrior2.equipWeapon(weapon);
        }

        //when
        Battle.fight(warrior1,warrior2);

        //then
        assertEquals(expectedHeatlhUnit1,warrior1.getHealth());
        assertEquals(expectedHeatlhUnit2,warrior2.getHealth());
    }

    static List<Arguments> testTwoWarriorsFight(){
        return List.of(
                arguments(new Defender(),
                        new Lancer(),
                        new Weapon[]{new Sword()},
                        new Weapon[]{new SuperSpear()},
                        25,0));
    }

    @ParameterizedTest(name = "{index}. Fight {0} fight against {1}, expected result = {2}")
    @MethodSource
    @DisplayName("Two armies straight fight with weapons")
    void testTwoArmiesStraightFightWithWeapons(Army army1, Army army2, Weapon[] weapon1, Weapon[] weapon2, boolean expected){
        //when
        for (int i = 0; i < weapon1.length; i++) {
            if (weapon1[i]!=null) army1.equipWarriorAtPosition(i,weapon1[i]);
        }
        for (int j = 0; j < weapon2.length; j++) {
            if (weapon2[j]!=null) army2.equipWarriorAtPosition(j,weapon2[j]);
        }

        var status = Battle.straightFight(army1,army2);

        //then
        assertEquals(expected,status);
    }

    static List<Arguments> testTwoArmiesStraightFightWithWeapons(){
        return List.of(
                arguments(
                        new Army()
                                .addUnits(Lancer::new, 2)
                                .addUnits(Defender::new, 2),
                        new Army()
                                .addUnits(Stealer::new, 1)
                                .addUnits(Defender::new, 2),
                        new Weapon[]{new SuperSpear(),new SuperSpear(),new SuperSpear()},
                        new Weapon[]{new Sword(),new Sword(),new Sword()},
                        false));
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
                arguments(
                        new Army()
                                .addUnits(Lancer::new, 2)
                                .addUnits(Stealer::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 1)
                                .addUnits(Defender::new, 2),
                        new Weapon[]{new SuperSpear(),new SuperSpear()},
                        new Weapon[]{new Sword(),new Sword(),new Sword()},
                        true));
    }
}
