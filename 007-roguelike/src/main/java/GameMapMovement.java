public enum GameMapMovement {
    UP(new GameMapPosition(0, -1)),
    RIGHT(new GameMapPosition(1, 0)),
    DOWN(new GameMapPosition(0, 1)),
    LEFT(new GameMapPosition(-1, 0)),
    NONE(new GameMapPosition(0, 0));

    private final GameMapPosition gameMapPosition;

    GameMapMovement(GameMapPosition gameMapPosition) {
        this.gameMapPosition = gameMapPosition;
    }

    public GameMapPosition getGameMapPosition() {
        return gameMapPosition;
    }
}
