package query;

public class SQLQuery implements Query {
    protected StringBuilder queryString;

    public SQLQuery(StringBuilder queryString) {
        this.queryString = queryString;
    }

    public void append(String value) {
        this.queryString = this.queryString.append(value);
    }

    public void append(int value) {
        this.queryString = this.queryString.append(value);
    }

    public void append(double value) {
        this.queryString = this.queryString.append(value);
    }

    public void insert(int index, String value) {
        this.queryString = this.queryString.insert(index, value);
    }

    @Override
    public String toString() {
        return queryString.toString();
    }
}
