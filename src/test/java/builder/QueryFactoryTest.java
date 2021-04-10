package builder;

import factory.QueryFactory;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class QueryFactoryTest {

    @Test
    public void testReturnSelect() {
        Select queryBuilder = QueryFactory.select();
        assertThat(queryBuilder, instanceOf(Select.class));
    }

}
