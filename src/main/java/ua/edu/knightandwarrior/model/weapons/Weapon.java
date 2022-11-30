package ua.edu.knightandwarrior.model.weapons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Weapon {
    private int healthPoints;
    private int attackPoints;
    private int defensePoints;
    private int vampirismPoints;
    private int healPowerPoints;

}
