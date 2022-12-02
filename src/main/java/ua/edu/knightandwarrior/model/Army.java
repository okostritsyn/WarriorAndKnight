package ua.edu.knightandwarrior.model;

import lombok.extern.slf4j.Slf4j;
import ua.edu.knightandwarrior.model.units.CanHeal;
import ua.edu.knightandwarrior.model.units.IWarrior;
import ua.edu.knightandwarrior.model.units.Warlord;
import ua.edu.knightandwarrior.model.weapons.Weapon;
import ua.edu.knightandwarrior.service.EventListener;
import ua.edu.knightandwarrior.service.EventManager;
import ua.edu.knightandwarrior.service.EventType;

import java.util.*;
import java.util.function.Supplier;

@Slf4j
public class Army implements Iterable<IWarrior> {
    List<IWarrior> troops = new ArrayList<>();
    WarriorInArmy tail;
    Warlord warlord;
    ArmyType type = ArmyType.TROOP;

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<IWarrior> iterator() {
        return new NextAliveIterator(true);
    }

    static class WarriorInArmy implements HasWarriorBehind, HasWarriorAHead, EventListener {
        IWarrior nextWarrior;
        IWarrior warrior;
        IWarrior previousWarrior;
        EventManager events;

        public WarriorInArmy(IWarrior opponent) {
            this.warrior = opponent;
            this.events = new EventManager(EventType.UNIT_DIED);
        }

        @Override
        public IWarrior getWarriorBehind() {
            return this.nextWarrior;
        }

        @Override
        public IWarrior getWarriorAHead() {
            return this.previousWarrior;
        }

        @Override
        public IWarrior getWarrior() {
            return this.warrior;
        }

        private void setWarriorBehind(IWarrior opponent) {
            this.nextWarrior = opponent;
        }

        private void setPreviousWarrior(IWarrior opponent) {
            this.previousWarrior = opponent;
        }

        @Override
        public int getHealth() {
            return warrior.getHealth();
        }

        @Override
        public void attack(IWarrior opponent) {
            warrior.attack(opponent);
        }

        @Override
        public int getAttack() {
            return warrior.getAttack();
        }

        @Override
        public void receiveDamage(int attack) {
            warrior.receiveDamage(attack);

            if (!isAlive()) {
                getEvents().notify(EventType.UNIT_DIED, this);
            }
        }

        @Override
        public void healBy(int healPoints) {
            warrior.healBy(healPoints);
        }

        @Override
        public List<Weapon> getWeapons() {
            return warrior.getWeapons();
        }

        @Override
        public void equipWeapon(Weapon weapon) {
            warrior.equipWeapon(weapon);

            if (!isAlive()) {
                getEvents().notify(EventType.UNIT_DIED, this);
            }
        }

        @Override
        public EventManager getEvents() {
            return this.events;
        }

        @Override
        public String toString() {
            return getWarrior().toString();
        }

        @Override
        public void update(EventType eventType, IWarrior unit) {
            if (eventType == EventType.UNIT_DIED
                    && warrior instanceof CanHeal
                    && unit instanceof HasWarriorAHead diedWarrior
                    && diedWarrior.getWarrior() instanceof CanHeal) {
                var warriorAHead = (HasWarriorAHead) diedWarrior.getWarriorAHead();
                if (warriorAHead != null
                        && warriorAHead.getWarrior().isAlive()
                        && !(warriorAHead.getWarrior() instanceof CanHeal)) {
                    warriorAHead.getWarrior().getEvents().subscribe(EventType.I_NEED_HEALTH, (ua.edu.knightandwarrior.service.EventListener) warrior);
                }
            }
        }
    }

    public Iterator<IWarrior> firstAliveIterator() {
        return new NextAliveIterator();
    }

    class NextAliveIterator implements Iterator<IWarrior> {
        Iterator<IWarrior> iterator;
        IWarrior nextAlive;
        boolean untilUnitDies;

        public NextAliveIterator() {
            iterator = troops.iterator();
            nextAlive = findNewAliveWarrior();
            untilUnitDies = type == ArmyType.TROOP;
        }

        public NextAliveIterator(boolean returnAlwaysNextAlive) {
            iterator = troops.iterator();
            nextAlive = findNewAliveWarrior();
            untilUnitDies = !returnAlwaysNextAlive;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            if ((nextAlive != null) && nextAlive.isAlive()) {
                return true;
            }

            if (untilUnitDies) nextAlive = findNewAliveWarrior();

            return ((nextAlive != null) && nextAlive.isAlive());
        }

