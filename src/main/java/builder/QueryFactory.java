package builder;

public class QueryFactory {

    public static SelectQueryBuilder select() {
        return new SelectQueryBuilder(new StringBuilder("SELECT "));
    }
}
