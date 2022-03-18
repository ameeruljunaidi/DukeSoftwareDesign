import java.util.ArrayList;
import java.util.Comparator;

/**
 * Compare the last word in the title of q1 and q2
 * Then compare magnitude after
 */
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {

    @Override public int compare(QuakeEntry q1, QuakeEntry q2) {
        String[] q1Title = q1.getInfo().split("\\s+");
        String[] q2Title = q2.getInfo().split("\\s+");
        String q1LastTitle = q1Title[q1Title.length - 1];
        String q2LastTitle = q2Title[q2Title.length - 1];

        if (q1LastTitle.compareTo(q2LastTitle) < 0) return -1;
        else if (q1LastTitle.compareTo(q2LastTitle) > 0) return 1;
        else if (q1.getMagnitude() < q2.getMagnitude()) return -1;
        else if (q1.getMagnitude() > q2.getMagnitude()) return 1;

        return 0;
    }
}
