package builder.create;

import builder.SQLStringBuilder;

public class ColumnType extends SQLStringBuilder {

    public ColumnType(StringBuilder builder) {
        this.builder = builder;
    }

    public Constraint type(DataType dataType) {
        int index = lastIndexOfRightBracket();
        insert(index, dataType.getType());
        insert(index, " ");
        return new Constraint(this.builder);
    }
}
