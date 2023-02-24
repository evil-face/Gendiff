import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.App;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

public class AppTest {
    @Test
    void testCorrectCase() {
        String[] args = {"src/resources/file1.json", "src/resources/file2.json"};
        int expected = 0;
        int actual = new CommandLine(new App()).execute(args);
        assertThat(actual).isEqualTo(expected);
    }
}
