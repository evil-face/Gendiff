package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Node;
import java.io.IOException;
import java.util.List;
public class Json {
    public static String generate(List<Node> diffList) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        for (Node node : diffList) {
            sb.append(mapper.writeValueAsString(node));
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");

        return sb.toString();
    }
}
