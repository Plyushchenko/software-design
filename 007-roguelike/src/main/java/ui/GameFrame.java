package ui;

import asciiPanel.AsciiPanel;
import model.Game;
import model.gamemap.GameMapPosition;
import model.gamemap.creature.Bot;
import model.gamemap.creature.Creature;
import model.gamemap.creature.Player;
import model.gamemap.landscape.Landscape;
import model.modifier.artifact.ArtifactType;
import model.modifier.artifact.GameMapArtifact;

import javax.swing.*;
import java.util.List;

/**
 * GUI class
 */
public class GameFrame extends JFrame {
    private AsciiPanel panel;
    private final Game game;
    private final Player player;
    private final List<Bot> bots;
    private State state;

    public GameFrame(Game game, Player player, List<Bot> bots) {
        this.game = game;
        state = State.GAME_MAP;
        panel = new AsciiPanel(
                game.getGameMap().getLandscape().getXSize(),
                game.getGameMap().getLandscape().getYSize()
        );
        add(panel);
        addKeyListener(player.getKeyboardListener());
        pack();
        this.player = player;
        this.bots = bots;
        drawGameMap();
    }

    public void draw() {
        if (state == State.GAME_MAP) {
            drawGameMap();
        } else if (state == State.INVENTORY) {
            drawInventory();
        }
    }

    private void drawInventory() {
        System.out.println("DRAW INV");
        /*
        panel.clear();
        panel.repaint();

        String[] strings = game.getGameMap().getPlayer().getInventory().toString().split("\n");
        int cnt = 0;
        for (String s: strings) {
            panel.write(s, 0, cnt++);
        }*/
    }

    private void drawGameMap() {
        System.out.println("DRAW MAP");
        panel.clear();
        panel.repaint();
        Landscape landscape = game.getGameMap().getLandscape();
        for (int i = 0; i < landscape.getXSize(); i++) {
            for (int j = 0; j < landscape.getYSize(); j++) {
                GameMapPosition gameMapPosition = GameMapPosition.from2DCoordinates(i, j);
                panel.write(
                        landscape.getLandscapeUnit(gameMapPosition).getLandscapeUnitType().getCharValue(),
                        j,
                        i
                );
            }
        }
        GameMapPosition playerMapPosition = player.getGameMapPosition();
        System.out.println(playerMapPosition.getY() + " " + playerMapPosition.getX());
        panel.write('P', playerMapPosition.getY(), playerMapPosition.getX());
        for (Creature bot: bots) {
            if (bot.isAlive()) {
                panel.write('B', bot.getGameMapPosition().getY(), bot.getGameMapPosition().getX());
            }
        }
        for (GameMapArtifact gameMapArtifact: game.getGameMap().getGameMapArtifacts()) {
            ArtifactType artifactType = gameMapArtifact.getArtifact().getArtifactType();
            GameMapPosition artifactMapPosition = gameMapArtifact.getGameMapPosition();
            int x = artifactMapPosition.getX();
            int y = artifactMapPosition.getY();
            panel.write(artifactType.getCharValue(), y, x);
        }
    }

    /*
    public void changeState() {
        if (state == GameFrameState.GAME_MAP) {
            System.out.println("here");
            state = GameFrameState.INVENTORY;
            remove(panel);
            panel = new AsciiPanel(
                    game.getGameMap().getLandscape().getXSize() * 10,
                    game.getGameMap().getLandscape().getYSize() * 10
            );
            add(panel);
            pack();
        } else {
            state = GameFrameState.GAME_MAP;
            remove(panel);
            panel = new AsciiPanel(
                    game.getGameMap().getLandscape().getXSize(),
                    game.getGameMap().getLandscape().getYSize()
            );
            add(panel);
            pack();
        }
    }
    */
}