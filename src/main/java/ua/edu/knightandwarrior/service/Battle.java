package ua.edu.knightandwarrior.service;

import ua.edu.knightandwarrior.model.Warrior;

public class Battle {
    public static boolean fight(Warrior defender, Warrior attacker){
        while (defender.isAlive() && attacker.isAlive()) {
            defender.attack(attacker);
            if(attacker.isAlive()){
                attacker.attack(defender);
            }
        }
        return defender.isAlive();
    }
}
