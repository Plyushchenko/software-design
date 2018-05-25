import java.util.List;
import java.util.stream.Collectors;

public class GameMap {
    private final int height;
    private final int width;
    private final List<GameMapObject> gameMapObjects;
    private final List<Creature> bots;
    private final List<Artifact> artifacts;
    private final Creature player;
    
    public GameMap(int height, int width) {
        this.height = height;
        this.width = width;
        //Генерация карты
        throw new UnsupportedOperationException();
    }
    
    public GameMap(String pathAsString) {
        //Чтение карты из файла
        throw new UnsupportedOperationException();        
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
