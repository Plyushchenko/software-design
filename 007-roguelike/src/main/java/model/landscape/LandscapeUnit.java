package model.landscape;

import model.gamemap.GameMapObject;
import model.gamemap.GameMapPosition;

public class LandscapeUnit implements GameMapObject {
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
