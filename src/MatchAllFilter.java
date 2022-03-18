import java.util.ArrayList;

public class MatchAllFilter implements Filter {
    private final ArrayList<Filter> filters;

    public MatchAllFilter() {
        this.filters = new ArrayList<>();
    }

    public void addFilter(Filter filter) {
        this.filters.add(filter);
    }

    @Override public boolean satisfies(QuakeEntry qe) {
        for (Filter f : this.filters) {
            if (!f.satisfies(qe)) return false;
        }
        return true;
    }

    @Override public String getName() {
        StringBuilder ret = new StringBuilder();
        for (Filter f : this.filters) {
            if (ret.isEmpty()) {
                ret.append(f.getName());
            } else {
                ret.append(", ").append(f.getName());
            }
        }

        return ret.toString();
    }
}
