package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws IOException {

        Path path1 = Paths.get(filepath1).normalize().toAbsolutePath();
        Path path2 = Paths.get(filepath2).normalize().toAbsolutePath();

        if (!Files.exists(path1)) {
            throw new NoSuchFileException("File " + path1 + " does not exist");
        }

        if (!Files.exists(path2)) {
            throw new NoSuchFileException("File " + path2 + " does not exist");
        }
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map1 = mapper.readValue(new File(path1.toString()), new TypeReference<>() {});
        Map<String, String> map2 = mapper.readValue(new File(path2.toString()), new TypeReference<>() {});

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
