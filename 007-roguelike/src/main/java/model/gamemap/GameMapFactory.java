package model.gamemap;

import model.landscape.Landscape;
import model.landscape.LandscapeFactory;
import utils.Pair;
import model.modificator.artifact.Artifact;
import model.modificator.artifact.ArtifactsFactory;
import model.creature.Creature;
import model.creature.CreaturesFactory;

import java.io.IOException;
import java.util.List;

public class GameMapFactory {
    public static GameMap createDefaultGameMap() throws IOException {
        Landscape landscape = LandscapeFactory.readLandscape();
        Pair<List<Creature>, Creature> botsAndPlayer = CreaturesFactory.getDefaultCreatures();
        List<Creature> bots = botsAndPlayer.getFirst();
        Creature player = botsAndPlayer.getSecond();
        List<Artifact> artifacts = ArtifactsFactory.getDefaultArtifacts();
        return new GameMap(landscape, bots, player, artifacts);
    }
}
