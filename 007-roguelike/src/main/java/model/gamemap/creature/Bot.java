package model.gamemap.creature;

import model.ability.Abilities;
import model.gamemap.GameMap;
import model.gamemap.GameMapMovement;
import model.gamemap.GameMapPosition;

import java.util.Random;

/**
 * Bot
 */
public class Bot extends Creature {
    Bot(GameMap gameMap, GameMapPosition gameMapPosition, Abilities abilities) {
        super(gameMap, gameMapPosition, abilities);
    }

    /**
     * Apply random GameMovement
     */
    @Override
    public void move() {
        applyMovement(GameMapMovement.values()[new Random().nextInt(GameMapMovement.values().length)]);
    }
}
