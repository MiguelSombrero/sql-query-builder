package builder.statement.create.table.foreignkey;

import query.SQLQuery;

public class OnAction extends ForeignKey {

    public OnAction(SQLQuery SQLQuery) {
        super(SQLQuery);
    }

    /**
     * Appends 'ON DELETE' into 'FOREIGN KEY (column)
     * REFERENCES Table(column) ON DELETE action' statement.
     *
     * @return Action class which can be used to assing
     * action on ON DELETE statement
     */
    public Action onDelete() {
        SQLQuery.append(" ON DELETE ");
        return new Action(SQLQuery);
    }

    /**
     * Appends 'ON UPDATE' into 'FOREIGN KEY (column)
     * REFERENCES Table(column) ON UPDATE action' statement.
     *
     * @return Action class which can be used to assing
     * action on ON UPDATE statement
     */
    public Action onUpdate() {
        SQLQuery.append(" ON UPDATE ");
        return new Action(SQLQuery);
    }
}
