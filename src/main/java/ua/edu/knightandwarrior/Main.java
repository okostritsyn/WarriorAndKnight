package ua.edu.knightandwarrior;

import ua.edu.knightandwarrior.model.*;
import ua.edu.knightandwarrior.model.units.*;
import ua.edu.knightandwarrior.model.weapons.Sword;
import ua.edu.knightandwarrior.model.weapons.Weapon;
import ua.edu.knightandwarrior.service.Battle;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        var bob = new Defender();
        var mike = new Warrior();
        var rog = new Rookie();
        var lancelot = new Defender();
        var vampire  = new Vampire();

        System.out.println("Fight Warrior vs Defender "+Battle.fight(mike, bob));
        System.out.println("Defender "+bob.getHealth());
        System.out.println("Warrior "+mike.getHealth());
        System.out.println("Fight Defender vs Rookie "+Battle.fight(lancelot, rog));

        var myArmy = new Army();
        myArmy.addUnits(Defender::new, 2);
        myArmy.addUnits(Vampire::new, 2);
        myArmy.addUnits(Warrior::new, 1);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Warrior::new, 2);
        enemyArmy.addUnits(Defender::new, 2);
        enemyArmy.addUnits(Vampire::new, 3);

        System.out.println("Fight myArmy vs enemyArmy "+Battle.fight(myArmy, enemyArmy));

        System.out.println("Warrior vs vampire "+Battle.fight(new Warrior(), vampire));
        System.out.println("vampire health"+vampire.getHealth());

        myArmy = new Army();
        myArmy.addUnits(Warrior::new, 1);
        myArmy.addUnits(Healer::new, 1);
        myArmy.addUnits(Healer::new, 1);
        myArmy.addUnits(Defender::new, 1);
        myArmy.addUnits(Healer::new, 1);
        myArmy.addUnits(Knight::new, 1);
        myArmy.addUnits(Warlord::new, 1);

        enemyArmy = new Army();
        enemyArmy.addUnits(Lancer::new, 1);
        enemyArmy.addUnits(Warrior::new, 4);
        enemyArmy.addUnits(Warlord::new, 1);

        System.out.println("Fight myArmy vs enemyArmy "+Battle.fight(myArmy, enemyArmy));

        Weapon sword = new Sword();
        System.out.println("sword.getDefense()" + sword.getDefensePoints());
    }
}
