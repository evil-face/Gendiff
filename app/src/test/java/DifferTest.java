import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {

    @Test
    void testCorrectCaseJSON() throws IOException {
        Path path = Paths.get("src/test/resources/expectedJSON1");
        String expected = Files.readString(path);

        assertThat(Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json"))
                .isEqualTo(expected);
    }

    @Test
    void testCorrectCaseYAML() throws IOException {
        Path path = Paths.get("src/test/resources/expectedJSON1");
        String expected = Files.readString(path);

        assertThat(Differ.generate("src/test/resources/file1.yml", "src/test/resources/file2.yml"))
                .isEqualTo(expected);
    }

    @Test
    void testNoSuchFilesException() {
        var thrown1 = catchThrowable(() -> Differ.generate("nofile.json", "src/test/resources/file2.json"));
        var thrown2 = catchThrowable(() -> Differ.generate("src/test/resources/file1.json", "nofile.json"));
        var thrown3 = catchThrowable(() -> Differ.generate("nofile.json", "nofile.json"));
        assertThat(thrown1).isInstanceOf(IOException.class);
        assertThat(thrown2).isInstanceOf(IOException.class);
        assertThat(thrown3).isInstanceOf(IOException.class);
    }

    @Test
    void testEmptyFileJSON() throws IOException {
        String expected = "{\n}";
        assertThat(Differ.generate("src/test/resources/emptyFile.json", "src/test/resources/emptyFile.json"))
                .isEqualTo(expected);
    }

    @Test
    void testEmptyFileYAML() throws IOException {
        String expected = "{\n}";
        assertThat(Differ.generate("src/test/resources/emptyFile.yml", "src/test/resources/emptyFile.yml"))
                .isEqualTo(expected);
    }

    @Test
    void testCorruptedFileJSON() {
        var thrown = catchThrowable(() -> Differ.generate("src/test/resources/file1.json",
                "src/test/resources/corruptedFile.json"));
        assertThat(thrown).isInstanceOf(IOException.class);
    }

    @Test
    void testCorruptedFileYAML() {
        var thrown = catchThrowable(() -> Differ.generate("src/test/resources/file1.yml",
                "src/test/resources/corruptedFile.yml"));
        assertThat(thrown).isInstanceOf(IOException.class);
    }
}
