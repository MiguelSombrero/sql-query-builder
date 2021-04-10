package builder;

public class Insert extends SQLQueryBuilder {

    public Insert(StringBuilder builder) {
        this.builder = builder;
    }

    public Insert toColumns(String columns) {
        append("(");
        append(columns);
        append(")");
        return this;
    }

    public Insert values(String values) {
        append("(");
        append(values);
        append(")");
        return this;
    }
}
