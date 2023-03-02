package hexlet.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;


public class Differ {
    public static final String STATUS_ADDED = "added";
    public static final String STATUS_REMOVED = "removed";
    public static final String STATUS_UNCHANGED = "unchanged";
    public static final String STATUS_CHANGED = "changed";

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        Map<String, Object> map1 = Parser.parse(filepath1);
        Map<String, Object> map2 = Parser.parse(filepath2);
        List<Node> diffList = buildDiffList(map1, map2);

        return Formatter.format(diffList, format);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, Formatter.STYLISH);
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
                status = STATUS_REMOVED;
            } else if (!map1.containsKey(key)) {
                status = STATUS_ADDED;
            } else if (Objects.equals(oldValue, newValue)) {
                status = STATUS_UNCHANGED;
            } else {
                status = STATUS_CHANGED;
            }
            diffList.add(new Node(key, oldValue, newValue, status));
        }
        return diffList;
    }
}
