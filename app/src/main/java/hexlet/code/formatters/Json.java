package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Node;
import java.io.IOException;
import java.util.List;

public class Json {
    public static String format(List<Node> diffList) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(diffList);
    }
}
