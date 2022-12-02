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

class WarlordTest {
    @ParameterizedTest(name = "{index}. Fight {0} fight against {1}, expected result = {2}")
    @MethodSource
    @DisplayName("Two warriors fight")
    void testTwoWarriorsFight(Warrior warrior1, Warrior warrior2, boolean warrior2Check, boolean expected){
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
        return List.of(arguments(new Defender(), new Warlord(),false, false),
                arguments(new Warlord(), new Vampire(),false, true),
                arguments(new Warlord(), new Knight(),false, true));
    }
}
