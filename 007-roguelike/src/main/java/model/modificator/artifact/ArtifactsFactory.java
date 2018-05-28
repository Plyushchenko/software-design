package model.modificator.artifact;

import model.gamemap.GameMapPosition;
import model.ability.Abilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArtifactsFactory {
    public static List<Artifact> getDefaultArtifacts() throws IOException {
        List<Artifact> artifacts = new ArrayList<>();
        Artifact basicSword = new Artifact(
                "Basic sword",
                GameMapPosition.from2DCoordinates(4, 3),
                Abilities.newBuilder().setAttack(10),
                Slot.RIGHT_HAND,
                ArtifactType.SWORD
        );
        artifacts.add(basicSword);
        Artifact basicDagger = new Artifact(
                "Basic dagger",
                GameMapPosition.from2DCoordinates(4, 6),
                Abilities.newBuilder().setAttack(5),
                Slot.LEFT_HAND,
                ArtifactType.DAGGER
        );
        artifacts.add(basicDagger);
        return artifacts;
    }

}
