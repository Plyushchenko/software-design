package ru.spbau.roguelike.model.gamemap;

/**
 * Object located on a game map
 */
public interface GameMapObject extends PositionObject {
    GameMapPosition getGameMapPosition();

    GameMap getGameMap();
}
