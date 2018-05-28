package ui;

import asciiPanel.*;
import model.*;
import model.modificator.artifact.Artifact;
import model.modificator.artifact.ArtifactType;
import model.creature.Creature;
import model.gamemap.GameMapPosition;
import model.landscape.Landscape;
import model.movestrategy.KeyboardListener;

import javax.swing.*;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame {
    private AsciiPanel panel;
    private int mapWidth;
    private Game game;
    private GameFrameState state;

    public GameFrame(Game game) {
        this.game = game;
        state = GameFrameState.GAME_MAP;
        //panel = new AsciiPanel(20, 40);
        panel = new AsciiPanel(
                game.getGameMap().getLandscape().getXSize(),
                game.getGameMap().getLandscape().getYSize()
        );
        add(panel);
        pack();
        ((KeyboardListener) game.getGameMap().getPlayer().getMoveStrategy()).setGameFrame(this);
        addKeyListener((KeyListener) game.getGameMap().getPlayer().getMoveStrategy());
        /*
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 40; j++) {
                panel.write((char)('a' + i), i, j);
            }
        }
        */
    }

    public void draw() {
        if (state == GameFrameState.GAME_MAP) {
            drawGameMap();
        } else if (state == GameFrameState.INVENTORY) {
            drawInventory();
        }
    }

    private void drawInventory() {
        System.out.println("DRAW INV");
        panel.clear();
        panel.repaint();

        String[] strings = game.getGameMap().getPlayer().getInventory().toString().split("\n");
        int cnt = 0;
        for (String s: strings) {
            panel.write(s, 0, cnt++);
        }
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
        GameMapPosition playerMapPosition = game.getGameMap().getPlayer().getGameMapPosition();
        System.out.println(playerMapPosition.getY() + " " + playerMapPosition.getX());
        panel.write('P', playerMapPosition.getY(), playerMapPosition.getX());
        for (Creature bot: game.getGameMap().getBots()) {
            if (bot.isAlive()) {
                panel.write('B', bot.getGameMapPosition().getY(), bot.getGameMapPosition().getX());
            }
        }
        for (Artifact artifact: game.getGameMap().getArtifacts()) {
            ArtifactType artifactType = artifact.getArtifactType();
            GameMapPosition artifactMapPosition = artifact.getGameMapPosition();
            int x = artifactMapPosition.getX();
            int y = artifactMapPosition.getY();
            panel.write(artifactType.getCharValue(), y, x);
        }
        System.out.println("Inventory:\n" + game.getGameMap().getPlayer().getInventory());
    }

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
}