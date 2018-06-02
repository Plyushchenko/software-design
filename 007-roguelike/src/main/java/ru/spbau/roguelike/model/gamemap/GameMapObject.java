package ru.spbau.roguelike.model.gamemap;

public interface GameMapObject extends PositionObject {
    GameMapPosition getGameMapPosition();

    GameMap getGameMap();
}
