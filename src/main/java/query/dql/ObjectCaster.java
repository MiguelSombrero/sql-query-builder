package query.dql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ObjectCaster {
    protected static Logger logger = LoggerFactory.getLogger(ObjectCaster.class);

    private static final ObjectToTypeCaster<String> stringCaster = new ObjectToTypeCaster<>();
    private static final ObjectToTypeCaster<Integer> integerCaster = new ObjectToTypeCaster<>();
    private static final ObjectToTypeCaster<Double> doubleCaster = new ObjectToTypeCaster<>();
    private static final ObjectToTypeCaster<Boolean> booleanCaster = new ObjectToTypeCaster<>();
    private static final ObjectToTypeCaster<LocalDate> dateCaster = new ObjectToTypeCaster<>();
    private static final ObjectToTypeCaster<LocalDateTime> dateTimeCaster = new ObjectToTypeCaster<>();
    private static final ObjectToTypeCaster<Timestamp> timestampCaster = new ObjectToTypeCaster<>();

    public static String castToString(Object object) {
        return stringCaster.cast(object);
    }

    public static Integer castToInteger(Object object) {
        return integerCaster.cast(object);
    }

    public static Double castToDouble(Object object) {
        return doubleCaster.cast(object);
    }

    public static Boolean castToBoolean(Object object) {
        return booleanCaster.cast(object);
    }

    public static LocalDate castToLocalDate(Object object) {
        return dateCaster.cast(object);
    }

    public static LocalDateTime castToLocalDateTime(Object object) {
        return dateTimeCaster.cast(object);
    }

    public static Timestamp castToTimestamp(Object object) {
        return timestampCaster.cast(object);
    }
}
