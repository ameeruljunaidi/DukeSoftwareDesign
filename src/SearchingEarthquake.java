public class SearchingEarthquake {
    public static void run() {
        String source = "data/EarthquakeData/nov20quakedata.atom";
        EarthQuakeClient eqc = new EarthQuakeClient();

        System.out.println("\nQuestion 1");
        eqc.quakesOfDepth(source, -10000.0, -8000.0);

        System.out.println("\nQuestion 2");
        eqc.quakesByPhrase(source, "start", "Explosion");

        System.out.println("\nQuestion 3");
        eqc.quakesByPhrase(source, "end", "California");

        System.out.println("\nQuestion 4");
        eqc.quakesByPhrase(source, "any", "Creek");


        ClosestQuakes cq = new ClosestQuakes();

        System.out.println("\nQuestion 5");
        cq.findLargestQuakes(source, 3);

        System.out.println("\nQuestion 6");
        cq.findLargestQuakes(source, 5);
    }
}
