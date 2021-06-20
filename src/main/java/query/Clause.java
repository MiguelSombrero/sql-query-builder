package query;

import database.column.ColumnValue;

import java.util.List;

public interface Clause extends Statement {
    void addParam(ColumnValue param);
    void mergeClause(Clause clause);
    List<ColumnValue> getParams();
}
