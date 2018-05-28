package model;

import model.creature.Creature;
import model.gamemap.GameMap;

public class Game {
    private final GameMap gameMap;

    public Game(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void playTurn() {
        Creature player = gameMap.getPlayer();
        if (player.isAlive()) {
            System.out.println(player.getInventory());
            player.moveByMoveStrategy();
            player.pickUpArtifacts(gameMap);
        }
        for (Creature bot : gameMap.getBots()) {
            if (!bot.isAlive()) {
                continue;
            }
            bot.moveByMoveStrategy();
            if (player.isAlive() && player.getGameMapPosition().equals(bot.getGameMapPosition())) {
                System.out.println("FIGHT");
                player.attack(bot);
                if (bot.isAlive()) {
                    bot.attack(player);
                }
                System.out.println("player = " + player.getAbilities());
                System.out.println("   bot = " + bot.getAbilities());
            }
        }

    }

    public GameMap getGameMap() {
        return gameMap;
    }
}
