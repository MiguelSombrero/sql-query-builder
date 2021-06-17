package query.ddl;

import javax.sql.DataSource;

public class CreateQuery extends DDLBaseQuery {
    public CreateQuery(StringBuilder queryString, DataSource dataSource) {
        super(queryString, dataSource);
    }
}
