package ua.edu.knightandwarrior.model;

import ua.edu.knightandwarrior.model.units.IWarrior;

public interface HasWarriorBehind extends IWarriorInArmy {
    IWarrior getWarriorBehind();
}
