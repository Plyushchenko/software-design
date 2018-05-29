package model.gamemap;

import model.gamemap.landscape.Landscape;
import model.gamemap.landscape.LandscapeFactory;
import model.modifier.artifact.ArtifactsFactory;
import model.modifier.artifact.PositionArtifact;

import java.io.IOException;
import java.util.List;

public class GameMapFactory {
    public static GameMap createDefaultGameMap() throws IOException {
        Landscape landscape = LandscapeFactory.readLandscape();
        List<PositionArtifact> positionArtifacts = ArtifactsFactory.getDefaultArtifacts();
        return new GameMap(landscape, positionArtifacts);
    }
}
