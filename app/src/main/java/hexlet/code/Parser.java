package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static final String JSON_EXT = "json";
    public static final String YAML_EXT_1 = "yml";
    public static final String YAML_EXT_2 = "yaml";

    public static Map<String, Object> parse(String filepath) throws IOException {
        Path path = Paths.get(filepath).normalize().toAbsolutePath();
        if (!Files.exists(path)) {
            throw new NoSuchFileException("File " + path + " does not exist");
        }

        String ext = filepath.substring(filepath.lastIndexOf(".") + 1);
        if (ext.equals("-1")) {
            throw new IOException("No file extension");
        }

        Map<String, Object> parsedMap = createMapper(ext)
                .readValue(new File(path.toString()), new TypeReference<>() { });
        if (parsedMap == null) {
            return new HashMap<>();
        }
        return parsedMap;
    }

    private static ObjectMapper createMapper(String ext) {
        ObjectMapper mapper;
        if (ext.equalsIgnoreCase(JSON_EXT)) {
            mapper = new ObjectMapper();
        } else if (ext.equalsIgnoreCase(YAML_EXT_1) || ext.equalsIgnoreCase(YAML_EXT_2)) {
            mapper = new YAMLMapper();
        } else {
            throw new RuntimeException("Not supported extension");
        }
        return mapper;
    }
}
