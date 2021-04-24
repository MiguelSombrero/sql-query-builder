package builder.field;

import builder.SQLQueryBuilder;
import builder.table.Table;

public class AliasedField extends SQLQueryBuilder {

    public AliasedField(StringBuilder builder) {
        this.builder = builder;
    }

    public Field field(String fieldName) {
        append(", ");
        append(fieldName);
        return new Field(this.builder);
    }

    public Table from(String table) {
        append(" FROM ");
        append(table);
        return new Table(this.builder);
    }

}
