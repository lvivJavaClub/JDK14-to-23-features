package n03_jep77_ZGC_jep379_Shenandoah;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        for (int i = 0; i < 1_000_000; i++) {
            doNothingWith(String.valueOf(System.currentTimeMillis()));
        }
    }

    private static void doNothingWith(String string) {
        var list = new ArrayList<String>();
        list.add(string);
    }
}
