package model.pickupstrategy;

import model.modificator.artifact.Artifact;
import model.pickupstrategy.PickUpStrategy;

public class NoPickUpStrategy implements PickUpStrategy {
    @Override
    public boolean pickUp(Artifact artifact) {
        return false;
    }
}
