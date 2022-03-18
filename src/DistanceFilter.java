public class DistanceFilter implements Filter {
    private final Location location;
    private final double maxDistance;

    /**
     * @param location    the Location object to compare to
     * @param maxDistance the maximum distance to filter for
     */
    public DistanceFilter(Location location, double maxDistance) {
        this.location = location;
        this.maxDistance = maxDistance;
    }

    @Override public boolean satisfies(QuakeEntry qe) {
        return (qe.getLocation().distanceTo(this.location) * 0.001) < maxDistance;
    }

    @Override public String getName() {
        return "Distance Filter";
    }
}
