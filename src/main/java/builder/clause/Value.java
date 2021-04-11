package builder.clause;

public class Value extends As {

    public Value(StringBuilder builder) {
        super(builder);
    }

    public As as(String text) {
        append(" AS ");
        append(text);
        return new As(this.builder);
    }
}
