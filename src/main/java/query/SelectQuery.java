package query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectQuery implements Query {
    private static Logger logger = LoggerFactory.getLogger(SelectQuery.class);

    private StringBuilder queryString;
    private Connection connection;

    public SelectQuery(StringBuilder queryString, Connection connection) {
        this.queryString = queryString;
        this.connection = connection;
    }

    public void append(String value) {
        this.queryString = this.queryString.append(value);
    }

    public void append(int value) {
        this.queryString = this.queryString.append(value);
    }

    public void append(double value) {
        this.queryString = this.queryString.append(value);
    }

    public void insert(int index, String value) {
        this.queryString = this.queryString.insert(index, value);
    }

    public String build() {
        return queryString.toString();
    }

    public ResultSet execute() throws SQLException {
        PreparedStatement statement = prepare();
        ResultSet result = null;

        try {
            result = statement.executeQuery();
        } catch (SQLException e) {
            logger.info("Executing of SELECT query failed");
            logger.debug(e.getLocalizedMessage());
            throw e;
        } finally {
            statement.close();
        }
        return result;
    }

    private PreparedStatement prepare() throws SQLException {
        PreparedStatement statement = null;

        try {
            String query = build();
            statement = connection.prepareStatement(query);
        } catch (SQLException e) {
            logger.info("Creating of PreparedStatement failed");
            logger.debug(e.getLocalizedMessage());
            throw e;
        }
        return statement;
    }
}
