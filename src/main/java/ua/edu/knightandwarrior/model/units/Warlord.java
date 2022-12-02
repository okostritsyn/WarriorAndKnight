package ua.edu.knightandwarrior.model.units;

import ua.edu.knightandwarrior.model.IWarriorInArmy;

import java.util.ArrayList;
import java.util.Iterator;

public class Warlord extends Defender {
    private static final int ATTACK=4;

    public Warlord() {
        super(100);
    }

    @Override
    public int getAttack() {
        return Math.max(0,ATTACK + getAttackByWeapon());
    }

    public Iterator<IWarrior> moveUnits(Iterable<IWarrior> army){
        var list = new ArrayList<IWarrior>();

        for (var warrior:army) {
            list.add(((IWarriorInArmy) warrior).getWarrior());
        }
        var lancers = list.stream()
                .filter(Lancer.class::isInstance)
                .toList();

        var healers = list.stream()
                .filter(Healer.class::isInstance)
                .toList();

        var warlords = list.stream()
                .filter(Warlord.class::isInstance)
                .toList();

        var fighters = list.stream()
                .filter(el -> !(el instanceof Lancer
                || el instanceof CanHeal
                || el instanceof Warlord))
                .toList();

        var newArmy = new ArrayList<IWarrior>();

        lancers.stream().limit(1).findFirst().ifPresent(newArmy::add);
        if (newArmy.isEmpty()){
            fighters.stream().limit(1).findFirst().ifPresent(newArmy::add);
        }
        var fighterOnFirstPosition = newArmy.isEmpty()?null:newArmy.get(0);

        newArmy.addAll(healers);
        lancers.stream().filter(el -> el != fighterOnFirstPosition).forEach(newArmy::add);
        fighters.stream().filter(el -> el != fighterOnFirstPosition).forEach(newArmy::add);
        newArmy.addAll(warlords);

        return newArmy.iterator();
    }
}
