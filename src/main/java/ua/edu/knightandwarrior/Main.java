package ua.edu.knightandwarrior;

import ua.edu.knightandwarrior.model.*;
import ua.edu.knightandwarrior.service.Battle;

public class Main {
    public static void main(String[] args) {
        var bob = new Defender();
        var mike = new Warrior();
        var rog = new Rookie();
        var lancelot = new Defender();

        System.out.println("Fight Warrior vs Defender "+Battle.fight(mike, bob));
        System.out.println("Defender "+bob.getHealth());
        System.out.println("Warrior "+mike.getHealth());
        System.out.println("Fight Defender vs Rookie "+Battle.fight(lancelot, rog));
    }
}
