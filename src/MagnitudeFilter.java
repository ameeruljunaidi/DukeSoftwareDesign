import org.jetbrains.annotations.NotNull;

public class MagnitudeFilter implements Filter {
    private final double minMag;
    private final double maxMag;

    /**
     * @param min the minimum magnitude value
     * @param max the maximum magnitude value
     */
    public MagnitudeFilter(double min, double max) {
        this.minMag = min;
        this.maxMag = max;
    }

    /**
     * @param qe the QuakeEntry object to check
     * @return true if QuakeEntry's magnitude is between the minimum and the maximum magnitudes
     */
    @Override public boolean satisfies(@NotNull QuakeEntry qe) {
        return qe.getMagnitude() >= this.minMag && qe.getMagnitude() <= this.maxMag;
    }

    @Override public String getName() {
        return "Magnitude Filter";
    }
}
