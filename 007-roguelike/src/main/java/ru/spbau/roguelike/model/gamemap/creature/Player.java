package ru.spbau.roguelike.model.gamemap.creature;

import ru.spbau.roguelike.model.modifier.ability.Abilities;
import ru.spbau.roguelike.model.gamemap.GameMap;
import ru.spbau.roguelike.model.gamemap.GameMapMovement;
import ru.spbau.roguelike.model.gamemap.GameMapPosition;
import ru.spbau.roguelike.model.modifier.artifact.Inventory;
import ru.spbau.roguelike.ui.InventoryCursor;
import ru.spbau.roguelike.ui.KeyboardListener;

public class Player extends Creature {
    private final KeyboardListener keyboardListener;
    private final Inventory inventory;

    public Player(GameMap gameMap,
                  GameMapPosition gameMapPosition) {
        super(gameMap, gameMapPosition, buildDefaultAbilities());
        this.inventory = new Inventory(this);
        this.keyboardListener = new KeyboardListener(new InventoryCursor(inventory));
    }

    private static Abilities buildDefaultAbilities() {
        return Abilities.newBuilder().setAttack(10).setDefence(10).setHealthPoints(10);
    }

    public KeyboardListener getKeyboardListener() {
        return keyboardListener;
    }

    @Override
    public void moveTo(GameMapPosition gameMapPosition) {
        if (fightOpponent != null && !fightOpponent.isAlive()) {
            fightOpponent.fightOpponent = null;
            fightOpponent = null;
        }
        if (gameMapPosition.equals(this.gameMapPosition)) {
            return;
        }
        if (fightOpponent != null) {
            fightOpponent.fightOpponent = null;
            fightOpponent = null;
        }
        super.moveTo(gameMapPosition);
    }

    @Override
    public void move() {
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
