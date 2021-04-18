package builder.field;

import builder.SQLQueryBuilder;
import builder.field.Field;

public class FirstField extends SQLQueryBuilder {

    public FirstField(StringBuilder builder) {
        this.builder = builder;
    }

    public Field field(String fieldName) {
        append(fieldName);
        return new Field(this.builder);
    }
}
