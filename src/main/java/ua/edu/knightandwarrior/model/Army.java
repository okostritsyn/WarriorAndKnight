package ua.edu.knightandwarrior.model;

import ua.edu.knightandwarrior.model.units.CanHeal;
import ua.edu.knightandwarrior.model.units.IWarrior;
import ua.edu.knightandwarrior.model.units.Warlord;
import ua.edu.knightandwarrior.model.weapons.Weapon;
import ua.edu.knightandwarrior.service.EventListener;
import ua.edu.knightandwarrior.service.EventManager;
import ua.edu.knightandwarrior.service.EventType;

import java.util.*;
import java.util.function.Supplier;

public class Army implements Iterable<IWarrior>, EventListener {
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
        return firstAliveIterator();
    }

    @Override
    public void update(EventType eventType, IWarrior unit) {
        moveUnits();
    }

    static class WarriorInArmy implements HasWarriorBehind, HasWarriorAHead , IWarrior {
        IWarrior nextWarrior;
        IWarrior warrior;
        IWarrior previousWarrior;

        public WarriorInArmy(IWarrior opponent) {
            this.warrior = opponent;
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
        public int getZeroHealth() {
            return warrior.getZeroHealth();
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
        }

        @Override
        public EventManager getEvents() {
            return warrior.getEvents();
        }

        @Override
        public String toString() {
            return getWarrior().toString();
        }
    }

    public Iterator<IWarrior> firstAliveIterator(){
        return new FirstAliveIterator();
    }

    class FirstAliveIterator implements Iterator<IWarrior>{
        Iterator<IWarrior> iterator;
        IWarrior nextAlive;

        public FirstAliveIterator() {
            iterator = troops.iterator();
            nextAlive = findNewAliveWarrior();
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

            if (type == ArmyType.TROOP) nextAlive = findNewAliveWarrior();

            return ((nextAlive != null) && nextAlive.isAlive());
        }

        private IWarrior findNewAliveWarrior(){
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
            if (!hasNext()){
                throw new NoSuchElementException();
            }

            var currWarrior = nextAlive;

            if (type == ArmyType.LINE) nextAlive = findNewAliveWarrior();

            return type == ArmyType.TROOP? currWarrior : ((WarriorInArmy) currWarrior).getWarrior();
        }
    }

    public void equipWarriorAtPosition(int position, Weapon weapon){

        var currWarrior = troops.get(position);
        currWarrior.equipWeapon(weapon);
    }

    public void moveUnits(){
        if (warlord != null){
            warlord.moveUnits(this);
        }
    }

    public void addUnit(IWarrior warrior){
        //Only one warload in army
        if (warrior instanceof Warlord && warlord != null){
            return;
        } else if (warrior instanceof Warlord) {
            warlord = (Warlord) warrior;
        }

        WarriorInArmy unitInArmy = new WarriorInArmy(warrior);
        if (tail != null){
            tail.setWarriorBehind(unitInArmy);
            unitInArmy.setPreviousWarrior(tail);
        }
        tail = unitInArmy;
        troops.add(unitInArmy);
    }

    public Army addUnits(Supplier<IWarrior> factory, int quantity){
        for (int i = 0; i < quantity; i++){
            addUnit(factory.get());
        }
        return this;
    }

    public void initArmy(ArmyType type){
        this.type = type;
        changeSubscribeUnitsInArmy(this.type == ArmyType.TROOP);
    }

    public int size(){
       return troops.size();
    }


    private void changeSubscribeUnitsInArmy(boolean turnOn){
        for (IWarrior unit:troops) {
            if (turnOn) {
                unit.getEvents().subscribe(EventType.UNIT_DIED, this);
            } else {
                unit.getEvents().unsubscribe(EventType.UNIT_DIED, this);
            }

            if (unit instanceof HasWarriorBehind warriorInArmy){
                var nextWarrior = (HasWarriorBehind) warriorInArmy.getWarriorBehind();
                if (nextWarrior != null && nextWarrior.getWarrior() instanceof CanHeal) {
                    if (turnOn) {
                        unit.getEvents().subscribe(EventType.I_NEED_HEALTH, (ua.edu.knightandwarrior.service.EventListener) nextWarrior.getWarrior());
                    } else {
                        unit.getEvents().unsubscribe(EventType.I_NEED_HEALTH, (ua.edu.knightandwarrior.service.EventListener) nextWarrior.getWarrior());
                    }
                }
            }
        }
    }

    private String getContentOfArmy(){
        HashMap<String,Integer> countHashMap = new HashMap<>();

        for (IWarrior warrior:troops) {
            var currentUnit = (HasWarriorBehind) warrior;
            String currTypeOfTroop = currentUnit.getWarrior().getClass().getSimpleName();
            countHashMap.putIfAbsent(currTypeOfTroop,0);
            countHashMap.put(currTypeOfTroop,countHashMap.get(currTypeOfTroop)+1);
        }
        StringBuilder mapAsString = new StringBuilder();

        for (Map.Entry<String, Integer> entry : countHashMap.entrySet())
        {
            mapAsString.append(countHashMap.get(entry.getKey()))
                    .append(" ")
                    .append(entry.getKey())
                    .append(", ");
        }

        mapAsString.delete(mapAsString.length()-2, mapAsString.length());
        return mapAsString.toString();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " of " + getContentOfArmy();
    }
}
