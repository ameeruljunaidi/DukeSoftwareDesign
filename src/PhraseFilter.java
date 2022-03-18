import org.jetbrains.annotations.NotNull;

public class PhraseFilter implements Filter {
    private final String where;
    private final String phrase;

    /**
     * @param where  either "start", "end", or "any"
     * @param phrase is the word to look for in the title
     */
    public PhraseFilter(String where, String phrase) {
        this.where = where;
        this.phrase = phrase;
    }

    /**
     * @param qe the QuakeEntry object
     * @return if the earthquake has the phrase at the location indicated
     */
    @Override public boolean satisfies(@NotNull QuakeEntry qe) {
        String title = qe.getInfo();
        int index = title.indexOf(this.phrase);
        String substring = index == -1 ? "" : title.substring(index);

        boolean start = this.where.equals("start") && index == 0;
        boolean end = this.where.equals("end") && substring.equals(phrase);
        boolean any = this.where.equals("any") && title.contains(phrase);

        return start || end || any;
    }

    @Override public String getName() {
        return "Phrase Filter";
    }
}
