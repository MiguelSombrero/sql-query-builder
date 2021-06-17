package utils;

import database.column.ColumnValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StringUtils {
    protected static Logger logger = LoggerFactory.getLogger(StringUtils.class);
    private static final char QUESTION_MARK = 63;

    public static String replaceQuestionMarksWithParams(String text, List<ColumnValue> params) {
        StringBuilder replacedText = new StringBuilder();
        int count = 0;

        for (int i = 0; i < text.length(); i++) {
            Character character = text.charAt(i);

            if (character == QUESTION_MARK) {
                ColumnValue value;

                try {
                    value = params.get(count);
                } catch (IndexOutOfBoundsException e) {
                    logger.info("Mismatch in query string and param count");
                    logger.debug(e.getLocalizedMessage());
                    throw e;
                }

                String param = value.toString();
                replacedText.append(param);
                count++;
            } else {
                replacedText.append(text.substring(i, i + 1));
            }
        }

        return replacedText.toString();
    }
}
