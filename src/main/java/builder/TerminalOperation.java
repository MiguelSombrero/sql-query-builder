package builder;

public class TerminalOperation extends SQLStringBuilder implements Builder {

    public TerminalOperation(StringBuilder builder) {
        this.builder = builder;
    }

    public String build() {
        this.builder.append(";");
        return builder.toString();
    }
}
