package builder.query;

import builder.statement.StatementFactory;
import builder.query.create.Create;
import builder.query.delete.DeleteTable;
import builder.query.drop.Drop;
import builder.query.insert.InsertTable;
import builder.query.select.column.FirstColumn;
import builder.query.update.UpdateTable;
import query.*;

import javax.sql.DataSource;

public class SQLQueryBuilder {
    private static DataSource dataSource;

    public SQLQueryBuilder(DataSource source) {
        dataSource = source;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * Creates new SELECT statement.
     *
     * @return FirstColumn class which starts SELECT
     * statement builder.
     */
    public FirstColumn select() {
        Statement statement = StatementFactory.createStatement("SELECT ");
        return new FirstColumn(statement);
    }

    /**
     * Creates new SELECT DISTINCT statement.
     *
     * @return FirstColumn class which starts SELECT
     * statement builder.
     */
    public FirstColumn selectDistinct() {
        Statement statement = StatementFactory.createStatement("SELECT DISTINCT ");
        return new FirstColumn(statement);
    }

    /**
     * Creates new SELECT TOP 'rows' statement.
     *
     * @param rows Number of rows to select
     *
     * @return FirstColumn class which starts SELECT
     * statement builder.
     */
    public FirstColumn selectTop(int rows) {
        Statement statement = StatementFactory.createStatement("SELECT TOP " + rows + " ");
        return new FirstColumn(statement);
    }

    /**
     * Creates new INSERT statement.
     *
     * @return InsertTable class which starts INSERT
     * statement builder.
     */
    public InsertTable insert() {
        Statement statement = StatementFactory.createStatement("INSERT INTO ");
        return new InsertTable(statement);
    }

    /**
     * Creates new DELETE statement.
     *
     * @return DeleteTable class which starts DELETE
     * statement builder.
     */
    public DeleteTable delete() {
        Statement statement = StatementFactory.createStatement("DELETE FROM ");
        return new DeleteTable(statement);
    }

    /**
     * Creates new UPDATE statement.
     *
     * @return UpdateTable class which starts UPDATE
     * statement builder.
     */
    public UpdateTable update() {
        Statement statement = StatementFactory.createStatement("UPDATE ");
        return new UpdateTable(statement);
    }

    /**
     * Creates new CREATE statement.
     *
     * @return Create class which starts CREATE
     * statement builder.
     */
    public Create create() {
        Statement statement = StatementFactory.createStatement("CREATE ");
        return new Create(statement);
    }

    /**
     * Creates new DROP statement.
     *
     * @return Drop class which starts DROP
     * statement builder.
     */
    public Drop drop() {
        Statement statement = StatementFactory.createStatement("DROP ");
        return new Drop(statement);
    }
}
