package model.gamemap.landscape;

import model.gamemap.GameMapPosition;
import model.gamemap.PositionObject;

public class LandscapeUnit implements PositionObject {
    private final GameMapPosition gameMapPosition;
    private final LandscapeUnitType landscapeUnitType;

    public LandscapeUnit(GameMapPosition gameMapPosition, LandscapeUnitType landscapeUnitType) {
        this.gameMapPosition = gameMapPosition;
        this.landscapeUnitType = landscapeUnitType;
    }

    @Override
    public GameMapPosition getGameMapPosition() {
        return gameMapPosition;
    }

    public LandscapeUnitType getLandscapeUnitType() {
        return landscapeUnitType;
    }
}
