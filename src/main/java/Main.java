import model.Game;
import model.gamemap.GameMap;
import model.gamemap.GameMapFactory;
import model.gamemap.creature.Bot;
import model.gamemap.creature.CreaturesFactory;
import model.gamemap.creature.Player;
import ui.GameFrame;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        GameMap gameMap = GameMapFactory.createDefaultGameMap();
        Player player = CreaturesFactory.getDefaultPlayer(gameMap);
        List<Bot> bots = CreaturesFactory.getDefaultBots(gameMap);
        Game game = new model.Game(gameMap, player, bots);
        GameFrame gameFrame = new GameFrame(game, player, bots);
        gameFrame.draw();
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);
        while (player.isAlive()) {
            game.playTurn();
            gameFrame.draw();
            Thread.sleep(500);
        }

    }
}