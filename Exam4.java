import java.io.ByteArrayInputStream;
import java.io.Console;
import java.util.List;
import jdk.jshell.*;
import jdk.jshell.Snippet.Status;

public class Exam4 {
    public static void run(int n, JShell js) {
        long t1 = System.nanoTime();
        var r = js.eval("a * x + b;");
        long t2 = System.nanoTime();
        System.out.println(String.format("#%d %d", n, (t2-t1)));
    }

    public static void main(String[] args) {
        try (JShell js = JShell.create()) {
            js.eval("double a =  2.0;");
            js.eval("double x = 10.0;");
            js.eval("double b =  5.0;");
            run(0, js);
            run(1, js);
            run(2, js);
        }
    }
}
