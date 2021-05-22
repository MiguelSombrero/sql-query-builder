package builder.statement.select.table;

import builder.query.Query;
import builder.statement.select.SelectQueryBuilder;
import factory.ValidatorFactory;
import validation.Validator;

public class From {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    private Query query;

    public From(Query query) {
        this.query = query;
    }

    /**
     * Appends 'table' into 'FROM table' statement.
     *
     * @param table Table name to be queried from
     *
     * @return Table class which can be used to append
     * tables in FROM statement, alias tables and proceed
     * to WHERE, JOIN, GROUP BY etc. statements
     */
    public Table table(String table) {
        validator.validate(table);
        query.append(table);
        return new Table(query);
    }

    /**
     * Appends sub-select in 'SELECT ... FROM (SELECT ...)' statement.
     * Sub-select is created with factory class QueryFactory
     *
     * @param subQuery Sub-select to be inserted in FROM statement
     *
     * @return SubQuery class which can be used to
     * alias sub-query or proceed to WHERE and JOIN statements
     */
    public SubQuery sub(SelectQueryBuilder subQuery) {
        query.append("(");
        query.append(subQuery.build());
        query.append(")");
        return new SubQuery(query);
    }
}
