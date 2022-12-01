package ua.edu.knightandwarrior.model.units;

import ua.edu.knightandwarrior.model.weapons.Weapon;
import ua.edu.knightandwarrior.service.EventListener;
import ua.edu.knightandwarrior.service.EventType;

public class Healer extends Warrior implements CanHeal,EventListener {
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
        return Math.max(0,HEALTH_POINTS + getHealPointsByWeapon()) ;
    }

    @Override
    public void heal(IWarrior unit,int healthPoints){
        unit.healBy(healthPoints);
    }

    @Override
    public void update(EventType eventType, IWarrior unit) {
        if(eventType == EventType.I_NEED_HEALTH && !(unit instanceof CanHeal)){
            heal(unit,getHealthPoints());
        }
    }

    private int getHealPointsByWeapon() {
        return getWeapons().stream().mapToInt(Weapon::getHealPowerPoints).sum();
    }

}
