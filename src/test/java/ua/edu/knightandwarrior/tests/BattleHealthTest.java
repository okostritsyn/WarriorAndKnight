package ua.edu.knightandwarrior.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.edu.knightandwarrior.model.units.*;
import ua.edu.knightandwarrior.service.Battle;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BattleHealthTest {
    @ParameterizedTest(name = "{index}. Fight {0} fight against {1}, expected result = {2} and expected result = {3}")
    @MethodSource
    @DisplayName("Two warriors fight")
    void testTwoWarriorsFight(Warrior warrior1, Warrior warrior2, int expectedHeatlhUnit1, int expectedHeatlhUnit2){
        //when
        Battle.fight(warrior1,warrior2);

        //then
        assertEquals(expectedHeatlhUnit1,warrior1.getHealth());
        assertEquals(expectedHeatlhUnit2,warrior2.getHealth());
    }

    static List<Arguments> testTwoWarriorsFight(){
        return List.of(arguments(new Warrior(), new Knight(),-6, 10),
                arguments(new Knight(), new Warrior(),15, -6),
                arguments(new Warrior(), new Defender(),-1, 9),
                arguments(new Defender(), new Warrior(),12, -1),
                arguments(new Defender(), new Vampire(),22, -1),
                arguments(new Vampire(), new Defender(),-1, 20),
                arguments(new Defender(), new Lancer(),0, 5),
                arguments(new Lancer(), new Defender(),8, 0),
                arguments(new Warrior(), new Lancer(),-4, 5),
                arguments(new Lancer(), new Warrior(),10, -4),
                arguments(new Healer(), new Warrior(),0, 50));
    }
}
