package builder.select.field;

public class Field extends AliasedField {

    public Field(StringBuilder builder) {
        super(builder);
    }

    public AliasedField alias(String text) {
        append(" AS ");
        append(text);
        return new AliasedField(this.builder);
    }
}
