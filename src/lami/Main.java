package lami;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        Lami lami = new Lami();
        lami.execute(new File("./code.txt"));
    }
}
