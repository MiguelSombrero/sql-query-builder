package query;

public class Query {
    private StringBuilder queryString;

    public Query(StringBuilder queryString) {
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

    public String build() {
        return queryString.toString();
    }
}
