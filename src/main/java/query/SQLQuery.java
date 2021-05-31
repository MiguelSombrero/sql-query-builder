package query;

public class SQLQuery extends QueryTemplate {

    public SQLQuery(StringBuilder queryString) {
        super(queryString);
    }

    public String build() {
        return queryString.toString();
    }

    @Override
    public String toString() {
        return queryString.toString();
    }
}
