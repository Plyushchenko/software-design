package ru.spbau.roguelike.controller;

import ru.spbau.roguelike.model.gamemap.GameMapMovement;
import ru.spbau.roguelike.model.modifier.artifact.Inventory;
import ru.spbau.roguelike.view.GameFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class that listens keyboard
 */
public class KeyboardListener implements KeyListener {
    private KeyEvent pressedGameMap;
    private KeyboardListenerState state;
    private final InventoryCursor inventoryCursor;
    private GameFrame gameFrame;

    public KeyboardListener(Inventory inventory) {
        state = KeyboardListenerState.GAME_MAP;
        this.inventoryCursor = new InventoryCursor(inventory);
    }

    /**
     * Transform pressed arrow key into GameMapMovement
     */
    public GameMapMovement getGameMapMovement() {
        KeyEvent keyEvent = pressedGameMap;
        if (keyEvent == null) {
            return GameMapMovement.NONE;
        }
        pressedGameMap = null;
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_UP:
                return GameMapMovement.UP;
            case KeyEvent.VK_DOWN:
                return GameMapMovement.DOWN;
            case KeyEvent.VK_LEFT:
                return GameMapMovement.LEFT;
            case KeyEvent.VK_RIGHT:
                return GameMapMovement.RIGHT;
            default:
                return GameMapMovement.NONE;
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (state) {
            case GAME_MAP:
                if (keyEvent.getKeyCode() == KeyEvent.VK_UP ||
                        keyEvent.getKeyCode() == KeyEvent.VK_DOWN ||
                        keyEvent.getKeyCode() == KeyEvent.VK_LEFT ||
                        keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                    pressedGameMap = keyEvent;
                }
                break;
            case INVENTORY:
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        inventoryCursor.decrement();
                        break;
                    case KeyEvent.VK_DOWN:
                        inventoryCursor.increment();
                        break;
                    case KeyEvent.VK_ENTER:
                        inventoryCursor.useArtifact();
                        break;
                }
                gameFrame.draw();
                break;
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_I) {
            changeState();
        }
    }

    private void changeState() {
        switch (state) {
            case INVENTORY:
                state = KeyboardListenerState.GAME_MAP;
                break;
            case GAME_MAP:
                state = KeyboardListenerState.INVENTORY;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {}

    public KeyboardListenerState getState() {
        return state;
    }

    public InventoryCursor getInventoryCursor() {
        return inventoryCursor;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }
}