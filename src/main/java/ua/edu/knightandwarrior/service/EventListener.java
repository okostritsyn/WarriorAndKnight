package ua.edu.knightandwarrior.service;

import ua.edu.knightandwarrior.model.units.IWarrior;

public interface EventListener {
    void update(String eventType, IWarrior unit);
}
