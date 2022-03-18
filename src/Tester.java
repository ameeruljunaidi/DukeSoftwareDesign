import edu.duke.FileResource;

import java.util.ArrayList;

public class Tester {
    public void testGetFollows() {
        MarkovOne mo = new MarkovOne();
        String training = "this is a test yes this is a test.";
        mo.setTraining(training);
        ArrayList<String> follows = mo.getFollows("t.");
        System.out.println(follows);
    }

    public void testGetFollowsWithFile() {
        MarkovOne mo = new MarkovOne();
        FileResource fr = new FileResource("data/MarkovData/confucius.txt");
        String training = fr.asString();
        training = training.replace('\n', ' ');
        mo.setTraining(training);
        ArrayList<String> follows = mo.getFollows("he");
        System.out.println(follows.size());
        System.out.println(follows);
    }
}
