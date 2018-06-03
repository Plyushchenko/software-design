package ru.spbau.roguelike.model.gamemap.landscape;

import ru.spbau.roguelike.model.gamemap.GameMapPosition;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for landscape generation
 */
public class LandscapeFactory {
    private static final String DEFAULT_LANDSCAPE_PATH_AS_STRING = "src/main/resources/landscape.txt";

    public static Landscape readLandscape() throws IOException {
        return LandscapeFactory.readLandscape(DEFAULT_LANDSCAPE_PATH_AS_STRING);
    }

    public static Landscape readLandscape(String landscapePathAsString) throws IOException {
        Path landscapePath = Paths.get(landscapePathAsString);
        List<List<LandscapeUnit>> landscape = new ArrayList<>();
        try {
            List<String> landscapeFileLines = Files.readAllLines(landscapePath);
            for (int i = 0; i < landscapeFileLines.size(); i++) {
                String landscapeFileLine = landscapeFileLines.get(i);
                List<LandscapeUnit> landscapeLine = new ArrayList<>();
                for (int j = 0; j < landscapeFileLine.length(); j++) {
                    LandscapeUnit landscapeUnit = new LandscapeUnit(
                            GameMapPosition.from2DCoordinates(i, j),
                            LandscapeUnitType.fromChar(landscapeFileLine.charAt(j))
                    );
                    landscapeLine.add(landscapeUnit);
                }
                landscape.add(landscapeLine);
            }
        } catch (Exception e) {
            throw new IOException("Could not read a landscape at " + landscapePathAsString, e);
        }
        return new Landscape(landscape);
    }
}
