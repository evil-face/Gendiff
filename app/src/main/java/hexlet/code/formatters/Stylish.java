package hexlet.code.formatters;

import hexlet.code.Node;
import hexlet.code.TreeDiffer;

import java.io.IOException;
import java.util.List;
import java.util.StringJoiner;

public class Stylish {
    private static final String DELIMITER = System.lineSeparator();
    public static String format(List<Node> diffList) throws IOException {
        if (diffList.isEmpty()) {
            return "{\n}";
        }

        StringJoiner sj = new StringJoiner(DELIMITER, "{\n", "\n}");

        for (Node node : diffList) {
            switch (node.getStatus()) {
                case TreeDiffer.STATUS_UNCHANGED -> {
                    sj.add("    %s: %s".formatted(node.getKey(), node.getOldValue()));
                }
                case TreeDiffer.STATUS_REMOVED -> {
                    sj.add("  - %s: %s".formatted(node.getKey(), node.getOldValue()));
                }
                case TreeDiffer.STATUS_ADDED -> {
                    sj.add("  + %s: %s".formatted(node.getKey(), node.getNewValue()));
                }
                case TreeDiffer.STATUS_CHANGED -> {
                    sj.add("  - %s: %s".formatted(node.getKey(), node.getOldValue()));
                    sj.add("  + %s: %s".formatted(node.getKey(), node.getNewValue()));
                }
                default -> throw new IOException();
            }
        }

        return sj.toString();
    }
}
