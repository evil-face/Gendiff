import static org.assertj.core.api.Assertions.*;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DifferTest {

    @Test
    void testCorrectCase() throws IOException {
        String expected =
                """
                   {
                     - follow: false
                       host: hexlet.io
                     - proxy: 123.234.53.22
                     - timeout: 50
                     + timeout: 20
                     + verbose: true
                   }""";
        assertThat(Differ.generate("file1.json", "file2.json")).isEqualTo(expected);
    }

    @Test
    void testNoSuchFilesException() {
        var thrown1 = catchThrowable(() -> Differ.generate("nofile.json", "file2.json"));
        var thrown2 = catchThrowable(() -> Differ.generate("file1.json", "nofile.json"));
        var thrown3 = catchThrowable(() -> Differ.generate("nofile.json", "nofile.json"));
        assertThat(thrown1).isInstanceOf(IOException.class);
        assertThat(thrown2).isInstanceOf(IOException.class);
        assertThat(thrown3).isInstanceOf(IOException.class);
    }

    @Test
    void testEmptyFile() throws IOException {
        String expected = "{\n}";
        assertThat(Differ.generate("emptyFile.json", "emptyFile.json")).isEqualTo(expected);
    }

    @Test
    void testCorruptedFile() {
        var thrown = catchThrowable(() -> Differ.generate("file1.json", "corruptedFile.json"));
        assertThat(thrown).isInstanceOf(IOException.class);
    }

    @Test
    void testNullFile() throws IOException {
        String expected = """
                {
                  - host: hexlet.io
                  + host: null
                  - timeout: 20
                  + timeout: 30
                  - verbose: true
                  + verbose: false
                }""";
        assertThat(Differ.generate("file2.json", "nullFile.json")).isEqualTo(expected);
    }
}
