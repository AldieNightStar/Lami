package lami;

import lami.std_cmds.LamiPrint;
import lami.std_cmds.LamiSet;
import lami.std_cmds.LamiText;

import java.io.File;
import java.nio.file.Files;

public class Lami {
    private LamiExecutor executor;
    private LamiLogger logger = System.out::println;

    public Lami() {
        executor = new LamiExecutor();
        executor.registerCommand("print", new LamiPrint());
        executor.registerCommand("text", new LamiText());
        executor.registerCommand("set", new LamiSet());
    }

    public Object execute(String s) {
        return executor.eval(s);
    }

    public Object execute(File file) {
        try {
            String code = new String(Files.readAllBytes(file.toPath()));
            return execute(code);
        } catch (Exception e) {
            logger.log("Error while reading file: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void setLogger(LamiLogger logger) {
        this.logger = logger;
        executor.setLogger(logger);
    }

    public void registerCommand(String commandName, LamiCommand command) {
        executor.registerCommand(commandName, command);
    }
}
