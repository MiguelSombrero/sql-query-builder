package builder.update;

import builder.where.Negation;

public class Column extends ColumnTemplate {

    public Column(StringBuilder builder) {
        super(builder);
    }

    public Negation where(String value) {
        append(" WHERE ");
        append(value);
        return new Negation(this.builder);
    }

    @Override
    protected void addCommaAfterFirstValue() {
        append(", ");
    }
}
