package query;

public interface Query {
    void append(String value);
    void append(int value);
    void append(double value);
    void insert(int index, String value);
    void addParam(Object param);
}
