package query.dql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CustomRowProcessor {
    private static Logger logger = LoggerFactory.getLogger(CustomRowProcessor.class);

    public Map<String, Object> toMap(ResultSet rs) throws SQLException {
        Map<String, Object> result = new HashMap<>();
        ResultSetMetaData metaData = rs.getMetaData();
        int cols = metaData.getColumnCount();

        for(int i = 1; i <= cols; ++i) {
            String columnName = metaData.getColumnLabel(i).toLowerCase();

            if (null == columnName || 0 == columnName.length()) {
                columnName = metaData.getColumnName(i).toLowerCase();
            }

            if (result.containsKey(columnName)) {
                logger.info("Query result contains duplicate column name '" + columnName + "'. Alias columns to proceed.");
                throw new IllegalArgumentException("Duplicate column name");
            }

            result.put(columnName, rs.getObject(i));
        }

        return result;
    }
}
