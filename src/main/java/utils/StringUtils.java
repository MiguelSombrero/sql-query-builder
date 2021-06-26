package utils;

import database.column.ColumnValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StringUtils {
    protected static Logger logger = LoggerFactory.getLogger(StringUtils.class);
    private static final String PARAM_PLACEHOLDER = "?";

    public static String replaceQuestionMarksWithParams(String text, List<ColumnValue> params) {
        StringBuilder replacedText = new StringBuilder(text);

        for (ColumnValue columnValue : params) {
            int position = replacedText.indexOf(PARAM_PLACEHOLDER);
            replacedText.replace(position, position + 1, columnValue.toString());
        }

        return replacedText.toString();
    }

    public static String repeatTextTimes(String text, int times) {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < times; i++) {
            string.append(text);
        }

        return string.toString();
    }
}
