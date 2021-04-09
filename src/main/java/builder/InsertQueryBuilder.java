package builder;

public class InsertQueryBuilder extends QueryBuilder {

    public InsertQueryBuilder() {
        this.builder = new StringBuilder("INSERT INTO ");
    }
}
