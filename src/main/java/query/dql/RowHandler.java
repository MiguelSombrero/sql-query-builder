package query.dql;

import org.apache.commons.dbutils.handlers.AbstractListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowHandler extends AbstractListHandler<Row> {
    private CustomRowProcessor convert;

    public RowHandler() {
        convert = new CustomRowProcessor();
    }

    @Override
    protected Row handleRow(ResultSet resultSet) throws SQLException {
        Object[] columns = this.convert.toArray(resultSet);
        String[] columnNames = this.convert.parseColumnNames(resultSet);
        int[] columnTypes = this.convert.parseColumnTypes(resultSet);

        Row row = new Row();
        row.setColumns(columns);
        row.setColumnNames(columnNames);
        row.setColumnTypes(columnTypes);

        return row;
    }
}
