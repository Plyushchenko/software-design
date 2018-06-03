package ru.spbau.roguelike.model.gamemap.creature;

import ru.spbau.roguelike.model.modifier.ability.Abilities;
import ru.spbau.roguelike.model.gamemap.GameMap;
import ru.spbau.roguelike.model.gamemap.GameMapPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for creature generation
 */
public class CreaturesFactory {
    public static Player getDefaultPlayer(GameMap gameMap) {
        return new Player(
                gameMap,
                GameMapPosition.from2DCoordinates(4, 4)
        );
    }

    public static List<Bot> getDefaultBots(GameMap gameMap) {
        List<Bot> bots = new ArrayList<>();
        Bot firstBot = new Bot(
                gameMap,
                GameMapPosition.from2DCoordinates(2, 1),
                Abilities.newBuilder().setHealthPoints(100)
        );
        bots.add(firstBot);
        Bot secondBot = new Bot(
                gameMap,
                GameMapPosition.from2DCoordinates(4, 1),
                Abilities.newBuilder().setHealthPoints(40)
        );
        bots.add(secondBot);
        return bots;
    }
}
