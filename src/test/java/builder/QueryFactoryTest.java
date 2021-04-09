package builder;

import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class QueryFactoryTest {

    @Test
    public void testReturnSelect() {
        SelectQueryBuilder queryBuilder = QueryFactory.select();
        assertThat(queryBuilder, instanceOf(SelectQueryBuilder.class));
    }

}
