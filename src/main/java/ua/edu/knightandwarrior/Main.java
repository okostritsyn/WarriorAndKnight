package ua.edu.knightandwarrior;

import ua.edu.knightandwarrior.model.Knight;
import ua.edu.knightandwarrior.model.Warrior;
import ua.edu.knightandwarrior.service.Battle;

public class Main {
    public static void main(String[] args) {
        Warrior chuck = new Warrior();
        Warrior bruce = new Warrior();
        Warrior carl = new Knight();
        Warrior dave = new Warrior();

        System.out.println("Fight chuck vs bruce "+Battle.fight(chuck,bruce));
        System.out.println("chuck "+chuck);
        System.out.println("bruce "+bruce);

        System.out.println("Fight dave vs carl "+Battle.fight(dave,carl));
        System.out.println("dave "+dave);
        System.out.println("carl "+carl);

    }
}
