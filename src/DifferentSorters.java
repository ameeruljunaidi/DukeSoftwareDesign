
/**
 * Write a description of class DifferentSorters here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class DifferentSorters {
    String source = "data/EarthquakeData/earthQuakeDataWeekDec6sample1.atom";

    public void sortWithCompareTo() {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);

        // Collections is a part of the java.util class, it can access the interface comparable in the class
        // and look for the compareTo method to sort the elements
        Collections.sort(list);

        // for (QuakeEntry qe : list) {
        //     System.out.println(qe);
        // }

        int quakeNumber = 600;
        System.out.println("Print quake entry in position: " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }

    public void sortByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        list.sort(new MagnitudeComparator());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

    }

    public void sortByDistance() {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        // Location is Durham, NC
        Location where = new Location(35.9886, -78.9072);
        list.sort(new DistanceComparator(where));
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

    }

    public void sortByTitleAndDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        list.sort(new TitleAndDepthComparator());
        int quakeNumber = 500;
        System.out.println("Print quake entry in position: " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }

    public void sortByLastWordInTitleThenByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        list.sort(new TitleLastAndMagnitudeComparator());
        int quakeNumber = 500;
        System.out.println("Print quake entry in position: " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }
}
