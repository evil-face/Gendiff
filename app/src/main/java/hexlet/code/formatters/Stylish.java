package hexlet.code.formatters;

import hexlet.code.Node;

import java.io.IOException;
import java.util.List;

public class Stylish {
    public static String generate(List<Node> diffList) throws IOException {
        StringBuilder sb = new StringBuilder();

        sb.append("{\n");
        for (Node node : diffList) {
            switch (node.getStatus()) {
                case "unchanged" -> {
                    sb.append("    ");
                    sb.append(node.getKey());
                    sb.append(": ");
                    sb.append(node.getOldValue());
                    sb.append("\n");
                }
                case "removed" -> {
                    sb.append("  - ");
                    sb.append(node.getKey());
                    sb.append(": ");
                    sb.append(node.getOldValue());
                    sb.append("\n");
                }
                case "added" -> {
                    sb.append("  + ");
                    sb.append(node.getKey());
                    sb.append(": ");
                    sb.append(node.getNewValue());
                    sb.append("\n");
                }
                case "changed" -> {
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
