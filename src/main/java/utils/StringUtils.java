package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StringUtils {
    private static final char QUESTION_MARK = 63;

    public static String replaceQuestionMarksWithParams(String text, List<Object> params) {
        StringBuilder replacedText = new StringBuilder();
        int count = 0;

        for (int i = 0; i < text.length(); i++) {
            Character character = text.charAt(i);

            if (character == QUESTION_MARK) {
                String param = (String) params.get(count);
                replacedText.append("'" + param + "'");
                count++;
            } else {
                replacedText.append(text.substring(i, i + 1));
            }
        }

        return replacedText.toString();
    }

}
