package model.modificator.artifact;

import model.gamemap.GameMapObject;
import model.gamemap.GameMapPosition;
import model.modificator.Modificator;
import model.ability.Abilities;

public class Artifact implements Modificator, GameMapObject {
    public static final Artifact NO_ARTIFACT = new Artifact(null, null,null, null, null);
    private final String name;
    private final GameMapPosition gameMapPosition;
    private final Abilities abilities;
    private final Slot slot;
    private final ArtifactType artifactType;

    public Artifact(String name,
                    GameMapPosition gameMapPosition,
                    Abilities abilities,
                    Slot slot,
                    ArtifactType artifactType) {
        this.name = name;
        this.gameMapPosition = gameMapPosition;
        this.abilities = abilities;
        this.slot = slot;
        this.artifactType  = artifactType;
    }

    public String getName() {
        return name;
    }

    @Override
    public GameMapPosition getGameMapPosition() {
        return gameMapPosition;
    }

    @Override
    public Abilities getAbilities() {
        return abilities;
    }

    public Slot getSlot() {
        return slot;
    }

    public ArtifactType getArtifactType() {
        return artifactType;
    }
}
