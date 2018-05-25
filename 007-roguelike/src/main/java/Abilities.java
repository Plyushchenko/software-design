import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class Abilities {
    private final Map<AbilityName, Integer> abilities;

    private Abilities() {
        abilities = new TreeMap<>();
        setAttack(0).setDefence(0).setHealthPoints(0);
    }

    public static Abilities newBuilder() {
        return new Abilities();
    }

    public Abilities set(AbilityName abilityName, int value) {
        abilities.put(abilityName, value);
        return this;
    }

    public Abilities setAttack(int value) {
        return set(AbilityName.ATTACK, value);
    }

    public Abilities setDefence(int value) {
        return set(AbilityName.DEFENCE, value);
    }

    public Abilities setHealthPoints(int value) {
        return set(AbilityName.HEALTH_POINTS, value);
    }

    public int get(AbilityName abilityName) throws NoSuchElementException {
        if (!abilities.containsKey(abilityName)) {
            throw new NoSuchElementException(abilityName.toString());
        }
        return abilities.get(abilityName);
    }

    public int getOrZero(AbilityName abilityName) {
        if (!abilities.containsKey(abilityName)) {
            return 0;
        }
        return abilities.get(abilityName);
    }


    public int getAttack() {
        return abilities.get(AbilityName.ATTACK);
    }

    public int getDefence() {
        return abilities.get(AbilityName.DEFENCE);
    }

    public int getHealthPoints() {
        return abilities.get(AbilityName.HEALTH_POINTS);
    }

    public Abilities compose(Abilities other) {
        for (AbilityName abilityName: other.abilities.keySet()) {
            set(abilityName, getOrZero(abilityName) + other.get(abilityName));
        }
        return this;
    }

    public Abilities decompose(Abilities other) {
        for (AbilityName abilityName: other.abilities.keySet()) {
            set(abilityName, getOrZero(abilityName) - other.get(abilityName));
        }
        return this;
    }
}
