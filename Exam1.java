import java.io.ByteArrayInputStream;
import java.io.Console;
import java.util.List;
import jdk.jshell.*;
import jdk.jshell.Snippet.Status;

public class Exam1 {
    public static void main(String[] args) {
        try (JShell js = JShell.create()) {
        }
        System.out.println("Hello Exam1");
    }
}
