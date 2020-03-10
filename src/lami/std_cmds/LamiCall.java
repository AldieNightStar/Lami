package lami.std_cmds;

import lami.LamiCommand;

public class LamiCall implements LamiCommand {
    @Override
    public void call(Argument argument) {
        if (argument.argsLen() == 0) {
            argument.setReturn(null);
        } else {
            argument.setReturn(
                    argument.memory().get(
                            argument.arg(0)
                    )
            );
        }
    }
}
