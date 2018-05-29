package model.modifier.artifact;

import model.gamemap.GameMapPosition;
import model.gamemap.PositionObject;

/**
 * Class to store artifacts with specified position
 */
public class PositionArtifact implements PositionObject {
    private final Artifact artifact;
    private final GameMapPosition gameMapPosition;

    public PositionArtifact(Artifact artifact, GameMapPosition gameMapPosition) {
        this.artifact = artifact;
        this.gameMapPosition = gameMapPosition;
    }

    @Override
    public GameMapPosition getGameMapPosition() {
        return gameMapPosition;
    }

    public Artifact getArtifact() {
        return artifact;
    }
}
