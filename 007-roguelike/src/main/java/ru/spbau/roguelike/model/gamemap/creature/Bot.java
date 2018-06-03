package ru.spbau.roguelike.model.gamemap.creature;

import ru.spbau.roguelike.model.modifier.ability.Abilities;
import ru.spbau.roguelike.model.gamemap.GameMap;
import ru.spbau.roguelike.model.gamemap.GameMapMovement;
import ru.spbau.roguelike.model.gamemap.GameMapPosition;

import java.util.Random;

/**
 * Bot
 */
public class Bot extends Creature {
    Bot(GameMap gameMap, GameMapPosition gameMapPosition, Abilities abilities) {
        super(gameMap, gameMapPosition, abilities);
    }

    /**
     * Apply random GameMovement or keep fighting
     */
    @Override
    public void move() {
        if (fightOpponent == null) {
            applyMovement(GameMapMovement.values()[new Random().nextInt(GameMapMovement.values().length)]);
        }

    }
}
