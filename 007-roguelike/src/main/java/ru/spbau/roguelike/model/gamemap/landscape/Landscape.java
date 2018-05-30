package ru.spbau.roguelike.model.gamemap.landscape;

import ru.spbau.roguelike.model.gamemap.GameMapPosition;

import java.util.List;

public class Landscape {
    private final int height;
    private final int width;
    private final List<List<LandscapeUnit>> landscape;

    public Landscape(List<List<LandscapeUnit>> landscape) {
        this.landscape = landscape;
        height = landscape.size();
        width = landscape.get(0).size();
    }

    public List<List<LandscapeUnit>> getLandscape() {
        return landscape;
    }

    public LandscapeUnit getLandscapeUnit(GameMapPosition gameMapPosition) {
        int i = gameMapPosition.getX();
        int j = gameMapPosition.getY();
        return landscape.get(i).get(j);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
