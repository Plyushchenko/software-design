package model.modifier.artifact;

import model.ability.Abilities;
import model.modifier.Modifier;

/**
 * Class to store artifact
 */
public class Artifact implements Modifier, Comparable<Artifact> {
    public static final Artifact NO_ARTIFACT = new Artifact(null, null, null, null);
    private final String name;
    private final Abilities abilities;
    private final Slot slot;
    private final ArtifactType artifactType;

    public Artifact(String name,
                    Abilities abilities,
                    Slot slot,
                    ArtifactType artifactType) {
        this.name = name;
        this.abilities = abilities;
        this.slot = slot;
        this.artifactType  = artifactType;
    }

    public String getName() {
        return name;
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

    @Override
    public int compareTo(Artifact artifact) {
        return name.compareTo(artifact.name);
    }
}
