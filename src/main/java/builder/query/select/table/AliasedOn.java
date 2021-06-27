package builder.query.select.table;

import builder.appender.StringAppender;
import query.Statement;

public class AliasedOn {

    protected Statement statement;

    public AliasedOn(Statement statement) {
        this.statement = statement;
    }

    /**
     * Validates user input and appends 'ON column = joinColumn' into
     * query string 'SELECT ... JOIN table ON column = joinColumn'.
     *
     * @param column First column name used for join
     *
     * @param joinColumn Second column name user for join
     *
     * @return JoinTable class which can be used to create
     * more joins or proceed to WHERE, GROUP BY, etc. clauses
     */
    public JoinTable on(String column, String joinColumn) {
        statement.append(" ON ");
        StringAppender.validateAndAppend(statement, column);
        statement.append(" = ");
        StringAppender.validateAndAppend(statement, joinColumn);
        return new JoinTable(statement);
    }
}
