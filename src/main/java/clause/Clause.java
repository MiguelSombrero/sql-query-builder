package clause;

import database.column.AbstractColumnValue;
import database.column.ColumnValue;

import java.util.List;

public interface Clause {
    void append(String value);
    void append(int value);
    void append(double value);
    void insert(int index, String value);
    void addParam(AbstractColumnValue param);
    void mergeSubQuery(Clause clause);
    String getQueryString();
    List<ColumnValue> getParams();
}
