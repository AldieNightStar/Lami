package lami.std_cmds;

import lami.LamiCommand;

public class LamiConcat implements LamiCommand {

    @Override
    public void call(Argument argument) {
        if (argument.argsLen() == 2) {
            Object o1 = argument.arg(0);
            Object o2 = argument.arg(1);
            if (o1 instanceof String && o2 instanceof String) {
                String s1 = (String) o1;
                String s2 = (String) o2;
                argument.setReturn(s1 + s2);
            } else {
                argument.setReturn(null);
            }
        } else if (argument.argsLen() == 1) {
            argument.setReturn(argument.parseArg(0));
        } else {
            argument.setReturn(null);
        }
    }
}
