/**
 * Write a description of class QuakeSortInPlace here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }

    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i = from + 1; i < quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    /**
     * Get the index position of the QuakeEntry with the largest depth considering only those QuakeEntry's position
     * from the end of the ArrayList
     *
     * @param quakeData is an ArrayList that contains all the QuakeEntry
     * @param from      represents the index position in the ArrayList
     * @return index position of the QuakeEntry with the largest depth
     */
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int retIndex = from;

        for (int i = from; i < quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(retIndex).getDepth()) {
                retIndex = i;
            }
        }

        return retIndex;
    }

    public void sortByMagnitude(ArrayList<QuakeEntry> in) {

        for (int i = 0; i < in.size(); i++) {
            int minIdx = getSmallestMagnitude(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
        }

    }

    /**
     * Sort the passed in ArrayList by largest depth
     *
     * @param quakeData is an ArrayList that contains all the QuakeEntry data
     */
    public void sortByLargestDepth(ArrayList<QuakeEntry> quakeData, int passes) {
        for (int i = 0; i < passes; i++) {
            int largestIdx = getLargestDepth(quakeData, i);
            QuakeEntry qCurrent = quakeData.get(i);
            QuakeEntry qLargest = quakeData.get(largestIdx);
            quakeData.set(i, qLargest);
            quakeData.set(largestIdx, qCurrent);
        }
    }

    /**
     * Use bubble sort on the earthquakes by magnitude
     * This method makes one pass of bubble sort on the ArrayList
     * Take advantage of the fact that the last numSorted elements are already in sorted order
     *
     * @param quakeData an ArrayList containing all the earthquake data
     * @param numSorted represents the number of times this method has already been called & count of sorted elements
     */
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i = 0; i < quakeData.size() - 1 - numSorted; i++) {
            if (quakeData.get(i + 1).getMagnitude() < quakeData.get(i).getMagnitude()) {
                QuakeEntry temp = quakeData.get(i);
                quakeData.set(i, quakeData.get(i + 1));
                quakeData.set(i + 1, temp);
            }
        }
    }

    /**
     * If the ArrayList has N elements in it, this method should call onePassBubbleSort N - 1 times to sort elememnts
     *
     * @param quakeData an ArrayList that contains all the QuakeEntry data
     */
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> quakeData) {
        for (int i = 0; i < quakeData.size() - 1; i++) {
            onePassBubbleSort(quakeData, i);
        }
    }

    /**
     * Check if the ArrayList containing QuakeEntry are already sorted
     *
     * @param quakes is an ArrayList that contains all the QuakeEntry data
     * @return true if all the elements are in the right place
     */
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for (int i = 1; i < quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(i - 1).getMagnitude()) {
                return false;
            }
        }

        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> quakeData) {
        int passedMade = 0;

        for (int i = 0; i < quakeData.size() - 1; i++) {
            if (checkInSortedOrder(quakeData)) {
                System.out.println("Passed made: " + passedMade);
                return;
            }

            onePassBubbleSort(quakeData, i);
            passedMade++;
        }
    }

    /**
     * Sorts earthquakes by their magnitude from smallest to largest using selection sort similar to the sortByMagnitude
     * But this method should call the checkInSortedOrder and stop early if ArrayList is sorted
     *
     * @param quakeData an ArrayList containing all the QuakeEntry data
     */
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> quakeData) {
        int passedMade = 0;

        for (int i = 0; i < quakeData.size(); i++) {
            if (checkInSortedOrder(quakeData)) {
                System.out.println("Passed made: " + passedMade);
                return;
            }

            int minIdx = getSmallestMagnitude(quakeData, i);
            QuakeEntry qi = quakeData.get(i);
            QuakeEntry qmin = quakeData.get(minIdx);
            quakeData.set(i, qmin);
            quakeData.set(minIdx, qi);

            passedMade++;
        }
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/EarthquakeData/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        System.out.println("read data for " + list.size() + " quakes");
        sortByMagnitudeWithBubbleSortWithCheck(list);
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for (QuakeEntry qe : list) {
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n", qe.getLocation().getLatitude(), qe.getLocation().getLongitude(),
                    qe.getMagnitude(), qe.getInfo());
        }

    }
}
