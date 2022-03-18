/**
 * Write a description of class MarkovRunner here.
 *
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runMarkov() {
        FileResource fr = new FileResource("data/MarkovData/romeo.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;

        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, 88);

        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, 88);

        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, 88);

        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, 88);

    }

    public void testHashMap() {
        FileResource fr = new FileResource("data/MarkovData/romeo.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        EfficientMarkovModel mFive = new EfficientMarkovModel(5);
        runModel(mFive, st, 1000, 42);
    }

    public void compareMethods() {
        FileResource fr = new FileResource("data/MarkovData/hawthorne.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;

        long start;
        long end;
        double elapsed;

        start = System.nanoTime();
        MarkovModel mOne = new MarkovModel(2);
        System.out.println("Not efficient ------------------");
        runModel(mOne, st, size, seed);
        end = System.nanoTime();
        elapsed = (double) (end - start) / 1_000_000_000;
        System.out.println("Completed in: " + elapsed + " seconds");

        start = System.nanoTime();
        EfficientMarkovModel mOneEfficient = new EfficientMarkovModel(2);
        System.out.println("Efficient ------------------");
        runModel(mOneEfficient, st, size, seed);
        end = System.nanoTime();
        elapsed = (double) (end - start) / 1_000_000_000;
        System.out.println("Completed in: " + elapsed + " seconds");
    }

    private void printOut(String s) {
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for (int k = 0; k < words.length; k++) {
            System.out.print(words[k] + " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }

}
