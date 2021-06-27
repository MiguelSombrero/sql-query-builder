package query;

import database.column.ColumnValue;

import java.util.List;

public interface Statement {
    void append(String value);
    void appendFront(String value);
    String getQueryString();
    void addParam(ColumnValue param);
    void mergeStatement(Statement statement);
    List<ColumnValue> getParams();
}
