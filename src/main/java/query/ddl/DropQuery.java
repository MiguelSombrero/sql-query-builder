package query.ddl;

import javax.sql.DataSource;

public class DropQuery extends DDLBaseQuery {
    public DropQuery(StringBuilder queryString, DataSource dataSource) {
        super(queryString, dataSource);
    }
}
