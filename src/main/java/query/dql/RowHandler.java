package query.dql;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.handlers.AbstractListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RowHandler extends AbstractListHandler<Row> {
    private BasicRowProcessor convert;

    public RowHandler() {
        convert = new BasicRowProcessor();
    }

    @Override
    protected Row handleRow(ResultSet resultSet) throws SQLException {
        Map<String, Object> columns = this.convert.toMap(resultSet);
        Row row = new Row(columns);
        return row;
    }
}
