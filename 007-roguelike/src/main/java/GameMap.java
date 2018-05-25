import java.util.List;
import java.util.stream.Collectors;

public class GameMap {
    private final int height;
    private final int width;
    private final List<Creature> bots;
    private final List<Artifact> artifacts;
    private final List<List<LandscapeUnit>> landscape;
    private final Creature player;

    public GameMap(int height,
                   int width,
                   List<Creature> bots,
                   List<Artifact> artifacts,
                   List<List<LandscapeUnit>> landscape,
                   Creature player) {
        this.height = height;
        this.width = width;
        this.bots = bots;
        this.artifacts = artifacts;
        this.landscape = landscape;
        this.player = player;
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

    public void removeArtifact(Artifact artifact) {
        artifacts.remove(artifact);
    }
}
