package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Node;
import java.io.IOException;
import java.util.List;
import java.util.StringJoiner;

public class Json {
    public static String generate(List<Node> diffList) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringJoiner sj = new StringJoiner(",", "[", "]");

        for (Node node : diffList) {
            sj.add(mapper.writeValueAsString(node));
        }

        return sj.toString();
    }
}
