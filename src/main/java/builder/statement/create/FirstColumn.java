package builder.statement.create;

import builder.SQLStringBuilder;

public class FirstColumn extends SQLStringBuilder {

    public FirstColumn(StringBuilder builder) {
        super(builder);
    }

    public ColumnType column(String columns) {
        int index = lastIndexOfRightBracket();
        insert(index, columns);
        return new ColumnType(this.builder);
    }
}
