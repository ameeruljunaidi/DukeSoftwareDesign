
/**
 * Write a description of class MarkovRunner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size) {
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

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
        FileResource fr = new FileResource("data/MarkovData/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovWordEfficient markovWord = new MarkovWordEfficient(2);
        runModel(markovWord, st, 10, 65);
    }


    public void runMarkovZero() {
        FileResource fr = new FileResource("data/MarkovData/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovZero markov = new MarkovZero();
        markov.setRandom(1024);
        markov.setTraining(st);
        for (int k = 0; k < 3; k++) {
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    public void runMarkovOne() {
        FileResource fr = new FileResource("data/MarkovData/romeo.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setRandom(365);
        markov.setTraining(st);
        for (int k = 0; k < 1; k++) {
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    public void runMarkovFour() {
        FileResource fr = new FileResource("data/MarkovData/romeo.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovFour markov = new MarkovFour();
        markov.setRandom(715);
        markov.setTraining(st);
        for (int k = 0; k < 1; k++) {
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    public void runMarkovModel() {
        FileResource fr = new FileResource("data/MarkovData/romeo.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovModel markov = new MarkovModel(7);
        markov.setRandom(953);
        markov.setTraining(st);
        for (int k = 0; k < 1; k++) {
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    public void testHashMapMarkovModel() {
        FileResource fr = new FileResource("data/MarkovData/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        EfficientMarkovModel mModel = new EfficientMarkovModel(6);
        runModel(mModel, st, 10, 792);
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

    public void testHashMap() {
        String st = "this is a test yes this is really a test yes a test this is wow";
        st = st.replace('\n', ' ');
        MarkovWordEfficient markovWord = new MarkovWordEfficient(2);
        runModel(markovWord, st, 50, 42);
    }

    public void compareMethods() {
        FileResource fr = new FileResource("data/MarkovData/hawthorne.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');

        long begin, end, elapsedTime;
        double elapsedTimeInSecond;

        begin = System.nanoTime();
        MarkovWordEfficient markovWordEfficient = new MarkovWordEfficient(2);
        runModel(markovWordEfficient, st, 100, 42);
        end = System.nanoTime();
        elapsedTime = end - begin;
        elapsedTimeInSecond = (double) elapsedTime / 1_000_000_000;
        System.out.println("Completed in: " + elapsedTimeInSecond + " seconds");

        begin = System.nanoTime();
        MarkovWord markovWord = new MarkovWord(2);
        runModel(markovWord, st, 100, 42);
        end = System.nanoTime();
        elapsedTime = end - begin;
        elapsedTimeInSecond = (double) elapsedTime / 1_000_000_000;
        System.out.println("Completed in: " + elapsedTimeInSecond + " seconds");
    }
}
