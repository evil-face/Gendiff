package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static final String JSON_EXT = "json";
    public static final String YAML_EXT_1 = "yml";
    public static final String YAML_EXT_2 = "yaml";

    public static Map<String, Object> parse(String content, String ext) throws IOException {
        Map<String, Object> parsedMap = createMapper(ext).readValue(content, new TypeReference<>() { });

        return parsedMap == null ? new HashMap<>() : parsedMap;
    }

    private static ObjectMapper createMapper(String ext) {
        return switch (ext.toLowerCase()) {
            case JSON_EXT -> new ObjectMapper();
            case YAML_EXT_1, YAML_EXT_2 -> new YAMLMapper();
            default -> throw new RuntimeException("Not supported extension");
        };
    }
}
