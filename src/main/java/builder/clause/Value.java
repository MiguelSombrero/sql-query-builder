package builder.clause;

public class Value extends Alias {

    public Value(StringBuilder builder) {
        super(builder);
    }

    public Alias alias(String text) {
        append(" AS ");
        append(text);
        return new Alias(this.builder);
    }
}
