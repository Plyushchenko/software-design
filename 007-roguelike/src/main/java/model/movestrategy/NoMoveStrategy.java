package model.movestrategy;

import model.gamemap.GameMapMovement;

public class NoMoveStrategy implements MoveStrategy {
    @Override
    public GameMapMovement getGameMovement() {
        return GameMapMovement.NONE;
    }
}
