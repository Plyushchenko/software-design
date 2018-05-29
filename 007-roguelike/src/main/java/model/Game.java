package model;

import model.gamemap.creature.Bot;
import model.gamemap.creature.Creature;
import model.gamemap.GameMap;
import model.gamemap.creature.Player;

import java.util.List;

/**
 * Game
 */
public class Game {
    private final GameMap gameMap;
    private final Player player;
    private final List<Bot> bots;

    public Game(GameMap gameMap, Player player, List<Bot> bots) {
        this.gameMap = gameMap;
        this.player = player;
        this.bots = bots;
    }

    public void playTurn() {
        if (player.isAlive()) {
            player.move();
            player.pickUpGameMapArtifacts();
        }
        for (Creature bot : bots) {
            if (!bot.isAlive()) {
                continue;
            }
            bot.move();
            if (player.isAlive() && player.getGameMapPosition().equals(bot.getGameMapPosition())) {
                player.attack(bot);
                if (bot.isAlive()) {
                    bot.attack(player);
                }
            }
        }

    }

    public GameMap getGameMap() {
        return gameMap;
    }
}
