package ru.spbau.roguelike.model.modifier.artifact;

import ru.spbau.roguelike.model.gamemap.creature.Player;

import java.util.*;

/**
 * Inventory: bunch of Artifacts divided into active and inactive parts
 */
public class Inventory {
    private final Map<Slot, Artifact> activeArtifacts;
    private final Set<Artifact> inactiveArtifacts;
    private final Player player;

    public Inventory(Player player) {
        this.player = player;
        activeArtifacts = new TreeMap<>();
        for (Slot slot: Slot.values()) {
            activeArtifacts.put(slot, Artifact.NO_ARTIFACT);
        }
        inactiveArtifacts = new TreeSet<>();
    }

    /**
     * Move artifact from inactive section to active one.
     * Does nothing if no artifact presented.
     * @param artifact Artifact to put on
     */
    public void putOn(Artifact artifact) {
        if (!inactiveArtifacts.contains(artifact)) {
            return;
        }
        Slot slot = artifact.getSlot();
        if (activeArtifacts.get(slot) != Artifact.NO_ARTIFACT) {
            return;
        }
        inactiveArtifacts.remove(artifact);
        activeArtifacts.put(artifact.getSlot(), artifact);
        player.applyModifier(artifact);
    }

    /**
     * Move artifact from active section to inactive one.
     * Does nothing if the slot is empty.
     * @param slot Slot to free
     */
    public void takeOff(Slot slot) {
        Artifact artifact = activeArtifacts.get(slot);
        if (artifact == Artifact.NO_ARTIFACT) {
            return;
        }
        inactiveArtifacts.add(artifact);
        activeArtifacts.put(slot, Artifact.NO_ARTIFACT);
        player.unapplyModifier(artifact);
    }

    private void add(Artifact foundArtifact) {
        inactiveArtifacts.add(foundArtifact);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Active\n");
        for (Slot slot: activeArtifacts.keySet()) {
            s.append("slot = ").append(slot.toString()).append(", name = ");
            if (activeArtifacts.get(slot) == null) {
                s.append("empty");
            } else {
                s.append(activeArtifacts.get(slot).getName());
            }
            s.append("\n");
        }
        s.append("\nInactive\n");
        for (Artifact artifact: inactiveArtifacts) {
            s.append("name = ").append(artifact.getName()).append("\n");
        }
        return s.toString();
    }

    public void add(List<GameMapArtifact> gameMapArtifacts) {
        gameMapArtifacts.forEach(x -> add(x.getArtifact()));
    }

    public Map<Slot, Artifact> getActiveArtifacts() {
        return activeArtifacts;
    }

    public Set<Artifact> getInactiveArtifacts() {
        return inactiveArtifacts;
    }
}
