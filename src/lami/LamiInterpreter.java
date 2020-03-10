package lami;

import java.util.HashMap;
import java.util.Map;

public class LamiInterpreter {
    public Map<String, Object> memory = new HashMap<>();

    public Object parseArgument(String arg) {
        if (isQuoted(arg)) {
            if (arg.length() > 2) {
                return arg.substring(1, arg.length() - 1);
            } else {
                return "";
            }
        } else if (isNumber(arg)) {
            return Double.valueOf(arg);
        } else {
            return memory.get(arg);
        }
    }

    private boolean isNumber(String arg) {
        try {
            Double.parseDouble(arg);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isQuoted(String s) {
        return s.startsWith("\"") && s.endsWith("\"");
    }
}
