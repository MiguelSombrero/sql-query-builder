package builder.insert;

import builder.SQLQueryBuilder;

public class Table extends SQLQueryBuilder {

    public Table(StringBuilder builder) {
        this.builder = builder;
    }

    public FirstColumn table(String tableName) {
        int index = firstIndexOfLeftBracket();
        insert(index, " ");
        insert(index, tableName);
        return new FirstColumn(this.builder);
    }

    protected int firstIndexOfLeftBracket() {
        return this.builder.indexOf("(");
    }
}
