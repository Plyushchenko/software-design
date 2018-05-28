package model.pickupstrategy;

import model.modificator.artifact.Artifact;

public interface PickUpStrategy {
    boolean pickUp(Artifact artifact);
}
