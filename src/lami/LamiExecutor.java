package lami;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class LamiExecutor {
    private Map<String, LamiCommand> commands;
    private LamiInterpreter interpreter;
    private LamiLogger logger = System.out::println;

    public LamiExecutor() {
        commands = new HashMap<>();
        interpreter = new LamiInterpreter();
    }

    public void registerCommand(String commandName, LamiCommand command) {
        this.commands.put(commandName, command);
    }

    public void setLogger(LamiLogger logger) {
        this.logger = logger;
    }

    public Object eval(String s) {
        AtomicReference<Object> toReturn = new AtomicReference<>();
        AtomicBoolean toReturnActivated = new AtomicBoolean(false);
        Consumer<Object> toReturnSetter = r -> {
            toReturnActivated.set(true);
            toReturn.set(r);
        };

        s += "\n";
        String[] lines = s.split(Pattern.quote("\n"));

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.trim().isEmpty() || line.startsWith("#")) {
                continue;
            }
            LamiParser.Token token = LamiParser.parseLine(line);
            LamiCommand cmd = commands.get(token.command);
            if (cmd == null) {
                logger.log("LINE: " + i + " >> Command not found: " + token.command);
                break;
            }

            LamiCommand.SubArgument sub = new LamiCommand.SubArgument();
            sub.interpreter = interpreter;
            sub.token = token;
            sub.onReturn = toReturnSetter;

            LamiCommand.Argument cmdCallArg = new LamiCommand.Argument(sub);
            cmdCallArg.lamiExecutor = this;

            cmd.call(cmdCallArg);

            if (toReturnActivated.get()) return toReturn.get();
        }
        return null;
    }
}
