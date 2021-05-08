package builder;

public class TerminalOperation extends SQLStringBuilder implements Builder {

    public TerminalOperation() {
    }

    public TerminalOperation(StringBuilder builder) {
        this.builder = builder;
    }

    public String build() {
        return builder.toString();
    }
}
