package ua.edu.knightandwarrior.model;

import ua.edu.knightandwarrior.model.units.IWarrior;
import ua.edu.knightandwarrior.service.EventManager;

public interface IWarriorInArmy extends IWarrior {
    IWarrior getWarrior();

    EventManager getEvents();
}
