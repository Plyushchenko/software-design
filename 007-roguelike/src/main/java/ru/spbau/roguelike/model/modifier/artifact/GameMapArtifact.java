package ru.spbau.roguelike.model.modifier.artifact;

import ru.spbau.roguelike.model.gamemap.GameMap;
import ru.spbau.roguelike.model.gamemap.GameMapObject;
import ru.spbau.roguelike.model.gamemap.GameMapPosition;

/**
 * Class to store artifacts located on a map
 */
public class GameMapArtifact implements GameMapObject {
    private final GameMap gameMap;
    private final GameMapPosition gameMapPosition;
    private final Artifact artifact;

    public GameMapArtifact(GameMap gameMap, GameMapPosition gameMapPosition, Artifact artifact) {
        this.gameMap = gameMap;
        this.gameMapPosition = gameMapPosition;
        this.artifact = artifact;
    }

    public GameMapArtifact(GameMap gameMap, PositionArtifact positionArtifact) {
        this(gameMap, positionArtifact.getGameMapPosition(), positionArtifact.getArtifact());
    }

    @Override
    public GameMapPosition getGameMapPosition() {
        return gameMapPosition;
    }

    @Override
    public GameMap getGameMap() {
        return gameMap;
    }

    public Artifact getArtifact() {
        return artifact;
    }
}
