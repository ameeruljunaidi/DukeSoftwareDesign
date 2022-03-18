import java.util.ArrayList;

/**
 * Find N-closest quakes
 *
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */


public class ClosestQuakes {
    public void printQuakes(ArrayList<QuakeEntry> data) {
        for (QuakeEntry qe : data) {
            System.out.println(qe);
        }
    }

    /**
     * Find the closes number of howMany earthquakes to the current location and return them in a list
     * The earthquakes should be in the ArrayList in order with the closest earthquake in index position 0
     * If there are fewer than howMany earthquakes in quakeData, then the ArrayList return would be the same as
     *
     * @param quakeData the ArrayList that contain earthquake data in QuakeEntry
     * @param current   the current Location that we are comparing with
     * @param howMany   the number of earthquakes we want to get from this functions
     * @return ArrayList of earthquakes sorted from closest to furthest
     */
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> earthquakes = new ArrayList<>(quakeData);
        ArrayList<QuakeEntry> answer = new ArrayList<>();

        for (int i = 0; i < howMany; i++) {
            int index = 0;
            double closestDistance = 0;

            for (int j = 0; j < earthquakes.size(); j++) {
                double currentDistance = earthquakes.get(j).getLocation().distanceTo(current) * 0.001;

                if ((closestDistance == 0 && index == 0) || currentDistance < closestDistance) {
                    closestDistance = currentDistance;
                    index = j;
                }
            }

            answer.add(earthquakes.get(index));
            earthquakes.remove(index);
        }

        return answer;
    }

    /**
     * Get the earthquake with the largest magnitude
     *
     * @param data the ArrayList containing all the QuakeEntry
     * @return an index of the largest earthquake
     */
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int index = -1;
        double maxMag = -1;

        for (int i = 0; i < data.size(); i++) {
            QuakeEntry qe = data.get(i);
            double currentMag = qe.getMagnitude();
            if (index == -1 || currentMag > maxMag) {
                index = i;
                maxMag = currentMag;
            }
        }

        return index;
    }

    /**
     * Get the largest howMany number of earthquakes into a list
     *
     * @param quakeData the ArrayList that contains QuakeEntry data of all the earthquake
     * @param howMany   how many largest earthquake do we want
     * @return the ArrayList containing howMany number of the largest earthquakes
     */
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> earthquakes = new ArrayList<>(quakeData);
        ArrayList<QuakeEntry> answer = new ArrayList<>();

        for (int i = 0; i < howMany; i++) {
            int indexOfLargest = indexOfLargest(earthquakes);
            answer.add(earthquakes.get(indexOfLargest));
            earthquakes.remove(indexOfLargest);
        }

        return answer;
    }

    /**
     * Reads in data on earthquakes storing them in the ArrayList list and prints how many quakes there are
     * Sets the location variable named jakarta to the location of the city Jakarta
     *
     * @param source the file that contains the earthquake data
     */
    public void findClosestQuakes(String source) {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("Read data for " + list.size());

        Location jakarta = new Location(-6.211, 106.845);

        ArrayList<QuakeEntry> close = getClosest(list, jakarta, 3);
        for (QuakeEntry entry : close) {
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters / 1000, entry);
        }
        System.out.println("number found: " + close.size());
    }

    /**
     * Prints all the earthquakes and how many earthquakes that were from the source
     *
     * @param source the source file of the earthquakes
     */
    public void findLargestQuakes(String source, int howMany) {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("Read data for " + list.size());

        ArrayList<QuakeEntry> filtered = getLargest(list, howMany);
        printQuakes(filtered);
    }
}
