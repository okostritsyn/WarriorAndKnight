package ua.edu.knightandwarrior.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
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

    public Warrior getWarriorByIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size()){
            throw new IndexOutOfBoundsException("Index Out Of Bounds");
        }
        return troops.get(index);
    }

    public int size() {
        return troops.size();
    }

    public boolean isAlive() {
        boolean status = false;
        for (Warrior warrior : troops) {
            if (warrior.isAlive()){
                status = true;
                break;
            }
        }
        return status;
    }
}
