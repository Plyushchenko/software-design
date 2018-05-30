package ru.spbau.roguelike.model.gamemap.landscape;

public enum LandscapeUnitType {
    LAND('.'),
    WALL('X');

    private final char charValue;
    
    LandscapeUnitType(char charValue) {
        this.charValue = charValue;
    }
    
    public char getCharValue() {
        return charValue;
    }
    
    public static LandscapeUnitType fromChar(char charValue) {
        for (LandscapeUnitType landscapeUnitType: LandscapeUnitType.values()) {
            if (landscapeUnitType.charValue == charValue) {
                return landscapeUnitType;
            }
        }
        throw new IllegalArgumentException("No such ru.spbau.roguelike.model.gamemap.landscape.LandscapeUnitType: " + charValue);
    }
}
