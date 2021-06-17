package database.row;

import database.column.ColumnValue;
import org.apache.commons.dbutils.handlers.AbstractListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RowHandler extends AbstractListHandler<Row> {
    private CustomRowProcessor convert;

    public RowHandler() {
        this.convert = new CustomRowProcessor();
    }

    @Override
    protected Row handleRow(ResultSet resultSet) throws SQLException {
        Map<String, ColumnValue> columns = this.convert.toMap(resultSet);
        Row row = new Row(columns);
        return row;
    }
}
