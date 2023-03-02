package hexlet.code.formatters;

import hexlet.code.Node;
import hexlet.code.Differ;

import java.io.IOException;
import java.util.List;

public class Stylish {
    public static String generate(List<Node> diffList) throws IOException {
        StringBuilder sb = new StringBuilder();

        sb.append("{\n");
        for (Node node : diffList) {
            switch (node.getStatus()) {
                case Differ.STATUS_UNCHANGED -> {
                    sb.append("    ");
                    sb.append(node.getKey());
                    sb.append(": ");
                    sb.append(node.getOldValue());
                    sb.append("\n");
                }
                case Differ.STATUS_REMOVED -> {
                    sb.append("  - ");
                    sb.append(node.getKey());
                    sb.append(": ");
                    sb.append(node.getOldValue());
                    sb.append("\n");
                }
                case Differ.STATUS_ADDED -> {
                    sb.append("  + ");
                    sb.append(node.getKey());
                    sb.append(": ");
                    sb.append(node.getNewValue());
                    sb.append("\n");
                }
                case Differ.STATUS_CHANGED -> {
                    sb.append("  - ");
                    sb.append(node.getKey());
                    sb.append(": ");
                    sb.append(node.getOldValue());
                    sb.append("\n");
                    sb.append("  + ");
                    sb.append(node.getKey());
                    sb.append(": ");
                    sb.append(node.getNewValue());
                    sb.append("\n");
                }
                default -> throw new IOException();
            }
        }
        sb.append("}");

        return sb.toString();
    }
}
