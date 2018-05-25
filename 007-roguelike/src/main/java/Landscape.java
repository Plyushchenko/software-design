import java.util.List;

public class Landscape {
    private final List<List<LandscapeUnit>> landscape;

    public Landscape(List<List<LandscapeUnit>> landscape) {
        this.landscape = landscape;
    }

    public List<List<LandscapeUnit>> getLandscape() {
        return landscape;
    }

    public LandscapeUnit getLandscapeUnit(GameMapPosition gameMapPosition) {
        int i = gameMapPosition.getX();
        int j = gameMapPosition.getY();
        return landscape.get(i).get(j);
    }
}
