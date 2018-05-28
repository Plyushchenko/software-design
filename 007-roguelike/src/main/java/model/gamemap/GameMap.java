package model.gamemap;

import model.landscape.Landscape;
import model.modificator.artifact.Artifact;
import model.creature.Creature;

import java.util.List;
import java.util.stream.Collectors;

public class GameMap {
    private final Landscape landscape;
    private final List<Creature> bots;
    private final Creature player;
    private final List<Artifact> artifacts;

    public GameMap(Landscape landscape,
                   List<Creature> bots,
                   Creature player,
                   List<Artifact> artifacts) {
        this.landscape = landscape;
        this.bots = bots;
        this.player = player;
        this.artifacts = artifacts;
    }

    public List<Creature> getBots() {
        return bots;
    }

    public Creature getPlayer() {
        return player;
    }

    public List<Artifact> getArtifacts(GameMapPosition gameMapPosition) {
        return artifacts.stream()
                .filter(artifact -> artifact.getGameMapPosition().equals(gameMapPosition))
                .collect(Collectors.toList());
    }

    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public void removeArtifact(Artifact artifact) {
        artifacts.remove(artifact);
    }

    public Landscape getLandscape() {
        return landscape;
    }
}
