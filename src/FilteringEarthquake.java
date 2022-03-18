import java.util.ArrayList;

public class FilteringEarthquake {
    public static final String source = "data/EarthquakeData/nov20quakedata.atom";
    public static final EarthQuakeParser parser = new EarthQuakeParser();
    public static ArrayList<QuakeEntry> list = parser.read(source);

    public static void printQuakes(ArrayList<QuakeEntry> data) {
        for (QuakeEntry qe : data) {
            System.out.println(qe);
        }
    }

    /**
     * Test the filter method with MinMagFilter
     * Filter earthquakes using two criteria:
     * 1. Those with magnitude between 4.0 and 5.0 an
     * 2. Depth between -35000.0 and -12000.0
     */
    public static void run1() {
        System.out.println("Read data for " + list.size() + " quakes");
        Filter magnitudeFilter = new MagnitudeFilter(4.0, 5.0);
        Filter depthFilter = new DepthFilter(-35000.0, -12000);

        twoFilters(magnitudeFilter, depthFilter);
    }

    /**
     * Filter:
     * 1. 10,000,000 meters (10,000 km) from Tokyo, Japan (35.42, 139.43)
     * 2. Japan should be last word in title
     */
    public static void run2() {
        System.out.println("Read data for " + list.size() + " quakes");

        Location japan = new Location(35.42, 139.43);
        Filter distanceFilter = new DistanceFilter(japan, 10000.0);
        Filter phraseFilter = new PhraseFilter("end", "Japan");

        twoFilters(distanceFilter, phraseFilter);
    }

    private static void twoFilters(Filter distanceFilter, Filter phraseFilter) {
        ArrayList<QuakeEntry> filteredDistance = EarthQuakeClient2.filter(list, distanceFilter);
        ArrayList<QuakeEntry> filteredPhrase = EarthQuakeClient2.filter(filteredDistance, phraseFilter);

        System.out.println(filteredPhrase.size() + " quake found.");
        System.out.println("Filters used: " + distanceFilter.getName() + ", " + phraseFilter.getName());
    }

    /**
     * Create a matchAllFilter named maf and use the addFilter method to add three Filters to test:
     * 1. Magnitude between 0.0 and 2.0
     * 2. Depth between -100000.0 and -10000.0
     * 3. If letter "a" is in the title
     */
    public static void run3() {
        System.out.println("Read data for " + list.size() + " quakes");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 2.0));
        maf.addFilter(new DepthFilter(-100000.0, -10000.0));
        maf.addFilter(new PhraseFilter("any", "a"));
        ArrayList<QuakeEntry> filtered = EarthQuakeClient2.filter(list, maf);
        System.out.println(filtered.size() + " quake found.");
        System.out.println("Filters used: " + maf.getName());
    }

    /**
     * 1. Magnitude between 0.0 and 3.0
     * 2. Distance from Tulsa, Oklahoma (36.1314, -95.9372) < 10000 km
     * 3. If "Ca" is in the title
     */
    public static void run4() {
        System.out.println("Read data for " + list.size() + " quakes");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 3.0));
        maf.addFilter(new DistanceFilter(new Location(36.1314, -95.9372), 10000));
        maf.addFilter(new PhraseFilter("any", "Ca"));
        ArrayList<QuakeEntry> filtered = EarthQuakeClient2.filter(list, maf);
        System.out.println(filtered.size() + " quake found.");
        System.out.println("Filters used: " + maf.getName());
    }

    public static void runPractice() {
        System.out.println("\nQuestion 1");
        run2();
        System.out.println("\nQuestion 2");
        run1();
        System.out.println("\nQuestion 3");
        run3();
        System.out.println("\nQuestion 4");
        run4();
    }

    public static void runQuiz() {
        System.out.println("\nQuestion 1");
        runQ1();
        System.out.println("\nQuestion 2");
        runQ2();
        System.out.println("\nQuestion 3");
        runQ3();
        System.out.println("\nQuestion 4");
        runQ4();
        System.out.println("\nQuestion 5");
        runQ5();
        System.out.println("\nQuestion 6");
        runQ6();
        System.out.println("\nQuestion 7");
        runQ7();
        System.out.println("\nQuestion 8");
        runQ8();
        System.out.println("\nQuestion 9");
        runQ9();
        System.out.println("\nQuestion 10");
        runQ10();
        System.out.println("\nQuestion 11");
        runQ11();
    }

    private static void runQ11() {
        System.out.println("Read data for " + list.size() + " quakes");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 5.0));
        maf.addFilter(new DistanceFilter(new Location(55.7308, 9.1153), 3000.0));
        maf.addFilter(new PhraseFilter("any", "e"));
        ArrayList<QuakeEntry> filtered = EarthQuakeClient2.filter(list, maf);
        System.out.println(filtered.size() + " quake found.");
        System.out.println("Filters used: " + maf.getName());
    }

    private static void runQ10() {
        System.out.println("Read data for " + list.size() + " quakes");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(1.0, 4.0));
        maf.addFilter(new DepthFilter(-180000.0, -30000.0));
        maf.addFilter(new PhraseFilter("any", "o"));
        ArrayList<QuakeEntry> filtered = EarthQuakeClient2.filter(list, maf);
        System.out.println(filtered.size() + " quake found.");
        System.out.println("Filters used: " + maf.getName());
    }

    private static void runQ9() {
        System.out.println("Read data for " + list.size() + " quakes");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(3.5, 4.5));
        maf.addFilter(new DepthFilter(-55000.0, -20000.0));
        ArrayList<QuakeEntry> filtered = EarthQuakeClient2.filter(list, maf);
        System.out.println(filtered.size() + " quake found.");
        System.out.println("Filters used: " + maf.getName());
    }

    private static void runQ8() {
        System.out.println("Read data for " + list.size() + " quakes");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new DistanceFilter(new Location(39.7392, -104.9903), 1000));
        maf.addFilter(new PhraseFilter("any", "a"));
        ArrayList<QuakeEntry> filtered = EarthQuakeClient2.filter(list, maf);
        System.out.println(filtered.size() + " quake found.");
        System.out.println("Filters used: " + maf.getName());
    }

    private static void runQ7() {
        ClosestQuakes cq = new ClosestQuakes();
        cq.findLargestQuakes(source, 50);
    }

    private static void runQ6() {
        ClosestQuakes cq = new ClosestQuakes();
        cq.findLargestQuakes(source, 20);
    }

    private static void runQ5() {
        EarthQuakeClient eqc = new EarthQuakeClient();
        eqc.quakesByPhrase(source, "any", "Can");
    }

    private static void runQ4() {
        EarthQuakeClient eqc = new EarthQuakeClient();
        eqc.quakesByPhrase(source, "end", "Alaska");
    }

    private static void runQ3() {
        EarthQuakeClient eqc = new EarthQuakeClient();
        eqc.quakesByPhrase(source, "start", "Quarry Blast");
    }

    private static void runQ2() {
        EarthQuakeClient eqc = new EarthQuakeClient();
        eqc.quakesOfDepth(source, -4000.0, -2000.0);
    }

    private static void runQ1() {
        EarthQuakeClient eqc = new EarthQuakeClient();
        eqc.quakesOfDepth(source, -12000.0, -10000.0);
    }
}
