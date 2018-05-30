package ru.spbau.roguelike.ui;

import ru.spbau.roguelike.model.modifier.artifact.Artifact;
import ru.spbau.roguelike.model.modifier.artifact.Inventory;
import ru.spbau.roguelike.model.modifier.artifact.Slot;

public class InventoryCursor {
    private int block;
    private int number;
    private final Inventory inventory;

    public InventoryCursor(Inventory inventory) {
        this.inventory = inventory;
        block = 0;
        number = 0;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getBlock() {
        return block;
    }

    void increment() {
        if (block == 0) {
            if (number == inventory.getActiveArtifacts().size() - 1) {
                if (inventory.getInactiveArtifacts().size() != 0) {
                    block = 1;
                }
                number = 0;
            } else {
                number++;
            }
        } else if (block == 1) {
            if (number == inventory.getInactiveArtifacts().size() - 1) {
                block = 0;
                number = 0;
            } else {
                number++;
            }
        }
    }

    void decrement() {
        if (block == 0) {
            if (number == 0) {
                if (inventory.getInactiveArtifacts().size() != 0) {
                    block = 1;
                    number = inventory.getInactiveArtifacts().size() - 1;
                } else {
                    number = inventory.getActiveArtifacts().size() - 1;
                }
            } else {
                number--;
            }
        } else if (block == 1) {
            if (number == 0) {
                block = 0;
                number = inventory.getActiveArtifacts().size() - 1;
            } else {
                number--;
            }
        }
    }

    void useArtifact() {
        if (block == 0) {
            int i = 0;
            for (Slot slot: inventory.getActiveArtifacts().keySet()) {
                if (i == number) {
                    inventory.takeOff(slot);
                    return;
                }
                i++;
            }
        } else if (block == 1) {
            int i = 0;
            for (Artifact artifact: inventory.getInactiveArtifacts()) {
                if (i == number) {
                    inventory.putOn(artifact);
                    block = 0;
                    number = 0;
                    for (Slot slot: inventory.getActiveArtifacts().keySet()) {
                        if (artifact.getSlot() == slot) {
                            return;
                        }
                        number++;
                    }
                }
                i++;
            }

        }
    }
}
