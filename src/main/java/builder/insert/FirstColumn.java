package builder.insert;

import builder.SQLQueryBuilder;

public class FirstColumn extends SQLQueryBuilder {

    public FirstColumn(StringBuilder builder) {
        this.builder = builder;
    }

    public FirstValue column(String columns) {
        int index = firstIndexOfRightBracket();
        insert(index, columns);
        return new FirstValue(this.builder);
    }
}
