class Ability {
    private final AbilityName abilityName;
    private final int value;

    public Ability(AbilityName abilityName, int value) {
        this.abilityName = abilityName;
        this.value = value;
    }

    public Ability(AbilityName abilityName) {
        this(abilityName, 0);
    }

    public AbilityName getAbilityName() {
        return abilityName;
    }

    public int getValue() {
        return value;
    }
}
