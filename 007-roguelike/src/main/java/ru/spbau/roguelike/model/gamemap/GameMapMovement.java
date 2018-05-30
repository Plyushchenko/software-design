package ru.spbau.roguelike.model.gamemap;

public enum GameMapMovement {
    UP(GameMapPosition.from2DCoordinates(-1, 0)),
    RIGHT(GameMapPosition.from2DCoordinates(0, 1)),
    DOWN(GameMapPosition.from2DCoordinates(1, 0)),
    LEFT(GameMapPosition.from2DCoordinates(0, -1)),
    NONE(GameMapPosition.from2DCoordinates(0, 0));

    private final GameMapPosition gameMapPosition;

    GameMapMovement(GameMapPosition gameMapPosition) {
        this.gameMapPosition = gameMapPosition;
    }

    public GameMapPosition getGameMapPosition() {
        return gameMapPosition;
    }
}
