package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws IOException {
        Map<String, String> map1 = Parser.parse(filepath1);
        Map<String, String> map2 = Parser.parse(filepath2);

        Set<String> allKeysSet = new TreeSet<>();
        allKeysSet.addAll(map1.keySet());
        allKeysSet.addAll(map2.keySet());

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (String key : allKeysSet) {
            String value1 = map1.getOrDefault(key, "no such key");
            String value2 = map2.getOrDefault(key, "no such key");
            if (Objects.equals(value1, value2)) {
                sb.append("    " + key + ": " + value1 + "\n");
            } else if ("no such key".equals(value2)) {
                sb.append("  - " + key + ": " + value1 + "\n");
            } else if ("no such key".equals(value1)) {
                sb.append("  + " + key + ": " + value2 + "\n");
            } else {
                sb.append("  - " + key + ": " + value1 + "\n");
                sb.append("  + " + key + ": " + value2 + "\n");
            }
        }
        sb.append("}");

        return sb.toString();
    }
}
