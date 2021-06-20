package query;

public interface Statement {
    void append(String value);
    void appendFront(String value);
    String getQueryString();
}
