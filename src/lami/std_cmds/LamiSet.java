package lami.std_cmds;

import lami.LamiCommand;

import java.util.regex.Pattern;

public class LamiSet implements LamiCommand {
    @Override
    public void call(Argument argument) {
        String argsLine = argument.getArgsLine();
        if (!argsLine.contains("=")) return;
        String[] arr = argsLine.split(Pattern.quote("="), 2);
        String varName = arr[0].trim();
        String line = arr[1].trim();

        Object o = argument.lamiExecutor.eval(line);
        argument.memory().put(varName, o);
    }
}
