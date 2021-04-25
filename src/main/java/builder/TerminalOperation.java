package builder;

public abstract class TerminalOperation extends SQLStringBuilder implements Builder {

    public String build() {
        this.builder.append(";");
        return builder.toString();
    }
}
