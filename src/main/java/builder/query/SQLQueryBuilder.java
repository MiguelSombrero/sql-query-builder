package builder.query;

import builder.clause.ClauseFactory;
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
        Clause clause = ClauseFactory.createClause("SELECT ");
        return new FirstColumn(clause);
    }

    /**
     * Creates new SELECT DISTINCT statement.
     *
     * @return FirstColumn class which starts SELECT
     * statement builder.
     */
    public FirstColumn selectDistinct() {
        Clause clause = ClauseFactory.createClause("SELECT DISTINCT ");
        return new FirstColumn(clause);
    }

    /**
     * Creates new SELECT TOP 'rows' statement.
     *
     * @return FirstColumn class which starts SELECT
     * statement builder.
     */
    public FirstColumn selectTop(int rows) {
        Clause clause = ClauseFactory.createClause("SELECT TOP " + rows + " ");
        return new FirstColumn(clause);
    }

    /**
     * Creates new INSERT statement.
     *
     * @return InsertTable class which starts INSERT
     * statement builder.
     */
    public InsertTable insert() {
        Clause clause = ClauseFactory.createClause("INSERT INTO ");
        return new InsertTable(clause);
    }

    /**
     * Creates new DELETE statement.
     *
     * @return DeleteTable class which starts DELETE
     * statement builder.
     */
    public DeleteTable delete() {
        Clause clause = ClauseFactory.createClause("DELETE FROM ");
        return new DeleteTable(clause);
    }

    /**
     * Creates new UPDATE statement.
     *
     * @return UpdateTable class which starts UPDATE
     * statement builder.
     */
    public UpdateTable update() {
        Clause clause = ClauseFactory.createClause("UPDATE ");
        return new UpdateTable(clause);
    }

    /**
     * Creates new CREATE statement.
     *
     * @return Create class which starts CREATE
     * statement builder.
     */
    public Create create() {
        Clause clause = ClauseFactory.createClause("CREATE ");
        return new Create(clause);
    }

    /**
     * Creates new DROP statement.
     *
     * @return Drop class which starts DROP
     * statement builder.
     */
    public Drop drop() {
        Clause clause = ClauseFactory.createClause("DROP ");
        return new Drop(clause);
    }
}
