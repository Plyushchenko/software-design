public class NoMoveStrategy implements MoveStrategy {
    @Override
    public GameMapMovement getGameMovement() {
        return GameMapMovement.NONE;
    }
}
