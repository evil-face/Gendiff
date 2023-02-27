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
    public static Map<String, String> parse(String filepath) throws IOException {
        Path path = Paths.get(filepath).normalize().toAbsolutePath();
        if (!Files.exists(path)) {
            throw new NoSuchFileException("File " + path + " does not exist");
        }

        String ext = filepath.substring(filepath.lastIndexOf(".") + 1);
        if (ext.equals("-1")) {
            throw new IOException("Not supported extension");
        }

        Map<String, String> parsedMap = createMapper(ext)
                .readValue(new File(path.toString()), new TypeReference<>() { });
        if (parsedMap == null) {
            return new HashMap<>();
        }
        return parsedMap;
    }

    private static ObjectMapper createMapper(String ext) {
        ObjectMapper mapper = null;
        if (ext.equals("json")) {
            mapper = new ObjectMapper();
        } else if (ext.equals("yml")) {
            mapper = new YAMLMapper();
        }
        return mapper;
    }
}
