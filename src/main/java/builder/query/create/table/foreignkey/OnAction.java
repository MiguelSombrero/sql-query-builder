package builder.query.create.table.foreignkey;

import query.Statement;

public class OnAction extends ForeignKey {

    public OnAction(Statement query) {
        super(query);
    }

    /**
     * Appends 'ON DELETE' into 'FOREIGN KEY (column)
     * REFERENCES Table(column) ON DELETE action' statement.
     *
     * @return Action class which can be used to assing
     * action on ON DELETE statement
     */
    public Action onDelete() {
        statement.append(" ON DELETE ");
        return new Action(statement);
    }

    /**
     * Appends 'ON UPDATE' into 'FOREIGN KEY (column)
     * REFERENCES Table(column) ON UPDATE action' statement.
     *
     * @return Action class which can be used to assing
     * action on ON UPDATE statement
     */
    public Action onUpdate() {
        statement.append(" ON UPDATE ");
        return new Action(statement);
    }
}
