package model.creature;

import model.ability.Abilities;
import model.gamemap.GameMapPosition;
import model.movestrategy.KeyboardListener;
import model.movestrategy.NoMoveStrategy;
import model.pickupstrategy.NoPickUpStrategy;
import model.pickupstrategy.PickUpEverythingStrategy;
import utils.Pair;

import java.util.ArrayList;
import java.util.List;

public class CreaturesFactory {
    public static Pair<List<Creature>, Creature> getDefaultCreatures() {
        List<Creature> bots = new ArrayList<>();
        Creature firstBot = new Creature(
                new NoMoveStrategy(),
                new NoPickUpStrategy(),
                GameMapPosition.from2DCoordinates(1, 1),
                Abilities.newBuilder().setHealthPoints(30)
        );
        bots.add(firstBot);
        Creature secondBot = new Creature(
                new NoMoveStrategy(),
                new NoPickUpStrategy(),
                GameMapPosition.from2DCoordinates(4, 1),
                Abilities.newBuilder().setHealthPoints(30)
        );
        bots.add(secondBot);
        Creature player = new Creature(
                new KeyboardListener(),
                new PickUpEverythingStrategy(),
                GameMapPosition.from2DCoordinates(4, 4),
                Abilities.newBuilder().setAttack(10).setDefence(10).setHealthPoints(10)
        );
        return new Pair<>(bots, player);
    }
}
