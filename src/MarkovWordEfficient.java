import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class MarkovWordEfficient implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private final int myOrder;
    private final HashMap<WordGram, ArrayList<String>> wordMap;

    /**
     * Initialize the MarkovWord object with the order specified
     *
     * @param order the order to initialize the MarkovWord object with
     */
    public MarkovWordEfficient(int order) {
        this.myRandom = new Random();
        this.myOrder = order;
        this.wordMap = new HashMap<>();
    }

    /**
     * String test is split into words and stored in myText.
     * Words are also used to initialize the training text.
     *
     * @param text the
     */
    @Override public void setTraining(String text) {
        this.myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
    }

    /**
     * Allows generation of the same random text each time, will help in testing program
     *
     * @param seed the int that represents the seed
     */
    @Override public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public String toString() {
        return "Markov Order of " + this.myOrder;
    }

    private void buildMap() {

        // Loop through the myTest Array to get a WordGram for length myOrder and generate follows ArrayList for each
        // WordGram and then check if it is already in the wordMap
        // If it already is in the wordMap, just append the new word to the back of the ArrayList at index WordGram
        // If it is not in the wordMap, create new key and add the ArrayList to it

        for (int index = 0; index <= this.myText.length - this.myOrder; ++index) {
            WordGram kGram = new WordGram(this.myText, index, this.myOrder);
            String toAdd = ((index + this.myOrder) < this.myText.length) ? myText[index + this.myOrder] : null;

            if (!wordMap.containsKey(kGram)) {
                ArrayList<String> follows = new ArrayList<>();
                if (toAdd != null) follows.add(myText[index + this.myOrder]);
                wordMap.put(kGram, follows);
            } else {
                ArrayList<String> currentFollow = wordMap.get(kGram);
                if (toAdd != null) currentFollow.add(myText[index + this.myOrder]);
                wordMap.put(kGram, currentFollow);
            }
        }
    }

    /**
     * Generates each word by randomly choosing a word from the training text that follows the current word(s) in the
     * training text. Might need to use shiftAdd.
     *
     * @param numChars the number of words to generate
     * @return the String that is randomly generate from N-Word Markov
     */
    @Override public String getRandomText(int numChars) {
        StringBuilder sb = new StringBuilder();

        // First find a random word to start with to append to the string
        // Need to create a new WordGram object at that initial found index
        // Then append the words in the WordGram to the StringBuilder

        WordGram wg = new WordGram(this.myText, myRandom.nextInt(myText.length - this.myOrder), this.myOrder);
        for (int i = 0; i < wg.length(); ++i) sb.append(wg.wordAt(i)).append(" ");

        for (int k = 0; k < this.myText.length - this.myOrder; ++k) {

            // Get an ArrayList of String that follows the randomly picked word
            // If the current follows ArrayList is empty, then break the loop
            // Get a random index from the ArrayList of follows and append it to the StringBuilder
            // Then update the WordGram object to add the latest word added

            ArrayList<String> follows = getFollows(wg);
            if (follows == null || follows.isEmpty()) break;
            String next = follows.get(myRandom.nextInt(follows.size()));
            sb.append(next).append(" ");
            wg = wg.shiftAdd(next);
        }

        return sb.toString().trim();
    }

    /**
     * Get the first position from start that has words that match the WordGram target
     *
     * @param words  the Array containing all the words to look for
     * @param target the WordGram object that is the target to look for
     * @param start  the starting index to look for a WordGram match in words
     * @return the first position from start that has words in the array words that match the WordGram target.
     * If there is no such much, return -1.
     */
    public int indexOf(String[] words, WordGram target, int start) {

        // The idea is to compare a copy of a WordGram to the target, if that particular copy and the target WordGram
        // is the same, then return the index of the first word in the copy WordGram
        // By definition, if the copy WordGram is to be the same as the target WordGram, the length needs to be the same
        // So the copy WordGram will be initialized with the same length as the target WordGram

        for (int i = start; i < this.myText.length - this.myOrder; ++i) {

            WordGram copy = new WordGram(words, i, this.myOrder);

            // Check if this copy of the WordGram is the same as the target WordGram
            // Need to use the equal method that was created in WordGram class to ensure that we are comparing
            // the right
            // thing and not looking if there are both the same object
            // Need to loop through the myTest Array in this class to find the index of the first instance where the
            // list of words show us
            // To get the index of the first word in the copy WordGram that is equal to the target WordGram,
            // return the
            // int start

            if (copy.equals(target)) return i;
        }

        return -1;
    }

    /**
     * Method will call indexOf to find matches
     *
     * @param kGram the WordGram to look for the words
     * @return an ArrayList of all single words that immediate follow an instance of the WordGram parameter somewhere
     * in the training text
     */
    public ArrayList<String> getFollows(WordGram kGram) {
        return wordMap.get(kGram);
    }

    public void printHashMapInfo() {
        // System.out.println(wordMap);
        System.out.println("Keys in HashMap: " + wordMap.size());

        int largest = 0;
        for (WordGram key : wordMap.keySet()) if (wordMap.get(key).size() > largest) largest = wordMap.get(key).size();
        System.out.println("Largest WordGram size: " + largest);

        System.out.println("Largest keys: ");
        for (WordGram key : wordMap.keySet()) {
            if (wordMap.get(key).size() == largest) System.out.println(key + " : " + wordMap.get(key));
        }
    }
}
