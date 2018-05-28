package model.creature;

import model.*;
import model.ability.Abilities;
import model.modificator.DamageValueCalculator;
import model.modificator.Modificator;
import model.modificator.artifact.Artifact;
import model.gamemap.GameMap;
import model.gamemap.GameMapMovableObject;
import model.gamemap.GameMapPosition;
import model.movestrategy.MoveStrategy;
import model.pickupstrategy.PickUpStrategy;

import java.util.List;

public class Creature implements GameMapMovableObject {
    private final MoveStrategy moveStrategy;
    private GameMapPosition gameMapPosition;
    private final Abilities abilities;
    private boolean alive;
    private final PickUpStrategy pickUpStrategy;
    private final Inventory inventory;

    public Creature(MoveStrategy moveStrategy,
                    PickUpStrategy pickUpStrategy,
                    GameMapPosition gameMapPosition,
                    Abilities abilities) {
        this.moveStrategy = moveStrategy;
        this.pickUpStrategy = pickUpStrategy;
        this.gameMapPosition = gameMapPosition;
        this.abilities = abilities;
        alive = abilities.getHealthPoints() > 0;
        this.inventory = new Inventory();
    }

    @Override
    public void moveTo(GameMapPosition gameMapPosition) {
        System.out.println("moveTo begin: " + gameMapPosition);
        int x = gameMapPosition.getX();
        int y = gameMapPosition.getY();
        if (0 <= x && x < 10 && 0 <= y && y < 10) {
            this.gameMapPosition = gameMapPosition;
            System.out.println("moveTo   end: " + gameMapPosition);
        }
    }

    @Override
    public void moveByMoveStrategy() {
        applyMovement(moveStrategy.getGameMovement());
    }

    void applyModificator(Modificator modificator) {
        abilities.compose(modificator.getAbilities());
        alive = abilities.getHealthPoints() > 0;
    }

    void unapplyModificator(Modificator modificator) {
        abilities.decompose(modificator.getAbilities());
        alive = abilities.getHealthPoints() > 0;
    }

    @Override
    public GameMapPosition getGameMapPosition() {
        return gameMapPosition;
    }

    public Abilities getAbilities() {
        return abilities;
    }

    public void attack(Creature other) {
        CreatureAttack creatureAttack = new CreatureAttack(other);
        other.applyModificator(creatureAttack);
    }

    public boolean isAlive() {
        return alive;
    }

    public void pickUpArtifacts(GameMap gameMap) {
        List<Artifact> foundArtifacts = gameMap.getArtifacts(gameMapPosition);
        for (Artifact foundArtifact: foundArtifacts) {
            if (pickUpArtifact(foundArtifact)) {
                gameMap.removeArtifact(foundArtifact);
                inventory.add(foundArtifact);
            }
        }
    }

    private boolean pickUpArtifact(Artifact artifact) {
        return pickUpStrategy.pickUp(artifact);
    }

    public Inventory getInventory() {
        return inventory;
    }

    private class CreatureAttack implements Modificator {
        private final Abilities abilities;
        public CreatureAttack(Creature other) {
            int damageValue = DamageValueCalculator.calculate(Creature.this, other);
            abilities = Abilities.newBuilder().setHealthPoints(-damageValue);
        }

        @Override
        public Abilities getAbilities() {
            return abilities;
        }
    }

    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
    }
}
