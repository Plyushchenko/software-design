public enum ArtifactType {
    SWORD('S');

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
        throw new IllegalArgumentException("No such ArtifactType: " + charValue);
    }
}
