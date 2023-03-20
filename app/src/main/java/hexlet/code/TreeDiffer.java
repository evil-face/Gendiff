package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class TreeDiffer {
    public static final String STATUS_ADDED = "added";
    public static final String STATUS_REMOVED = "removed";
    public static final String STATUS_UNCHANGED = "unchanged";
    public static final String STATUS_CHANGED = "changed";

    static List<Node> findDiffMap(Map<String, Object> map1, Map<String, Object> map2) {
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
