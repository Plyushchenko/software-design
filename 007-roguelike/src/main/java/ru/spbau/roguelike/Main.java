package ru.spbau.roguelike;

import ru.spbau.roguelike.model.Game;
import ru.spbau.roguelike.model.gamemap.GameMap;
import ru.spbau.roguelike.model.gamemap.GameMapFactory;
import ru.spbau.roguelike.model.gamemap.creature.Bot;
import ru.spbau.roguelike.model.gamemap.creature.CreaturesFactory;
import ru.spbau.roguelike.model.gamemap.creature.Player;
import ru.spbau.roguelike.model.modifier.artifact.Inventory;
import ru.spbau.roguelike.ui.GameFrame;
import ru.spbau.roguelike.ui.InventoryCursor;
import ru.spbau.roguelike.ui.KeyboardListener;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            GameMap gameMap = GameMapFactory.createDefaultGameMap();
            Player player = CreaturesFactory.getDefaultPlayer(gameMap);
            List<Bot> bots = CreaturesFactory.getDefaultBots(gameMap);
            Game game = new Game(gameMap, player, bots);
            GameFrame gameFrame = new GameFrame(game);
            gameFrame.draw();
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameFrame.setVisible(true);
            while (player.isAlive()) {
                game.playTurn();
                gameFrame.draw();
                Thread.sleep(500);
            }
            gameFrame.setVisible(false);
            gameFrame.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}