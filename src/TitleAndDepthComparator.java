import java.util.Comparator;

/**
 * Compare the title to q1 and q2, then break tiw by comparing the depth of the two earthquake
 */
public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    @Override public int compare(QuakeEntry q1, QuakeEntry q2) {
        if (q1.getInfo().compareTo(q2.getInfo()) < 0) {
            return -1;
        } else if (q1.getInfo().compareTo(q2.getInfo()) > 0) {
            return 1;
        } else {
            if (q1.getDepth() < q2.getDepth()) {
                return -1;
            } else if (q1.getDepth() > q2.getDepth()) {
                return 1;
            }
        }

        return 0;
    }

}
