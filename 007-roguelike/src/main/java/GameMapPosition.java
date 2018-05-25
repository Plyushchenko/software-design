import java.util.Objects;

public class GameMapPosition {
    private final int x;
    private final int y;
    public static final GameMapPosition OUT_OF_GAME_MAP_POSITION = new GameMapPosition(-1, -1);

    public GameMapPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public GameMapPosition compose(GameMapPosition o) {
        return new GameMapPosition(x + o.getX(), y + o.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameMapPosition that = (GameMapPosition) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
