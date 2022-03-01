import java.io.ByteArrayInputStream;
import java.io.Console;
import java.util.List;
import jdk.jshell.*;
import jdk.jshell.Snippet.Status;

public class Exam1 {
    public static final long ROUNDS = 10;

    public static void run() {
        for (long i = 0; i < ROUNDS; i++) {
            try (JShell js = JShell.create()) {
            }
        }
    }

    public static final int TURNS = 10;

    public static void main(String[] args) {
        for (int i = 0; i < TURNS; i++) {
            long start = System.nanoTime();
            run();
            long dur = System.nanoTime() - start;
            System.out.println(String.format("#%d %d ns/round", i, dur / ROUNDS));
        }
    }
}
