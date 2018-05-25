public class PickUpEverythingStrategy implements PickUpStrategy {
    @Override
    public boolean pickUp(Artifact artifact) {
        return true;
    }
}
