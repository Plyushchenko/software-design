import java.io.IOException;
import java.util.List;

public class GameMapFactory {

    public static GameMap createGameMap(String landscapePathAsString, String creaturesPathAsString) throws IOException {
        Landscape landscape = LandscapeFactory.readLandscape(landscapePathAsString);
        Pair<List<Creature>, Creature> botsAndPlayer = CreaturesFactory.readCreatures(creaturesPathAsString);
        List<Creature> bots = botsAndPlayer.getFirst();
        Creature player = botsAndPlayer.getSecond();

        return null;
    }
}
