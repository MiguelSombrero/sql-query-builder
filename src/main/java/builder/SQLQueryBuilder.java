package builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SQLQueryBuilder implements Builder {
    private static Logger logger = LoggerFactory.getLogger(SQLQueryBuilder.class);
    protected StringBuilder builder;

    public String build() {
        append(";");
        return builder.toString();
    }

    public void append(String text) {
        this.builder = this.builder.append(text);
    }

    public void append(Integer value) {
        this.builder = this.builder.append(value);
    }

    public void insert(Integer index, String text) {
        this.builder = this.builder.insert(index, text);
    }
}
