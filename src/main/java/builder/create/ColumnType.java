package builder.create;

import builder.SQLQueryBuilder;

public class ColumnType extends SQLQueryBuilder {

    public ColumnType(StringBuilder builder) {
        this.builder = builder;
    }

    public Column type(DataType dataType) {
        int index = firstIndexOfRightBracket();
        insert(index, dataType.toString());
        insert(index, " ");
        return new Column(this.builder);
    }


}
