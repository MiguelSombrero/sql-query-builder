package builder.insert;

import builder.SQLQueryBuilder;

public class FirstValue extends SQLQueryBuilder {

    public FirstValue(StringBuilder builder) {
        this.builder = builder;
    }

    public Column value(String value) {
        int index = lastIndexOfRightBracket();
        return insertStringValue(index, value);
    }

    public Column value(int value) {
        int index = lastIndexOfRightBracket();
        return insertIntegerValue(index, value);
    }

    private Column insertStringValue(int index, String value) {
        insert(index, "'");
        insert(index, value);
        insert(index, "'");
        return new Column(this.builder);
    }

    private Column insertIntegerValue(int index, Integer value) {
        insert(index, value);
        return new Column(this.builder);
    }

    protected int lastIndexOfRightBracket() {
        return this.builder.lastIndexOf(")");
    }
}
