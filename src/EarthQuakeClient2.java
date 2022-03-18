import java.util.ArrayList;

public class EarthQuakeClient2 {
    /**
     * Generic filter method that takes in a Filter class
     *
     * @param quakeData the ArrayList containing all the earthquake data
     * @param f         the class that implements the Filter interface
     * @return an ArrayList that is filtered based on the filter class provided
     */
    public static ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (QuakeEntry qe : quakeData) {
            if (f.satisfies(qe)) {
                answer.add(qe);
            }
        }

        return answer;
    }

    /**
     * Tests a particular source to see if valid earthquake data is found
     *
     * @param source the path to the xml file that contains the earthquake data
     */
    public void createCSV(String source) {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    /**
     * Print all the earthquake data acquired from a source file
     *
     * @param list the ArrayList containing all the earthquake data
     */
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for (QuakeEntry qe : list) {
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n", qe.getLocation().getLatitude(), qe.getLocation().getLongitude(),
                    qe.getMagnitude(), qe.getInfo());
        }
    }

}
