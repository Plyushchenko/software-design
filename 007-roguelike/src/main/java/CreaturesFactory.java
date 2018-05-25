import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CreaturesFactory {
    public static Pair<List<Creature>,Creature> readCreatures(String creaturesPathAsString) throws IOException {
        Path creaturesPath = Paths.get(creaturesPathAsString);
        List<Creature> bots = new ArrayList<>();
        Creature player = null;
        try {
            List<String> creaturesFileLines = Files.readAllLines(creaturesPath);
            for (int i = 0; i < creaturesFileLines.size(); i++) {
                String creaturesFileLine = creaturesFileLines.get(i);
                for (int j = 0; j < creaturesFileLine.length(); j++) {
                    CreatureType creatureType = CreatureType.fromChar(creaturesFileLine.charAt(j));
                    switch (creatureType) {
                        case PLAYER:
                            if (player == null) {
                                MoveStrategy keyboardMoveStrategy = new KeyBoardMoveStrategy();
                                PickUpStrategy pickUpEverythingStrategy = new PickUpEverythingStrategy();
                                Abilities abilities = Abilities.newBuilder()
                                        .setAttack(10)
                                        .setDefence(10)
                                        .setHealthPoints(10);
                                player = new Creature(
                                        keyboardMoveStrategy,
                                        pickUpEverythingStrategy,
                                        GameMapPosition.from2DCoordinates(i, j),
                                        abilities
                                );
                            } else {
                                throw new IllegalArgumentException("Only one player is allowed");
                            }
                            break;
                        case BOT:
                            MoveStrategy noMoveStrategy = new NoMoveStrategy();
                            PickUpStrategy noPickUpStrategy = new NoPickUpStrategy();
                            Abilities abilities = Abilities.newBuilder().setAttack(0).setDefence(0).setHealthPoints(50);
                            Creature bot = new Creature(
                                    noMoveStrategy,
                                    noPickUpStrategy,
                                    GameMapPosition.from2DCoordinates(i, j),
                                    abilities
                            );
                            bots.add(bot);
                            break;
                        case NO_CREATURE:
                            break;
                        default:
                            throw new IllegalArgumentException("Unknown creatureType: " + creatureType);
                    }
                }
            }
        } catch (Exception e) {
            throw new IOException("Could not read creatures at " + creaturesPathAsString, e);
        }
        if (player == null) {
            throw new IllegalArgumentException("No player found");
        }
        return new Pair<>(bots, player);
    }
}
