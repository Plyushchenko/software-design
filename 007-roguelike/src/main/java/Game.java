import sun.management.counter.perf.PerfLongArrayCounter;

import java.util.List;

public class Game {
    private final GameMap gameMap;
    private final Creature player;
    public Game(GameMap gameMap, Creature player) {
        this.gameMap = gameMap;
        this.player = player;
    }

    public void playTurn() {
        player.moveByMoveStrategy();
        player.pickUpArtifacts(gameMap);
        for (Creature bot : gameMap.getBots()) {
            bot.moveByMoveStrategy();
            if (player.isAlive() && player.getGameMapPosition().equals(bot.getGameMapPosition())) {
                player.attack(bot);
                if (bot.isAlive()) {
                    bot.attack(player);
                }
            }
        }
    }

}
