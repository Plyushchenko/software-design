package model.movestrategy;

import model.gamemap.GameMapMovement;
import ui.GameFrame;

import java.awt.event.*;
import java.util.AbstractQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class KeyboardListener implements MoveStrategy, KeyListener {
    AbstractQueue<KeyEvent> pressed;
    GameFrame gameFrame;

    public KeyboardListener() {
        pressed = new ConcurrentLinkedQueue<>();
    }

    @Override
    public GameMapMovement getGameMovement() {
        System.out.println(pressed.size());
        KeyEvent keyEvent = pressed.poll();
        if (keyEvent == null) {
            return GameMapMovement.NONE;
        }
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
    public void keyTyped(KeyEvent keyEvent) {
        System.out.println("TYPED    " + keyEvent);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        System.out.println("PRESSED  " + keyEvent);
        if (keyEvent.getKeyCode() == KeyEvent.VK_UP ||
                keyEvent.getKeyCode() == KeyEvent.VK_DOWN ||
                keyEvent.getKeyCode() == KeyEvent.VK_LEFT ||
                keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            pressed.offer(keyEvent);
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_I) {
            gameFrame.changeState();
        }
        System.out.println("PRESSED " + pressed.size());
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        System.out.println("RELEASED " + keyEvent);
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }
}