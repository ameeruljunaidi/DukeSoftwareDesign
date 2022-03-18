import java.util.*;

public class EarthQuakeClient {
    public void printQuakes(ArrayList<QuakeEntry> data) {
        for (QuakeEntry qe : data) {
            System.out.println(qe);
        }
    }

    /**
     * Filter the earthquake list based on a minimum magnitude value
     *
     * @param quakeData the ArrayList containing all the QuakeEntry
     * @param magMin    the minimum magnitude to filter from
     * @return an ArrayList of type QuakeEntry of all the earthquakes from quakeData that have a magnitude > magMin
     */
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> earthquakes = new ArrayList<>(quakeData);
        ArrayList<QuakeEntry> answer = new ArrayList<>();

        for (QuakeEntry qe : earthquakes) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }

        return answer;
    }

    /**
     * Filter the earthquake list by distance
     *
     * @param quakeData the ArrayList that contains all the QuakeEntry data
     * @param distMax   the max distance to filter by in kilometres
     * @param from      the location to compare it to
     * @return ArrayList with QuakeEntry of all the earthquakes from quakeData that are less than distMax from the
     * location
     */
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> earthquakes = new ArrayList<>(quakeData);
        ArrayList<QuakeEntry> answer = new ArrayList<>();

        for (QuakeEntry qe : earthquakes) {
            double currentDistance = qe.getLocation().distanceTo(from) * 0.001;
            if (currentDistance < distMax) {
                answer.add(qe);
            }
        }

        return answer;
    }

    /**
     * Get earthquakes with depth between certain values
     *
     * @param quakeData the list that contains all the earthquake data
     * @param minDepth  the minimum depth of the earthquake
     * @param maxDepth  the maximum depth of the earthquake
     * @return the filtered ArrayList of the earthquakes
     */
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        assert minDepth <= maxDepth;

        ArrayList<QuakeEntry> earthquakes = new ArrayList<>(quakeData);
        ArrayList<QuakeEntry> answer = new ArrayList<>();

        for (QuakeEntry qe : earthquakes) {
            double currentDepth = qe.getDepth();
            if (currentDepth > minDepth && currentDepth < maxDepth) {
                answer.add(qe);
            }
        }

        return answer;
    }

    /**
     * Filter earthquakes by a phrase in the title given for the earthquake in three ways
     * 1. Title starts with a phrase
     * 2. Title ends with a phrase
     * 3. Phrase appears somewhere in the title
     *
     * @param quakeData the ArrayList that contains all the QuakeEntry
     * @param where     either "start", "end" or "any"
     * @param phrase    the String to look for
     * @return an ArrayList containing the QuakeEntry that is already filtered
     */
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        assert where.equals("start") || where.equals("end") || where.equals("any");

        ArrayList<QuakeEntry> earthquakes = new ArrayList<>(quakeData);
        ArrayList<QuakeEntry> answer = new ArrayList<>();

        for (QuakeEntry qe : earthquakes) {
            String title = qe.getInfo();
            int index = title.indexOf(phrase);
            String substring = index == -1 ? "" : title.substring(index);

            boolean start = where.equals("start") && index == 0;
            boolean end = where.equals("end") && substring.equals(phrase);
            boolean any = where.equals("any") && title.contains(phrase);

            if (start || end || any) {
                answer.add(qe);
            }
        }

        return answer;
    }

    /**
     * Prints out the CSV
     *
     * @param list list of earthquakes in an array list
     */
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for (QuakeEntry qe : list) {
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n", qe.getLocation().getLatitude(), qe.getLocation().getLongitude(),
                    qe.getMagnitude(), qe.getInfo());
        }

    }

    /**
     * Print out the size of the list from the source
     * Only print the filtered earthquakes
     *
     * @param source is the path or url to the earthquake data
     */
    public void bigQuakes(String source) {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);

        System.out.println("Read data for " + list.size() + " quakes");

        ArrayList<QuakeEntry> filtered = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : filtered) {
            System.out.println(qe);
        }
        System.out.println("filtered down to " + filtered.size() + " quakes");
    }

    /**
     * Print out the earthquakes within 1000km to a specified city
     *
     * @param source the source of the file to get the earthquakes from
     */
    public void closeToMe(String source) {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("Read data for " + list.size() + " quakes");

        // This location is Durham, NC
        // Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city = new Location(38.17, -118.82);

        ArrayList<QuakeEntry> filtered = filterByDistanceFrom(list, 1000.0, city);
        for (QuakeEntry qe : filtered) {
            System.out.println(qe);
        }
        System.out.println("Found " + filtered.size() + " quakes that match that criteria.");
    }

    /**
     * Test the filterByDepth method that was previously created
     * Print all the earthquakes from a data source whose depth is between a given minimum and maximum value
     * Should also print out the number of earthquakes found
     *
     * @param source the source of the file to get the earthquakes from
     */
    public void quakesOfDepth(String source, double minDepth, double maxDepth) {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("Read data for " + list.size() + " quakes");

        ArrayList<QuakeEntry> filtered = filterByDepth(list, minDepth, maxDepth);
        System.out.println("Find quakes with depth between " + minDepth + " and " + maxDepth);
        System.out.println("Found " + filtered.size() + " quakes that match that criteria.");
    }

    /**
     * Test the method filterByPhrase that was created previously
     * Print all the earthquakes from a data source that have a phrase in their title
     * Also print out the number of earthquakes found
     *
     * @param source the source of the file to get the all earthquake data
     * @param where  either "start", "end" or "any"
     * @param phrase the String to look for
     */
    public void quakesByPhrase(String source, String where, String phrase) {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("Read data for " + list.size() + " quakes");

        ArrayList<QuakeEntry> filtered = filterByPhrase(list, where, phrase);
        System.out.println("Found " + filtered.size() + " quakes that match " + phrase + " at " + where + "\n");
    }

    /**
     * Test to see if the dumpCSV function is implemented correctly
     *
     * @param source is the path or url to the earthquake data
     */
    public void createCSV(String source) {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

}
