package ua.edu.knightandwarrior.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Supplier;

public class Army {
    List<Warrior> troops = new ArrayList<>();

    public Iterator<Warrior> firstAliveIterator(){
        return new FirstAliveIterator();
    }

    class FirstAliveIterator implements Iterator<Warrior>{
        Iterator<Warrior> iterator = troops.iterator();
        Warrior champion;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            if ((champion == null) || !champion.isAlive()){
                if (iterator.hasNext()){
                    champion = iterator.next();
                } else {
                    return false;
                }
            }
            return true;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Warrior next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            return champion;
        }
    }

    private void addUnit(Warrior warrior){
        troops.add(warrior);
    }

    public Army addUnits(Supplier<Warrior> factory, int quantity){
        for (int i = 0; i < quantity; i++){
            addUnit(factory.get());
        }
        return this;
    }

    public int size() {
        return troops.size();
    }

    private String getContentOfArmy(){
        HashMap<String,Integer> countHashMap = new HashMap<>();

        for (Warrior warrior:troops) {
            String currTypeOfTroop = warrior.getClass().getSimpleName();
            if (!countHashMap.containsKey(currTypeOfTroop)){
                countHashMap.put(currTypeOfTroop,0);
            }
            countHashMap.put(currTypeOfTroop,countHashMap.get(currTypeOfTroop)+1);
        }
        StringBuilder mapAsString = new StringBuilder();
        for (String key : countHashMap.keySet()) {
            mapAsString.append(countHashMap.get(key)).append(" ").append(key).append(", ");
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
