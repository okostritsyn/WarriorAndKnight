package ua.edu.knightandwarrior.model;

import ua.edu.knightandwarrior.model.units.IHealer;
import ua.edu.knightandwarrior.model.units.IWarrior;
import ua.edu.knightandwarrior.service.EventManager;
import ua.edu.knightandwarrior.service.EventType;

import java.util.*;
import java.util.function.Supplier;

public class Army {
    List<IWarrior> troops = new ArrayList<>();
    WarriorInArmy tail;

    static class WarriorInArmy implements HasWarriorBehind, IWarrior{
        IWarrior nextWarrior;
        IWarrior warrior;

        public WarriorInArmy(IWarrior opponent) {
            this.warrior = opponent;
        }

        @Override
        public IWarrior getWarriorBehind() {
            return this.nextWarrior;
        }

        @Override
        public IWarrior getWarrior() {
            return this.warrior;
        }

        private void setWarriorBehind(IWarrior opponent) {
            this.nextWarrior = opponent;
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
        Iterator<IWarrior> iterator = troops.iterator();
        IWarrior champion;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            if ((champion != null) && champion.isAlive()) {
                return true;
            }
            while (iterator.hasNext()) {
                champion = iterator.next();
                if (champion.isAlive()) return true;
            }
            return false;
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
            return champion;
        }
    }

    public void addUnit(IWarrior warrior){
        WarriorInArmy unitInArmy = new WarriorInArmy(warrior);
        if (tail != null){
            tail.setWarriorBehind(unitInArmy);
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

    public  void initSubscribesInArmy(){
        for (IWarrior unit:troops) {
            if (unit instanceof HasWarriorBehind warriorInArmy){
                var nextWarrior = (HasWarriorBehind) warriorInArmy.getWarriorBehind();
                if (nextWarrior != null && nextWarrior.getWarrior() instanceof IHealer) {
                    unit.getEvents().subscribe(EventType.I_NEED_HEALTH, (ua.edu.knightandwarrior.service.EventListener) nextWarrior.getWarrior());
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
