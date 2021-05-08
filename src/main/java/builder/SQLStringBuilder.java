package builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SQLStringBuilder {
    protected static Logger logger = LoggerFactory.getLogger(SQLStringBuilder.class);

    protected StringBuilder builder;

    protected void append(String text) {
        this.builder = this.builder.append(text);
    }

    protected void append(Integer value) {
        this.builder = this.builder.append(value);
    }

    protected void append(Double value) {
        this.builder = this.builder.append(value);
    }

    protected void appendStringValue(String value) {
        append("'");
        append(value);
        append("'");
    }

    protected void insert(Integer index, String text) {
        this.builder = this.builder.insert(index, text);
    }

    protected void insert(int index, int value) {
        this.builder = this.builder.insert(index, value);
    }

    protected int lastIndexOfRightBracket() {
        return this.builder.lastIndexOf(")");
    }
}
