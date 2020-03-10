package lami.std_cmds;

import lami.LamiCommand;

public class LamiText implements LamiCommand {
    @Override
    public void call(Argument argument) {
        int len = argument.argsLen();
        if (len > 0) {
            argument.setReturn(argument.parseArg(0));
        } else {
            argument.setReturn(null);
        }
    }
}
