package ru.spbau.roguelike.model.modifier.ability;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * Class to store abilities such as attack, defence, and others...
 */
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

    /**
     * Compose two groups of abilities by summing up every ability value
     */
    public Abilities compose(Abilities other) {
        for (AbilityName abilityName: other.abilities.keySet()) {
            set(abilityName, getOrZero(abilityName) + other.get(abilityName));
        }
        return this;
    }

    /**
     * Decompose two groups of abilities by subtracting every ability value
     */
    public Abilities decompose(Abilities other) {
        for (AbilityName abilityName: other.abilities.keySet()) {
            set(abilityName, getOrZero(abilityName) - other.get(abilityName));
        }
        return this;
    }

    @Override
    public String toString() {
        return "ATK = " + getAttack() + ", DEF = " + getDefence() + ",  HP = " + getHealthPoints();
    }

    public Map<AbilityName, Integer> getAsMap() {
        return abilities;
    }
}
