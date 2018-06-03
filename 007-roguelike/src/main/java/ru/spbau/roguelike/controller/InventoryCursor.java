package ru.spbau.roguelike.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.spbau.roguelike.model.modifier.artifact.Artifact;
import ru.spbau.roguelike.model.modifier.artifact.Inventory;
import ru.spbau.roguelike.model.modifier.artifact.Slot;

/**
 * GUI Inventory handler
 */
public class InventoryCursor {
    private static final Log LOGGER = LogFactory.getLog(InventoryCursor.class);
    private static int INACTIVE_ARTIFACTS_BLOCK = 0;
    private static int ACTIVE_ARTIFACTS_BLOCK = 1;
    private int block;
    private int number;
    private final Inventory inventory;

    public InventoryCursor(Inventory inventory) {
        this.inventory = inventory;
        block = INACTIVE_ARTIFACTS_BLOCK;
        number = 0;
    }

    public int getNumber() {
        return number;
    }

    public int getBlock() {
        return block;
    }

    /**
     * Get the next element of a list or go to the beginning of the next list (cycled)
     */
    void increment() {
        if (LOGGER != null) {
            LOGGER.info("Before increment: block = " + block + ", number = " + number);
        }
        if (block == INACTIVE_ARTIFACTS_BLOCK) {
            if (number == inventory.getActiveArtifacts().size() - 1) {
                if (inventory.getInactiveArtifacts().size() != 0) {
                    block = ACTIVE_ARTIFACTS_BLOCK;
                }
                number = 0;
            } else {
                number++;
            }
        } else if (block == ACTIVE_ARTIFACTS_BLOCK) {
            if (number == inventory.getInactiveArtifacts().size() - 1) {
                block = INACTIVE_ARTIFACTS_BLOCK;
                number = 0;
            } else {
                number++;
            }
        }
        if (LOGGER != null) {
            LOGGER.info("After increment: block = " + block + ", number = " + number);
        }
    }

    /**
     * Get the previous element of a list or go to the bottom of the previous list (cycled)
     */
    void decrement() {
        if (LOGGER != null) {
            LOGGER.info("Before decrement: block = " + block + ", number = " + number);
        }
        if (block == INACTIVE_ARTIFACTS_BLOCK) {
            if (number == 0) {
                if (inventory.getInactiveArtifacts().size() != 0) {
                    block = ACTIVE_ARTIFACTS_BLOCK;
                    number = inventory.getInactiveArtifacts().size() - 1;
                } else {
                    number = inventory.getActiveArtifacts().size() - 1;
                }
            } else {
                number--;
            }
        } else if (block == ACTIVE_ARTIFACTS_BLOCK) {
            if (number == 0) {
                block = INACTIVE_ARTIFACTS_BLOCK;
                number = inventory.getActiveArtifacts().size() - 1;
            } else {
                number--;
            }
        }
        if (LOGGER != null) {
            LOGGER.info("After decrement: block = " + block + ", number = " + number);
        }
    }

    /**
     * Put on or take off an artifact at the cursor position
     */
    void useArtifact() {
        if (LOGGER != null) {
            LOGGER.info("At useArtifact: block = " + block + ", number = " + number);
        }
        if (block == INACTIVE_ARTIFACTS_BLOCK) {
            int i = 0;
            for (Slot slot: inventory.getActiveArtifacts().keySet()) {
                if (i == number) {
                    inventory.takeOff(slot);
                    return;
                }
                i++;
            }
        } else if (block == ACTIVE_ARTIFACTS_BLOCK) {
            int i = 0;
            for (Artifact artifact: inventory.getInactiveArtifacts()) {
                if (i == number) {
                    inventory.putOn(artifact);
                    block = INACTIVE_ARTIFACTS_BLOCK;
                    int tmpNumber = 0;
                    for (Slot slot: inventory.getActiveArtifacts().keySet()) {
                        if (artifact.getSlot() == slot) {
                            number = tmpNumber;
                            return;
                        }
                        tmpNumber++;
                    }
                }
                i++;
            }
        }
    }
}
