package builder.insert;

import builder.SQLQueryBuilder;

public class Column extends SQLQueryBuilder {

    public Column(StringBuilder builder) {
        this.builder = builder;
    }

    public Value column(String columns) {
        int index = firstIndexOfRightBracket();
        insert(index, columns);
        insert(index, ", ");
        return new Value(this.builder);
    }

    protected int firstIndexOfRightBracket() {
        return this.builder.indexOf(")");
    }
}
