package builder.query.create.table.foreignkey;

import query.CreateQuery;

public class OnAction extends ForeignKey {

    public OnAction(CreateQuery query) {
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
        query.append(" ON DELETE ");
        return new Action(query);
    }

    /**
     * Appends 'ON UPDATE' into 'FOREIGN KEY (column)
     * REFERENCES Table(column) ON UPDATE action' statement.
     *
     * @return Action class which can be used to assing
     * action on ON UPDATE statement
     */
    public Action onUpdate() {
        query.append(" ON UPDATE ");
        return new Action(query);
    }
}
