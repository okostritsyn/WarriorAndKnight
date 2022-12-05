package ua.edu.knightandwarrior.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.edu.knightandwarrior.model.units.*;
import ua.edu.knightandwarrior.model.weapons.*;
import ua.edu.knightandwarrior.service.Battle;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class WeaponTest {
    @ParameterizedTest(name = "{index}. Weapon Fight {0} fight against {1}, expected result = {2}")
    @MethodSource
    @DisplayName("Two warriors fight with weapons")
    void testTwoWarriorsFight(Warrior warrior1, Warrior warrior2,Weapon[] weapon1,Weapon[] weapon2, boolean expected){
        //when
        for (Weapon value : weapon1) {
            warrior1.equipWeapon(value);
        }

        for (Weapon weapon : weapon2) {
            warrior2.equipWeapon(weapon);
        }

        Battle.fight(warrior1,warrior2);
        var test = warrior1.isAlive();

        //then
        assertEquals(expected,test);
    }

    static List<Arguments> testTwoWarriorsFight(){
        return List.of(arguments(new Warrior(), new Vampire(), new Weapon[]{new Weapon(-10, 5, 0, 40, 0,0,0)},new Weapon[]{new Sword()}, true),
                arguments(new Defender(), new Lancer(), new Weapon[]{new Shield()},new Weapon[]{new GreatAxe()}, false),
                arguments(new Healer(), new Knight(), new Weapon[]{new MagicWand()},new Weapon[]{new Katana()}, false),
                arguments(new Defender(), new Vampire(), new Weapon[]{new Shield(),new MagicWand()},new Weapon[]{new Shield(),new Katana()}, false));
    }
}
