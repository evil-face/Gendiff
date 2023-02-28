package hexlet.code.formatters;

import hexlet.code.Node;
import java.util.List;

public class Plain {
    public static String generate(List<Node> diffList) {
        StringBuilder sb = new StringBuilder();

        for (Node node : diffList) {
            switch (node.getStatus()) {
                case "changed" -> {
                    sb.append("Property '");
                    sb.append(node.getKey());
                    sb.append("' was updated. From ");
                    sb.append(parseValue(node.getOldValue()));
                    sb.append(" to ");
                    sb.append(parseValue(node.getNewValue()));
                    sb.append("\n");
                }
                case "removed" -> {
                    sb.append("Property '");
                    sb.append(node.getKey());
                    sb.append("' was removed\n");
                }
                case "added" -> {
                    sb.append("Property '");
                    sb.append(node.getKey());
                    sb.append("' was added with value: ");
                    sb.append(parseValue(node.getNewValue()));
                    sb.append("\n");
                }
                default -> sb.append("");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private static String parseValue(Object value) {
        String parsedValue;
        if (value == null) {
            parsedValue = "null";
        } else if (value instanceof String) {
            parsedValue = "'" + value + "'";
        } else {
            parsedValue = value.toString().startsWith("{") || value.toString().startsWith("[")
                    ? "[complex value]" : value.toString();
        }
        return parsedValue;
    }
}
