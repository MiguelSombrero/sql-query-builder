package builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SQLQueryBuilder implements Builder {
    protected static Logger logger = LoggerFactory.getLogger(SQLQueryBuilder.class);
    protected StringBuilder builder;

    public String build() {
        append(";");
        return builder.toString();
    }

    public void append(String text) {
        this.builder = this.builder.append(text);
    }

    public void append(int value) {
        this.builder = this.builder.append(value);
    }

    public void insert(int index, String text) {
        this.builder = this.builder.insert(index, text);
    }

    public void insert(int index, int value) {
        this.builder = this.builder.insert(index, value);
    }

    protected int firstIndexOfRightBracket() {
        return this.builder.indexOf(")");
    }

    protected int firstIndexOfLeftBracket() {
        return this.builder.indexOf("(");
    }

    protected int lastIndexOfRightBracket() {
        return this.builder.lastIndexOf(")");
    }

}
