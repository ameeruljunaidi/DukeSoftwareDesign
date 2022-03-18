/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int seed;

    public AbstractMarkovModel() {
        myRandom = new Random();
    }

    public void setTraining(String s) {
        myText = s.trim();
    }


    abstract public String getRandomText(int numChars);

    /**
     * Find all the characters from the private variable myText that follow key and put all these characters into
     * an ArrayList and then return this ArrayList
     *
     * @param key the String to look for
     * @return an ArrayList containing all the Strings
     */
    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> ret = new ArrayList<>();

        int index = 0;
        while (index != -1 && index < myText.length()) {
            index = myText.indexOf(key, index);

            if (index != -1 && (index + key.length()) < myText.length()) {
                ret.add(myText.substring(index + key.length(), index + key.length() + 1));
                index++; // start looking at the next index
            } else {
                break;
            }
        }

        return ret;
    }

    abstract public String toString();
}
