package model.gamemap.creature;

import model.ability.Abilities;
import model.gamemap.GameMap;
import model.gamemap.GameMapPosition;
import model.modifier.artifact.Inventory;
import ui.KeyboardListener;

import java.util.ArrayList;
import java.util.List;

public class CreaturesFactory {
    public static Player getDefaultPlayer(GameMap gameMap) {
        return new Player(
                gameMap,
                GameMapPosition.from2DCoordinates(4, 4),
                new KeyboardListener(),
                new Inventory()
        );
    }

    public static List<Bot> getDefaultBots(GameMap gameMap) {
        List<Bot> bots = new ArrayList<>();
        Bot firstBot = new Bot(
                gameMap,
                GameMapPosition.from2DCoordinates(2, 1),
                Abilities.newBuilder().setHealthPoints(30)
        );
        bots.add(firstBot);
        Bot secondBot = new Bot(
                gameMap,
                GameMapPosition.from2DCoordinates(4, 1),
                Abilities.newBuilder().setHealthPoints(30)
        );
        bots.add(secondBot);
        return bots;
    }
}
