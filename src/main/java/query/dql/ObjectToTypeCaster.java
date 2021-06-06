package query.dql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectToTypeCaster<T> {
    protected static Logger logger = LoggerFactory.getLogger(ObjectToTypeCaster.class);

    public T cast(Object object) {
        T value;

        try {
            value = (T) object;
        } catch (ClassCastException e) {
            logger.info("Casting of object failed");
            logger.debug(e.getLocalizedMessage());
            throw e;
        }

        return value;
    }
}
