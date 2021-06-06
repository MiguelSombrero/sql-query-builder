package query.dql;

import org.apache.commons.dbutils.BasicRowProcessor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class CustomRowProcessor extends BasicRowProcessor {

    public CustomRowProcessor() {
    }

    public String[] parseColumnNames(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();

        int cols = metaData.getColumnCount();

        String[] columnNames = new String[cols];

        for (int i = 0; i < cols; i++) {
            columnNames[i] = metaData.getColumnName(i + 1);
        }

        return columnNames;
    }

    public int[] parseColumnTypes(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();

        int cols = metaData.getColumnCount();

        int[] columnTypes = new int[cols];

        for (int i = 0; i < cols; i++) {
            columnTypes[i] = metaData.getColumnType(i + 1);
        }

        return columnTypes;
    }
}
