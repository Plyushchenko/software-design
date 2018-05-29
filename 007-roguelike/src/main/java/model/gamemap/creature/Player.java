package model.gamemap.creature;

import model.ability.Abilities;
import model.gamemap.GameMap;
import model.gamemap.GameMapMovement;
import model.gamemap.GameMapPosition;
import model.modifier.artifact.Inventory;
import ui.KeyboardListener;

public class Player extends Creature {
    private final KeyboardListener keyboardListener;
    private final Inventory inventory;

    public Player(GameMap gameMap,
                  GameMapPosition gameMapPosition,
                  KeyboardListener keyboardListener,
                  Inventory inventory) {
        super(gameMap, gameMapPosition, buildDefaultAbilities());
        this.keyboardListener = keyboardListener;
        this.inventory = inventory;
    }

    private static Abilities buildDefaultAbilities() {
        return Abilities.newBuilder().setAttack(10).setDefence(10).setHealthPoints(10);
    }

    public KeyboardListener getKeyboardListener() {
        return keyboardListener;
    }

    @Override
    public void move() {
        System.out.println("MOVE");
        GameMapMovement gameMapMovement = keyboardListener.getGameMapMovement();
        applyMovement(gameMapMovement);
    }

    public void pickUpGameMapArtifacts() {
            inventory.add(gameMap.pickUpArtifacts(gameMapPosition));
    }

    public Inventory getInventory() {
        return inventory;
    }
}
