package testutils;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getH2Connection() throws SQLException {
        DataSource dataSource = getH2DataSource();
        return dataSource.getConnection();
    }

    public static DataSource getH2DataSource() {
        JdbcDataSource datasource = new JdbcDataSource();
        datasource.setURL("jdbc:h2:mem:test;MODE=MYSQL;DATABASE_TO_LOWER=TRUE;DB_CLOSE_DELAY=-1;");
        datasource.setUser("sa");
        datasource.setPassword("");
        return datasource;
    }

    public static DataSource getMySQLDataSource() {
        MysqlDataSource datasource = new MysqlDataSource();
        datasource.setURL("jdbc:mysql://localhost:3306/test_db");
        datasource.setUser("root");
        datasource.setPassword("sa");
        return datasource;
    }

    public static DataSource getPostgreSQLDataSource() {
        PGSimpleDataSource datasource = new PGSimpleDataSource();
        datasource.setURL("jdbc:postgresql://localhost:5432/postgres");
        datasource.setUser("postgres");
        datasource.setPassword("sa");
        return datasource;
    }

}
