import java.util.List;
import java.util.Queue;

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
                    Abilities abilities,
                    boolean alive) {
        this.moveStrategy = moveStrategy;
        this.pickUpStrategy = pickUpStrategy;
        this.gameMapPosition = gameMapPosition;
        this.abilities = abilities;
        this.alive = alive;
        this.inventory = new Inventory();
    }

    @Override
    public void moveTo(GameMapPosition gameMapPosition) {
        this.gameMapPosition = gameMapPosition;
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

    private class CreatureAttack implements Modificator {
        private final Abilities abilities;
        public CreatureAttack(Creature other) {
            int damageValue = DamageValueCalculator.calculate(Creature.this, other);
            abilities = Abilities.newBuilder().setHealthPoints(damageValue);
        }

        @Override
        public Abilities getAbilities() {
            return abilities;
        }
    }
}