        private IWarrior findNewAliveWarrior() {
            IWarrior currChampion = null;
            while (iterator.hasNext()) {
                currChampion = iterator.next();
                if (currChampion.isAlive()) {
                    break;
                }
            }
            return currChampion;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public IWarrior next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            var currWarrior = nextAlive;

            if (!untilUnitDies) nextAlive = findNewAliveWarrior();

            return currWarrior;
            //return type == ArmyType.TROOP ? currWarrior : ((WarriorInArmy) currWarrior).getWarrior();
        }
    }

    public void equipWarriorAtPosition(int position, Weapon weapon) {

        var currWarrior = troops.get(position);
        currWarrior.equipWeapon(weapon);
    }

    public void moveUnits() {
        if (warlord != null) {
            var newArmy = warlord.moveUnits(this);

            changeSubscribeUnitsInArmy(false);

            var deadWarriors = troops.stream()
                    .filter(el-> !(el.isAlive()))
                    .toList();

            troops.clear();
            tail = null;
            warlord = null;

            while (newArmy.hasNext()) {
                addUnit(newArmy.next());
            }
            deadWarriors.forEach(el->addUnit(((IWarriorInArmy) el).getWarrior()));

            initArmy(type);
        }
    }

    public void addUnit(IWarrior warrior) {
        //Only one warload in army
        if (warrior instanceof Warlord && warlord != null) {
            return;
        } else if (warrior instanceof Warlord currWarrior) {
            warlord = currWarrior;
        }

        WarriorInArmy unitInArmy = new WarriorInArmy(warrior);
        if (tail != null) {
            tail.setWarriorBehind(unitInArmy);
            unitInArmy.setPreviousWarrior(tail);
        }
        tail = unitInArmy;
        troops.add(unitInArmy);
    }

    public Army addUnits(Supplier<IWarrior> factory, int quantity) {
        for (int i = 0; i < quantity; i++) {
            addUnit(factory.get());
        }
        return this;
    }

    public void initArmy(ArmyType type) {
        if (this.type != type){
            this.type = type;
            changeSubscribeUnitsInArmy(false);

            for (IWarrior currUnit:troops) {
                ((WarriorInArmy) currUnit).setPreviousWarrior(null);
                ((WarriorInArmy) currUnit).setWarriorBehind(null);
            }
        }
        changeSubscribeUnitsInArmy(this.type == ArmyType.TROOP);
    }

    public int size() {
        return troops.size();
    }

    public boolean isAlive() {
        return !troops.stream().filter(IWarrior::isAlive).toList().isEmpty();
    }

    private void changeSubscribeUnitsInArmy(boolean turnOn) {
        for (IWarrior unit : troops) {
            if (unit instanceof HasWarriorBehind warriorInArmy
                    && warriorInArmy.getWarriorBehind() != null) {
                var nextWarrior = (HasWarriorBehind) warriorInArmy.getWarriorBehind();
                changeSubscribeUnitInArmy(warriorInArmy, nextWarrior, EventType.UNIT_DIED, turnOn);
                if (nextWarrior.getWarrior() instanceof CanHeal) {
                    changeSubscribeUnitInArmy(warriorInArmy.getWarrior(), nextWarrior.getWarrior(), EventType.I_NEED_HEALTH, turnOn);
                }
            }
        }
    }

    private void changeSubscribeUnitInArmy(IWarrior subscriber, IWarrior listener, EventType type, boolean turnOn) {
        if (turnOn) {
            subscriber.getEvents().subscribe(type, (ua.edu.knightandwarrior.service.EventListener) listener);
        } else {
            subscriber.getEvents().unsubscribe(type, (ua.edu.knightandwarrior.service.EventListener) listener);
        }
    }

    private String getContentOfArmy() {
        HashMap<String, Integer> countHashMap = new HashMap<>();

        for (IWarrior warrior : troops) {
            var currentUnit = (HasWarriorBehind) warrior;
            String currTypeOfTroop = currentUnit.getWarrior().getClass().getSimpleName();
            countHashMap.putIfAbsent(currTypeOfTroop, 0);
            countHashMap.put(currTypeOfTroop, countHashMap.get(currTypeOfTroop) + 1);
        }
        StringBuilder mapAsString = new StringBuilder();

        for (Map.Entry<String, Integer> entry : countHashMap.entrySet()) {
            mapAsString.append(countHashMap.get(entry.getKey()))
                    .append(" ")
                    .append(entry.getKey())
                    .append(", ");
        }

        mapAsString.delete(mapAsString.length() - 2, mapAsString.length());
        return mapAsString.toString();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " of " + getContentOfArmy();
    }
}
