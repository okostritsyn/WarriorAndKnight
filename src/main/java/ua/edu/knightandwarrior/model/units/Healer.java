package ua.edu.knightandwarrior.model.units;

import ua.edu.knightandwarrior.service.EventListener;

public class Healer extends Warrior implements IHealer,EventListener {
    private static final int ATTACK=0;
    private static final int HEALTH_POINTS=2;

    public Healer() {
        super(60);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    public int getHealthPoints() {
        return HEALTH_POINTS;
    }

    @Override
    public void heal(IWarrior unit,int healthPoints){
        unit.healBy(healthPoints);
    }

    @Override
    public void update(String eventType, IWarrior unit) {
        if(eventType.equals("INeedHealth")){
            heal(unit,getHealthPoints());
        }
    }
}
