/**
 * This class represents words that have an ordering.
 * E.g. a WordGram might be the four words "this" "is" "a" "test" in this order
 */
public class WordGram {
    private final String[] myWords; // Store the words in order, one word per slot
    private final int myHash;             // Use to be able to use WordGrams as a key with a HashMap

    /**
     * The constructor copies the size number of words from source starting at the position start into a new WordGram.
     *
     * @param source the Array containing all the words in order
     * @param start  the first index to start copying at
     * @param size   the size of the Array being passed to the constructor
     */
    public WordGram(String[] source, int start, int size) {
        this.myWords = new String[size];
        this.myHash = hashCode();
        System.arraycopy(source, start, myWords, 0, size);
    }

    /**
     * Trying to emulate indexing for String
     *
     * @param index the index of the word to be retrieved
     * @return the word in the WordGram at position index
     */
    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt " + index);
        }
        return myWords[index];
    }

    /**
     * Get the length of the WordGram
     *
     * @return the length of the WordGram
     */
    public int length() {
        return myWords.length;
    }

    /**
     * Print a WordGram out, showing all the words in the WordGram on one line separated by spaces
     *
     * @return all the words separated by spaces
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (String myWord : myWords) ret.append(myWord).append(" ");
        return ret.toString().trim();
    }

    /**
     * Properly check if two WordGrams are actually the same
     *
     * @param o the WordGram object to pass into the equals method
     * @return true if two WordGrams are equal and false otherwise
     */
    public boolean equals(Object o) {
        if (o == null) return false;
        WordGram other = (WordGram) o;

        // There are two main checks for equal WordGrams:
        // 1. If the length of the two WordGrams are not the same, that is an automatic false.
        // 2. Else if the word at index i in either WordGrams are not the same, then return false.

        if (this.length() != other.length()) return false;
        for (int i = 0; i < this.length(); i++) if (!this.wordAt(i).equals(other.wordAt(i))) return false;

        return true;
    }

    public int hashCode() {
        return this.toString().hashCode();
    }

    /**
     * Return a new WordGram with the words shifted one (to the front) and the last word in the String is going to be
     * replaced by a word that is passed in the parameter.
     * This method does not replace the WordGRam on which it is called
     *
     * @param word the word to add at the end of the String
     * @return a new WordGram the same size as the original, consisting of each word shifted down one index.
     * E.g. Word in slot 1 would move to slot 0 and word in slot 2 would move to slot 1
     */
    public WordGram shiftAdd(String word) {
        WordGram out = new WordGram(myWords, 0, myWords.length);

        // First need to shift all the words by one position to the front
        // Loop through the array until the second last element because we don't need to shift the last element
        // The last position in the array would then have the same word as the second last
        // Replace the word in the last position in the array with the word that is passed in the parameter

        for (int i = 0; i < out.length() - 1; i++) out.myWords[i] = out.wordAt(i + 1);
        out.myWords[out.length() - 1] = word;

        return out;
    }

}