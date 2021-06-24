package builder.query.create.table.foreignkey;

import query.Clause;

public class OnAction extends ForeignKey {

    public OnAction(Clause query) {
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
        clause.append(" ON DELETE ");
        return new Action(clause);
    }

    /**
     * Appends 'ON UPDATE' into 'FOREIGN KEY (column)
     * REFERENCES Table(column) ON UPDATE action' statement.
     *
     * @return Action class which can be used to assing
     * action on ON UPDATE statement
     */
    public Action onUpdate() {
        clause.append(" ON UPDATE ");
        return new Action(clause);
    }
}
