package ru.spbau.roguelike.model.gamemap;

import ru.spbau.roguelike.model.gamemap.landscape.Landscape;
import ru.spbau.roguelike.model.gamemap.landscape.LandscapeFactory;
import ru.spbau.roguelike.model.modifier.artifact.ArtifactsFactory;
import ru.spbau.roguelike.model.modifier.artifact.PositionArtifact;

import java.io.IOException;
import java.util.List;

public class GameMapFactory {
    public static GameMap createDefaultGameMap() throws IOException {
        Landscape landscape = LandscapeFactory.readLandscape();
        List<PositionArtifact> positionArtifacts = ArtifactsFactory.getDefaultArtifacts();
        return new GameMap(landscape, positionArtifacts);
    }
}
