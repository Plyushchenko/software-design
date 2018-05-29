package model.gamemap.landscape;

import model.gamemap.GameMapPosition;

import java.util.List;

public class Landscape {
    private final int xSize;
    private final int ySize;
    private final List<List<LandscapeUnit>> landscape;

    public Landscape(List<List<LandscapeUnit>> landscape) {
        this.landscape = landscape;
        xSize = landscape.size();
        ySize = landscape.get(0).size();
    }

    public List<List<LandscapeUnit>> getLandscape() {
        return landscape;
    }

    public LandscapeUnit getLandscapeUnit(GameMapPosition gameMapPosition) {
        int i = gameMapPosition.getX();
        int j = gameMapPosition.getY();
        return landscape.get(i).get(j);
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return ySize;
    }
}
