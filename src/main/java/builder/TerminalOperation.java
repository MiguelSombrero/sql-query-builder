package builder;

public class TerminalOperation extends SQLStringAppender implements Builder {

    public TerminalOperation(StringBuilder builder) {
        super(builder);
    }

    public String build() {
        return queryString.toString();
    }
}
