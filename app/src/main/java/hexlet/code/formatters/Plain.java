package hexlet.code.formatters;

import hexlet.code.Differ;
import hexlet.code.Node;
import java.util.List;
import java.util.StringJoiner;

public class Plain {
    private static final String DELIMITER = System.lineSeparator();
    public static final String COMPLEX_VALUE = "[complex value]";

    public static String generate(List<Node> diffList) {
        StringJoiner sj = new StringJoiner(DELIMITER);
        String key;
        String oldValue;
        String newValue;

        for (Node node : diffList) {
            key = node.getKey();
            switch (node.getStatus()) {
                case Differ.STATUS_CHANGED -> {
                    oldValue = formatValue(node.getOldValue());
                    newValue = formatValue(node.getNewValue());
                    sj.add("Property '%s' was updated. From %s to %s".formatted(key, oldValue, newValue));
                }
                case Differ.STATUS_REMOVED -> {
                    sj.add("Property '%s' was removed".formatted(key));
                }
                case Differ.STATUS_ADDED -> {
                    newValue = formatValue(node.getNewValue());
                    sj.add("Property '%s' was added with value: %s".formatted(key, newValue));
                }
                case Differ.STATUS_UNCHANGED -> { }
                default -> throw new RuntimeException("Error: no such format available");
            }
        }
        return sj.toString();
    }

    private static String formatValue(Object value) {
        String parsedValue;
        if (value == null) {
            parsedValue = "null";
        } else if (value instanceof String) {
            parsedValue = "'%s'".formatted(value);
        } else {
            parsedValue = value.toString().startsWith("{") || value.toString().startsWith("[")
                    ? COMPLEX_VALUE : value.toString();
        }
        return parsedValue;
    }
}
