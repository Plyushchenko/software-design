public enum CreatureType {
    PLAYER('P'),
    BOT('B'),
    NO_CREATURE(' ');

    private final char charValue;

    CreatureType(char charValue) {
        this.charValue = charValue;
    }

    public char getCharValue() {
        return charValue;
    }

    public static CreatureType fromChar(char charValue) {
        for (CreatureType creatureType: CreatureType.values()) {
            if (creatureType.charValue == charValue) {
                return creatureType;
            }
        }
        throw new IllegalArgumentException("No such CreatureType: " + charValue);
    }
}
