package builder.statement.insert;

import builder.SQLStringBuilder;

public class FirstColumn extends SQLStringBuilder {

    public FirstColumn(StringBuilder builder) {
        this.builder = builder;
    }

    public FirstValue column(String columns) {
        int index = firstIndexOfRightBracket();
        insert(index, columns);
        return new FirstValue(this.builder);
    }
}
