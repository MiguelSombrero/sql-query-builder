package builder.create;

import builder.SQLStringBuilder;

public class Table extends SQLStringBuilder {

    public Table(StringBuilder builder) {
        this.builder = builder;
    }

    public FirstColumn table(String tableName) {
        int index = firstIndexOfLeftBracket();
        insert(index, " ");
        insert(index, tableName);
        return new FirstColumn(this.builder);
    }
}
