public enum AbilityName {
    ATTACK("Attack", "ATK"),
    DEFENCE("Defence", "DEF"),
    HEALTH_POINTS("Health points", "HP ");

    private final String name;
    private final String shortName;


    AbilityName(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }
}
