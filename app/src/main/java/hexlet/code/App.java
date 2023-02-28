package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Option(names = { "-f", "--format" }, paramLabel = "format", defaultValue = "stylish",
            description = "output format [default: stylish]")
    String format;

    @Parameters(index = "0", description = "path to first file", paramLabel = "filepath1")
    String filepath1;

    @Parameters(index = "1", description = "path to second file", paramLabel = "filepath2")
    String filepath2;

    @Override
    public Integer call() {
        try {
            System.out.println(Differ.generate(filepath1, filepath2, format.toLowerCase()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Try again");
        }
        return 0;
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
