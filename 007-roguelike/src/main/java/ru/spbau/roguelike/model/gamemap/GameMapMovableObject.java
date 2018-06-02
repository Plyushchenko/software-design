package ru.spbau.roguelike.model.gamemap;

/**
 * Object that can be located on a GameMap and move
 */
public interface GameMapMovableObject extends GameMapObject {
    /**
     * Something like a setter for gameMapPosition but with extra checks
     */
    void moveTo(GameMapPosition gameMapPosition);

    default void applyMovement(GameMapMovement gameMapMovement) {
        moveTo(getGameMapPosition().compose(gameMapMovement.getGameMapPosition()));
    }

    void move();
}
