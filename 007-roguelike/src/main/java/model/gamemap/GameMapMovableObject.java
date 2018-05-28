package model.gamemap;

public interface GameMapMovableObject extends GameMapObject {
    void moveTo(GameMapPosition gameMapPosition);

    default void applyMovement(GameMapMovement gameMapMovement) {
        System.out.println("MOVEMENT " + gameMapMovement);
        System.out.println("POSITION " + getGameMapPosition());
        moveTo(getGameMapPosition().compose(gameMapMovement.getGameMapPosition()));
    }

    void moveByMoveStrategy();
}
