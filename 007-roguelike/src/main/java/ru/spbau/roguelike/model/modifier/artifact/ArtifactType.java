package ru.spbau.roguelike.model.modifier.artifact;

public enum ArtifactType {
    SWORD('S'),
    DAGGER('D');

    private final char charValue;

    ArtifactType(char charValue) {
        this.charValue = charValue;
    }

    public char getCharValue() {
        return charValue;
    }

    public ArtifactType fromChar(char charValue) {
        for (ArtifactType artifactType: ArtifactType.values()) {
            if (artifactType.charValue == charValue) {
                return artifactType;
            }
        }
        throw new IllegalArgumentException("No such ru.spbau.roguelike.model.modifier.artifact.ArtifactType: " + charValue);
    }
}
