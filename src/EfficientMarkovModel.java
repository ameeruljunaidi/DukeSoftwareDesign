import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private final int order;
    private HashMap<String, ArrayList<String>> followsMap;

    public EfficientMarkovModel(int order) {
        myRandom = new Random();
        this.order = order;
        this.followsMap = new HashMap<>();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        myText = s.trim();
        buildMap();
        printHashMapInfo();
    }

    public void buildMap() {
        for (int i = 0; i <= myText.length() - order; i++) {
            String key = myText.substring(i, i + order);
            String follow = ((i + order) < myText.length()) ? myText.substring(i + order, i + order + 1) : null;

            if (!followsMap.containsKey(key)) {
                ArrayList<String> newFollows = new ArrayList<>();
                if (follow != null) newFollows.add(follow);
                followsMap.put(key, newFollows);
            } else {
                ArrayList<String> currentFollows = followsMap.get(key);
                if (follow != null) currentFollows.add(follow);
                followsMap.put(key, currentFollows);
            }
        }
    }


    @Override public ArrayList<String> getFollows(String key) {
        return followsMap.get(key);
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

        int index = myRandom.nextInt(myText.length() - order);
        String key = myText.substring(index, index + order);
        sb.append(key);

        for (int k = 0; k < numChars - order; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows == null || follows.isEmpty()) break;

            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);

            sb.append(next);
            key = key.substring(1, order) + next;
        }

        return sb.toString();
    }

    @Override public String toString() {
        return "EfficientMarkovModel of Order " + order;
    }

    public void printHashMapInfo() {
        // System.out.println("All keys and values are:");
        // for (String key : followsMap.keySet()) {
        //     System.out.print(key + " " + followsMap.get(key) + "\n");
        // }

        System.out.println("Number of keys in HashMap: " + followsMap.size());

        int largest = 0;
        for (String key : followsMap.keySet()) {
            if (followsMap.get(key).size() > largest) largest = followsMap.get(key).size();
        }
        System.out.println("Largest value in HashMap: " + largest);

        System.out.print("Keys with largest value: ");
        for (String key : followsMap.keySet()) {
            if (followsMap.get(key).size() == largest) System.out.print(key + " ");
        }
        System.out.println();
    }

}
