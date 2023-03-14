package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        Map<String, Object> map1 = Parser.parse(readData(filepath1), readExt(filepath1));
        Map<String, Object> map2 = Parser.parse(readData(filepath2), readExt(filepath2));
        List<Node> diffList = TreeDiffer.buildDiffList(map1, map2);

        return Formatter.format(diffList, format);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, Formatter.STYLISH);
    }

    private static String readData(String filepath) throws IOException {
        Path path = Paths.get(filepath).normalize().toAbsolutePath();

        if (!Files.exists(path)) {
            throw new NoSuchFileException("File " + path + " does not exist");
        }

        return Files.readString(path);
    }

    private static String readExt(String filepath) throws IOException {
        String ext = filepath.substring(filepath.lastIndexOf(".") + 1);
        if (ext.equals("-1")) {
            throw new IOException("No file extension");
        }

        return ext;
    }
}
