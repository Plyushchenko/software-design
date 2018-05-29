package ui;

import asciiPanel.AsciiPanel;
import model.Game;
import model.ability.Abilities;
import model.ability.AbilityName;
import model.gamemap.GameMapPosition;
import model.gamemap.creature.Bot;
import model.gamemap.creature.Creature;
import model.gamemap.creature.Player;
import model.gamemap.landscape.Landscape;
import model.modifier.artifact.*;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * GUI class
 */
public class GameFrame extends JFrame {
    private AsciiPanel panel;
    private final Game game;
    private final Player player;
    private final List<Bot> bots;
    private final KeyboardListener keyboardListener;
    private final InventoryCursor inventoryCursor;

    public GameFrame(Game game, Player player, List<Bot> bots) {
        this.game = game;
        panel = new AsciiPanel();
        add(panel);
        this.keyboardListener = player.getKeyboardListener();
        inventoryCursor = keyboardListener.getInventoryCursor();
        keyboardListener.setGameFrame(this);
        addKeyListener(keyboardListener);
        pack();
        this.player = player;
        this.bots = bots;
        draw();
    }

    public void draw() {
        remove(panel);
        switch (keyboardListener.getState()) {
            case GAME_MAP:
                drawGameMap();
                break;
            case INVENTORY:
                drawInventory();
                break;
        }
        add(panel);
        pack();
    }

    private void drawInventory() {
        Inventory inventory = player.getInventory();
        Map<Slot, Artifact> activeArtifacts = inventory.getActiveArtifacts();
        Set<Artifact> inactiveArtifacts = inventory.getInactiveArtifacts();
        panel = createPanel(inventory);
        int row = 0;
        panel.write("ACTIVE", 0, row++);
        int rowOffset = 1;
        for (Slot slot: activeArtifacts.keySet()) {
            String current = " ";
            if (inventoryCursor.getBlock() == 0) {
                if (inventoryCursor.getNumber() == row - rowOffset) {
                    current = "X";
                }
            }
            panel.write(current + " " + activeArtifacts.get(slot).getName() + " " + slot, 0, row++);
        }
        row++;
        panel.write("INACTIVE", 0, row++);
        rowOffset = row;
        for (Artifact artifact: inactiveArtifacts) {
            String current = " ";
            if (inventoryCursor.getBlock() == 1) {
                if (inventoryCursor.getNumber() == row - rowOffset) {
                    current = "X";
                }
            }
            panel.write(current + " " + artifact.getName(), 0, row++);
        }
    }

    private AsciiPanel createPanel(Inventory inventory) {
        Map<Slot, Artifact> activeArtifacts = inventory.getActiveArtifacts();
        Set<Artifact> inactiveArtifacts = inventory.getInactiveArtifacts();
        int panelWidth = 28;
        int panelHeight = 3 + activeArtifacts.size() + inactiveArtifacts.size();
        return new AsciiPanel(panelWidth, panelHeight);
    }

    private void drawGameMap() {
        Abilities abilities = player.getAbilities();
        Map<AbilityName, Integer> abilitiesAsMap = abilities.getAsMap();
        panel = createPanel(abilitiesAsMap);
        Landscape landscape = game.getGameMap().getLandscape();
        for (int i = 0; i < landscape.getHeight(); i++) {
            for (int j = 0; j < landscape.getWidth(); j++) {
                GameMapPosition gameMapPosition = GameMapPosition.from2DCoordinates(i, j);
                panel.write(
                        landscape.getLandscapeUnit(gameMapPosition).getLandscapeUnitType().getCharValue(),
                        j,
                        i
                );
            }
        }
        GameMapPosition playerMapPosition = player.getGameMapPosition();
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

        int column = game.getGameMap().getLandscape().getWidth() + 1;
        int row = writeAbilities(panel, 0, column, abilitiesAsMap);
        Creature fightOpponent = player.getFightOpponent();
        if (fightOpponent != null) {
            Map<AbilityName, Integer> fightOpponentAbilitiesAsMap = fightOpponent.getAbilities().getAsMap();
            row++;
            panel.write("FIGHT", game.getGameMap().getLandscape().getWidth() + 1, row++);
            writeAbilities(panel, row, column, fightOpponentAbilitiesAsMap);
        }
    }

    private int writeAbilities(AsciiPanel panel, int row, int column, Map<AbilityName,Integer> abilitiesAsMap) {
        for (AbilityName abilityName: abilitiesAsMap.keySet()) {
            panel.write(abilityName.getShortName() + " " + abilitiesAsMap.get(abilityName),
                    column,
                    row++
            );
        }
        return row;
    }

    private AsciiPanel createPanel(Map<AbilityName, Integer> abilitiesAsMap) {
        int panelWidth;
        int panelHeight;
        Creature fightOpponent = player.getFightOpponent();
        panelWidth = calcPanelWidth(abilitiesAsMap);
        panelHeight = calcPanelHeight(abilitiesAsMap);
        Map<AbilityName, Integer> fightOpponentAbilitiesAsMap;
        if (fightOpponent != null) {
            fightOpponentAbilitiesAsMap = fightOpponent.getAbilities().getAsMap();
            panelWidth = Math.max("FIGHT".length(), Math.max(panelWidth, calcPanelWidth(fightOpponentAbilitiesAsMap)));
            panelHeight = Math.max(panelHeight, calcPanelHeight(abilitiesAsMap, fightOpponentAbilitiesAsMap));
        }
        return new AsciiPanel(panelWidth, panelHeight);
    }

    private int calcPanelHeight(Map<AbilityName,Integer> abilitiesAsMap,
                                Map<AbilityName,Integer> fightOpponentAbilitiesAsMap) {
        return Math.max(
                game.getGameMap().getLandscape().getHeight(),
                2 + abilitiesAsMap.size() + fightOpponentAbilitiesAsMap.size()
        );
    }

    private int calcPanelHeight(Map<AbilityName,Integer> abilitiesAsMap) {
        return Math.max(game.getGameMap().getLandscape().getHeight(), abilitiesAsMap.size());
    }

    private int calcPanelWidth(Map<AbilityName,Integer> abilitiesAsMap) {
        int panelWidth = 0;
        for (AbilityName abilityName: abilitiesAsMap.keySet()) {
            panelWidth = Math.max(
                    panelWidth,
                    3 + abilityName.getShortName().length() + abilitiesAsMap.get(abilityName).toString().length() +
                            game.getGameMap().getLandscape().getWidth()
            );
        }
        return panelWidth;
    }
}
