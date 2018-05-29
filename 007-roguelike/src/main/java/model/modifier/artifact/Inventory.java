package model.modifier.artifact;

import java.util.*;

/**
 * Inventory: bunch of Artifacts divided into active and inactive parts
 */
public class Inventory {
    private final Map<Slot, Artifact> activeArtifacts;
    private final Set<Artifact> inactiveArtifacts;

    public Inventory() {
        activeArtifacts = new HashMap<>();
        for (Slot slot: Slot.values()) {
            activeArtifacts.put(slot, Artifact.NO_ARTIFACT);
        }
        inactiveArtifacts = new HashSet<>();
    }

    public void putOn(Artifact artifact) throws IllegalArgumentException {
        if (!inactiveArtifacts.contains(artifact)) {
            throw new IllegalArgumentException("putOn failed: no such artifact: " + artifact.getName());
        }
        Slot slot = artifact.getSlot();
        if (activeArtifacts.get(slot) != Artifact.NO_ARTIFACT) {
            throw new IllegalArgumentException("putOn failed: " + slot.toString() + " is busy");
        }
        inactiveArtifacts.remove(artifact);
        activeArtifacts.put(artifact.getSlot(), artifact);
    }

    public void takeOff(Slot slot) {
        Artifact artifact = activeArtifacts.get(slot);
        if (artifact == Artifact.NO_ARTIFACT) {
            throw new IllegalArgumentException("takeOff failed: " + slot.toString() + " is empty");
        }
        inactiveArtifacts.add(artifact);
        activeArtifacts.put(slot, Artifact.NO_ARTIFACT);
    }

    public void add(Artifact foundArtifact) {
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

    /*
    public void add(List<Artifact> artifacts) {
        artifacts.forEach(this::add);
    }
    */

    public void add(List<GameMapArtifact> gameMapArtifacts) {
        gameMapArtifacts.forEach(x -> add(x.getArtifact()));
    }
}
