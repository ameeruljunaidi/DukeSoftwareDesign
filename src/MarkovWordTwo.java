import java.util.ArrayList;
import java.util.Random;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo() {
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
     * @param words   the array containing all the words in the training set
     * @param target1 the word to look for
     * @param target2 the word to look for
     * @param start   the starting index to look for
     * @return an index of the words that proceeds the target word
     */
    private int indexOf(String[] words, String target1, String target2, int start) {
        for (int i = start; i < words.length - 1; ++i) {
            if (words[i].equals(target1) && words[i + 1].equals(target2)) return i;
        }

        return -1;
    }

    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - 2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index + 1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for (int k = 0; k < numWords - 2; k++) {
            ArrayList<String> follows = getFollows(key1, key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }

        return sb.toString().trim();
    }

    /**
     * Method should call the indexOf method
     *
     * @param key1 the target string to look for
     * @param key2 the target string to look for
     * @return an ArrayList of all the single words that immediately follow the key in the training test
     */
    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<>();

        int currentIndex = 0;
        while (currentIndex < myText.length) {
            int findIndex = indexOf(myText, key1, key2, currentIndex);
            String proceedingWord = (findIndex != -1 && findIndex != myText.length - 2) ? myText[findIndex + 2] : null;
            if (proceedingWord != null) follows.add(proceedingWord);
            currentIndex = (findIndex == -1) ? -1 : findIndex + 1;
            if (currentIndex == -1) break;
        }

        return follows;
    }

    public void testIndexOf() {
        String word = "this is just a test yes this is a simple test";
        String[] words = word.split("\\s+");
        System.out.println("Index of 'this is' starting at 2: " + indexOf(words, "this", "is", 2));
    }

    public void testGetFollows() {
        for (int i = 0; i < myText.length - 1; i++) {
            ArrayList<String> follows = getFollows(myText[i], myText[i + 1]);
            System.out.println("Key: " + myText[i] + " and " + myText[i + 1] + ". Follows: " + follows);
        }
    }
}
