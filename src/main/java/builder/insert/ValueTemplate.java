package builder.insert;

import builder.SQLQueryBuilder;

public abstract class ValueTemplate extends SQLQueryBuilder {

    public ValueTemplate(StringBuilder builder) {
        this.builder = builder;
    }

    protected Column value(String value) {
        int index = lastIndexOfRightBracket();
        insert(index, "'");
        insert(index, value);
        insert(index, "'");
        addCommaAfterFirstValue(index);
        return new Column(this.builder);
    }

    protected Column value(int value) {
        int index = lastIndexOfRightBracket();
        insert(index, value);
        addCommaAfterFirstValue(index);
        return new Column(this.builder);
    }

    protected abstract void addCommaAfterFirstValue(int index);
}
