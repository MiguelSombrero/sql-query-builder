package database;


import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.AbstractListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowHandler extends AbstractListHandler<Row> {
    private final RowProcessor convert;

    public RowHandler() {
        convert = new BasicRowProcessor();
    }

    @Override
    protected Row handleRow(ResultSet resultSet) throws SQLException {
        Object[] columns = this.convert.toArray(resultSet);
        return new Row(columns);
    }
}
