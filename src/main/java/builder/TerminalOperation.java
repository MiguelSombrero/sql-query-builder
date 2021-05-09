package builder;

public class TerminalOperation extends SQLStringBuilder implements Builder {

    public TerminalOperation(StringBuilder builder) {
        super(builder);
    }

    public String build() {
        return builder.toString();
    }
}
