/**
 * Write a description of class MarkovWordOne here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
    }

    /**
     * Starts looking at the start position and returns the first index location in words that matches target
     * If not word is found, then this method returns -1
     *
     * @param words  the array containing all the words in the training set
     * @param target the word to look for
     * @param start  the starting index to look for
     * @return an index of the words that proceeds the target word
     */
    private int indexOf(String[] words, String target, int start) {
        for (int i = start; i < words.length; ++i) {
            if (words[i].equals(target)) return i;
        }

        return -1;
    }

    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - 1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for (int k = 0; k < numWords - 1; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }

        return sb.toString().trim();
    }

    /**
     * Method should call the indexOf method
     *
     * @param key the target string to look for
     * @return an ArrayList of all the single words that immediately follow the key in the training test
     */
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<>();

        int currentIndex = 0;
        while (currentIndex < myText.length) {
            int findIndex = indexOf(myText, key, currentIndex);
            String proceedingWord = (findIndex != -1 && findIndex != myText.length - 1) ? myText[findIndex + 1] : null;
            if (proceedingWord != null) follows.add(proceedingWord);
            currentIndex = (findIndex == -1) ? -1 : findIndex + 1;
            if (currentIndex == -1) break;
        }

        return follows;
    }

    public void testIndexOf() {
        String word = "this is just a test yes this is a simple test";
        String[] words = word.split("\\s+");
        System.out.println("Index of this starting at 0: " + indexOf(words, "this", 0));
        System.out.println("Index of this starting at 3: " + indexOf(words, "this", 3));
        System.out.println("Index of frog starting at 0: " + indexOf(words, "frog", 0));
        System.out.println("Index of frog starting at 5: " + indexOf(words, "frog", 5));
        System.out.println("Index of simple starting at 2: " + indexOf(words, "simple", 2));
        System.out.println("Index of test starting at 5: " + indexOf(words, "test", 5));
    }

    public void testGetFollows() {
        for (String w : myText) {
            ArrayList<String> follows = getFollows(w);
            System.out.println("Key: " + w + ". Follows: " + follows);
        }
    }

}
