import java.util.ArrayList;
import java.util.Random;

public class MarkovOne extends AbstractMarkovModel {
    public MarkovOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    /**
     * Returns random text that is numChars long
     * This class generates each letter by randomly choosing a letter form the training text
     * Predict the nxt character, by finding all the characters that follow th current character in the training set
     * Randomly picking one of them as a character
     *
     * @param numChars the length of the string
     * @return a string that is randomly generated
     */
    public String getRandomText(int numChars) {
        if (myText == null) return "";

        StringBuilder sb = new StringBuilder();

        String key = String.valueOf(myText.charAt(myRandom.nextInt(myText.length() - 1)));
        sb.append(key);

        for (int k = 0; k < numChars - 1; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.isEmpty()) break;

            int index = myRandom.nextInt(follows.size());
            String next = follows.get(index);

            sb.append(next);
            key = next;
        }

        return sb.toString();
    }

    @Override public String toString() {
        return "MarkovModel of Order 1";
    }

}
