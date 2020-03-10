package lami;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface LamiCommand {
    void call(Argument argument);

    class Argument {
        private SubArgument sub;

        public Argument(SubArgument sub) {
            this.sub = sub;
        }

        public LamiExecutor lamiExecutor;

        public Object parseArg(int n) {
            if (n < 0) return null;
            List<String> args = sub.token.args;
            if (args.isEmpty()) return null;
            if (args.size() < n - 1) return null;
            String arg = args.get(n);
            return sub.interpreter.parseArgument(arg);
        }

        public String getArgsLine() {
            return sub.token.argsLine;
        }

        public String arg(int n) {
            if (n < 0) return null;
            List<String> args = sub.token.args;
            if (args.isEmpty()) return null;
            if (args.size() < n -1) return null;
            return args.get(n);
        }

        public int argsLen() {
            return sub.token.args.size();
        }

        public void setReturn(Object o) {
            sub.onReturn.accept(o);
        }

        public Map<String, Object> memory() {
            return sub.interpreter.memory;
        }
    }

    class SubArgument {
        public LamiParser.Token token;
        public LamiInterpreter interpreter;
        public Consumer<Object> onReturn;
    }

}
