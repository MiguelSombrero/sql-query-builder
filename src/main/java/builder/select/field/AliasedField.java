package builder.select.field;

import builder.SQLStringBuilder;
import builder.select.table.Table;

public class AliasedField extends SQLStringBuilder {

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
