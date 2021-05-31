package query;

public interface Query {
    String build();
    void append(String value);
    void append(int value);
    void append(double value);
    void insert(int index, String value);
}
