package ru.spbau.roguelike.model.gamemap;

import ru.spbau.roguelike.model.gamemap.landscape.Landscape;
import ru.spbau.roguelike.model.modifier.artifact.GameMapArtifact;
import ru.spbau.roguelike.model.modifier.artifact.PositionArtifact;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that stores landscape and artifacts on it
 */
public class GameMap {
    private final Landscape landscape;
    private final List<GameMapArtifact> gameMapArtifacts;

    public GameMap(Landscape landscape,
                   List<PositionArtifact> positionArtifacts) {
        this.landscape = landscape;
        gameMapArtifacts = positionArtifacts.stream()
                .map(x -> new GameMapArtifact(this, x))
                .collect(Collectors.toList());
    }

    public List<GameMapArtifact> getGameMapArtifacts(GameMapPosition gameMapPosition) {
        return gameMapArtifacts.stream()
                .filter(gameMapArtifact -> gameMapArtifact.getGameMapPosition().equals(gameMapPosition))
                .collect(Collectors.toList());
    }

    public List<GameMapArtifact> getGameMapArtifacts() {
        return gameMapArtifacts;
    }


    public Landscape getLandscape() {
        return landscape;
    }

    /**
     * Remove all the artifacts at the given position
     * @return Removed artifacts
     */
    public List<GameMapArtifact> pickUpArtifacts(GameMapPosition gameMapPosition) {
        List<GameMapArtifact> result = getGameMapArtifacts(gameMapPosition);
        gameMapArtifacts.removeAll(result);
        return result;
    }
}
