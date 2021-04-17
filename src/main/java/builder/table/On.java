package builder.table;

public class On {
    private JoinTable parent;

    public On(JoinTable parent) {
        this.parent = parent;
    }

    public JoinTable on(String condition) {
        parent.append(" ON ");
        parent.append(condition);
        return parent;
    }
}
