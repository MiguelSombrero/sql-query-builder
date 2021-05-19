package builder;

public class TerminalOperation implements Builder {

    protected Query query;

    public TerminalOperation(Query query) {
        this.query = query;
    }

    public String build() {
        return query.build();
    }
}
