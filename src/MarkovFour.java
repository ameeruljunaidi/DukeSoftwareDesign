import java.util.ArrayList;
import java.util.Random;

public class MarkovFour extends AbstractMarkovModel {
    public MarkovFour() {
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

        int index = myRandom.nextInt(myText.length() - 4);
        String key = myText.substring(index, index + 4);
        sb.append(key);

        for (int k = 0; k < numChars - 4; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.isEmpty()) break;

            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);

            sb.append(next);
            key = key.substring(1, 4) + next;
        }

        return sb.toString();
    }

    @Override public String toString() {
        return "Markov Model of Order 4";
    }

}
