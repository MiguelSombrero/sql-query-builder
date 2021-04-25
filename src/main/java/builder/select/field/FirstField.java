package builder.select.field;

import builder.SQLStringBuilder;

public class FirstField extends SQLStringBuilder {

    public FirstField(StringBuilder builder) {
        this.builder = builder;
    }

    public Field field(String fieldName) {
        append(fieldName);
        return new Field(this.builder);
    }
}
