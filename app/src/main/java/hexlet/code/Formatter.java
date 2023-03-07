package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.List;

public class Formatter {

    public static final String STYLISH = "stylish";
    public static final String PLAIN = "plain";
    public static final String JSON = "json";

    public static String format(List<Node> diffList, String format) throws IOException {
        return switch (format) {
            case STYLISH -> Stylish.generate(diffList);
            case PLAIN -> Plain.generate(diffList);
            case JSON -> Json.generate(diffList);
            default -> throw new RuntimeException("No such formatter available");
        };
    }
}
