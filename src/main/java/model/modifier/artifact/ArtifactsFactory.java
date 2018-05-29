package model.modifier.artifact;

import model.ability.Abilities;
import model.gamemap.GameMapPosition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArtifactsFactory {
    public static List<PositionArtifact> getDefaultArtifacts() {
        List<PositionArtifact> artifacts = new ArrayList<>();
        Artifact basicSwordArtifact = new Artifact(
                "Basic sword",
                Abilities.newBuilder().setAttack(10),
                Slot.RIGHT_HAND,
                ArtifactType.SWORD
        );
        PositionArtifact basicSword = new PositionArtifact(
                basicSwordArtifact,
                GameMapPosition.from2DCoordinates(4, 3)
        );
        artifacts.add(basicSword);
        Artifact basicDaggerArtifact = new Artifact(
                "Basic dagger",
                Abilities.newBuilder().setAttack(5),
                Slot.LEFT_HAND,
                ArtifactType.DAGGER
        );
        PositionArtifact basicDagger = new PositionArtifact(
                basicDaggerArtifact,
                GameMapPosition.from2DCoordinates(4, 6)
        );
        artifacts.add(basicDagger);
        return artifacts;
    }
}
