import org.jetbrains.annotations.NotNull;

public class DepthFilter implements Filter {
    private final double minDepth;
    private final double maxDepth;

    /**
     * @param min minimum depth
     * @param max maximum depth
     */
    public DepthFilter(double min, double max) {
        this.minDepth = min;
        this.maxDepth = max;
    }

    /**
     * @param qe the QuakeEntry object
     * @return true if a QuakeEntry's distance from the given location is less than the specified max distance
     */
    @Override public boolean satisfies(@NotNull QuakeEntry qe) {
        return qe.getDepth() >= this.minDepth && qe.getDepth() <= this.maxDepth;
    }

    @Override public String getName() {
        return "Depth Filter";
    }
}
