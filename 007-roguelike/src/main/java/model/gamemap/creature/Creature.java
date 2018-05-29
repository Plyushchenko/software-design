package model.gamemap.creature;

import model.ability.Abilities;
import model.gamemap.GameMap;
import model.gamemap.GameMapMovableObject;
import model.gamemap.GameMapPosition;
import model.gamemap.landscape.LandscapeUnitType;
import model.modifier.Modifier;

/**
 * Creature that can fight other creatures,
 */
public abstract class Creature implements GameMapMovableObject {
    final GameMap gameMap;
    GameMapPosition gameMapPosition;
    final Abilities abilities;
    boolean alive;

    Creature(GameMap gameMap, GameMapPosition gameMapPosition, Abilities abilities) {
        this.gameMap = gameMap;
        this.gameMapPosition = gameMapPosition;
        this.abilities = abilities;
        alive = isAlive();
    }

    @Override
    public void moveTo(GameMapPosition gameMapPosition) {
        if (gameMapPosition.inside(gameMap)) {
            LandscapeUnitType landscapeUnitType = gameMap.getLandscape()
                    .getLandscapeUnit(gameMapPosition).getLandscapeUnitType();
            if (landscapeUnitType == LandscapeUnitType.LAND) {
                this.gameMapPosition = gameMapPosition;
            }
        }
    }

    @Override
    public GameMapPosition getGameMapPosition() {
        return gameMapPosition;
    }

    @Override
    public GameMap getGameMap() {
        return gameMap;
    }

    public boolean isAlive() {
        return abilities.getHealthPoints() > 0;
    }

    void applyModifier(Modifier modifier) {
        abilities.compose(modifier.getAbilities());
    }

    void unapplyModifier(Modifier modifier) {
        abilities.decompose(modifier.getAbilities());
    }

    public void attack(Creature other) {
        other.applyModifier(CreatureAttack.createCreatureAttack(this, other));
    }

    public Abilities getAbilities() {
        return abilities;
    }
}
