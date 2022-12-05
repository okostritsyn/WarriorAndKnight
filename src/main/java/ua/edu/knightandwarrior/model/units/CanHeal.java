package ua.edu.knightandwarrior.model.units;

import ua.edu.knightandwarrior.model.weapons.Weapon;
import ua.edu.knightandwarrior.service.EventListener;
import ua.edu.knightandwarrior.service.EventType;

public interface CanHeal extends IWarrior, EventListener {

    default int getHealthPoints() {
        return Math.max(0,getHealthPointsByUnit() + getHealPointsByWeapon()) ;
    }

    int getHealthPointsByUnit();

    default void heal(IWarrior unit,int healthPoints){
        unit.healBy(healthPoints);
    }

    private int getHealPointsByWeapon() {
        return getWeapons().stream().mapToInt(Weapon::getHealPowerPoints).sum();
    }

    @Override
    default void update(EventType eventType, IWarrior unit) {
        if(this.isAlive() && eventType == EventType.I_NEED_HEALTH && !(unit instanceof CanHeal)){
            heal(unit,getHealthPoints());
        }
    }
}
