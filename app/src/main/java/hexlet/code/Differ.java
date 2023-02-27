package hexlet.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;


public class Differ {

    public static String generate(String filepath1, String filepath2) throws IOException {
        Map<String, Object> map1 = Parser.parse(filepath1);
        Map<String, Object> map2 = Parser.parse(filepath2);
        List<Node> diffList = buildDiffList(map1, map2);

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (Node node : diffList) {
            switch (node.getStatus()) {
                case "unchanged" -> sb.append("    " + node.getKey() + ": " + node.getOldValue() + "\n");
                case "removed" -> sb.append("  - " + node.getKey() + ": " + node.getOldValue() + "\n");
                case "added" -> sb.append("  + " + node.getKey() + ": " + node.getNewValue() + "\n");
                case "changed" -> {
                    sb.append("  - " + node.getKey() + ": " + node.getOldValue() + "\n");
                    sb.append("  + " + node.getKey() + ": " + node.getNewValue() + "\n");
                }
                default -> throw new IOException();
            }
        }
        sb.append("}");

        return sb.toString();
    }

    private static List<Node> buildDiffList(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> allKeysSet = new TreeSet<>();
        allKeysSet.addAll(map1.keySet());
        allKeysSet.addAll(map2.keySet());
        List<Node> diffList = new ArrayList<>();

        for (String key : allKeysSet) {
            String status;
            Object oldValue = map1.get(key);
            Object newValue = map2.get(key);
            if (!map2.containsKey(key)) {
                status = "removed";
            } else if (!map1.containsKey(key)) {
                status = "added";
            } else if (Objects.equals(oldValue, newValue)) {
                status = "unchanged";
            } else {
                status = "changed";
            }
            diffList.add(new Node(key, oldValue, newValue, status));
        }
        return diffList;
    }
}
