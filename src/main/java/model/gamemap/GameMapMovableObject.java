package model.gamemap;

public interface GameMapMovableObject extends GameMapObject {
    void moveTo(GameMapPosition gameMapPosition);

    default void applyMovement(GameMapMovement gameMapMovement) {
        moveTo(getGameMapPosition().compose(gameMapMovement.getGameMapPosition()));
    }

    void move();
}
