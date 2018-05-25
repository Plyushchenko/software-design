public class NoPickUpStrategy implements PickUpStrategy {
    @Override
    public boolean pickUp(Artifact artifact) {
        return false;
    }
}
