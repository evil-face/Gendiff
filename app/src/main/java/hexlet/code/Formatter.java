package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.List;

public class Formatter {
    public static String format(List<Node> diffList, String format) throws IOException {
        return switch (format) {
            case "stylish" -> Stylish.generate(diffList);
            case "plain" -> Plain.generate(diffList);
            default -> "";
        };
    }
}
