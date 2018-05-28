package model.pickupstrategy;

import model.modificator.artifact.Artifact;

public class PickUpEverythingStrategy implements PickUpStrategy {
    @Override
    public boolean pickUp(Artifact artifact) {
        return true;
    }
}
