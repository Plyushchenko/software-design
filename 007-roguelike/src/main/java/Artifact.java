public class Artifact implements Modificator, GameMapObject {
    public static final Artifact NO_ARTIFACT = new Artifact(null, null,null, null);
    private final String name;
    private final GameMapPosition gameMapPosition;
    private final Abilities abilities;
    private final Slot slot;

    public Artifact(String name, GameMapPosition gameMapPosition, Abilities abilities, Slot slot) {
        this.name = name;
        this.gameMapPosition = gameMapPosition;
        this.abilities = abilities;
        this.slot = slot;
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
}
