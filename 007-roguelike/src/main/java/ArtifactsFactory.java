import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        Artifact basicDagger = new Artifact(
                "Basic sword",
                GameMapPosition.from2DCoordinates(4, 3),
                Abilities.newBuilder().setAttack(10),
                Slot.RIGHT_HAND,
                ArtifactType.SWORD
        );

    }

}
