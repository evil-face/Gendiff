package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static final String JSON_EXT = "json";
    public static final String YAML_EXT_1 = "yml";
    public static final String YAML_EXT_2 = "yaml";

    public static Map<String, Object> parse(String content, String ext) throws IOException {
        switch (ext.toLowerCase()) {
            case JSON_EXT -> {
                return parseJson(content) == null ? new HashMap<>() : parseJson(content);
            }
            case YAML_EXT_1, YAML_EXT_2 -> {
                return parseYaml(content) == null ? new HashMap<>() : parseYaml(content);
            }
            default -> throw new RuntimeException("Not supported extension");
        }

        // return parsedMap == null ? new HashMap<>() : parsedMap;
    }

    private static Map<String, Object> parseYaml(String content) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(content, new TypeReference<>() { });
    }

    private static Map<String, Object> parseJson(String content) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, new TypeReference<>() { });
    }

}
