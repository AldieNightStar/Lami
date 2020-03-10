package lami.std_cmds;

import lami.LamiCommand;

public class LamiPrint implements LamiCommand {

    @Override
    public void call(Argument argument) {
        if (argument.argsLen() == 0) {
            System.out.println();
        } else {
            System.out.println(
                    argument.lamiExecutor.eval(argument.getArgsLine())
            );
        }
    }
}
