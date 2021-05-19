package builder;

public class TerminalOperation extends Query implements Builder {

    public TerminalOperation(StringBuilder builder) {
        super(builder);
    }

    public String build() {
        return queryString.toString();
    }
}
