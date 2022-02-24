import java.io.ByteArrayInputStream;
import java.io.Console;
import java.util.List;
import jdk.jshell.*;
import jdk.jshell.Snippet.Status;

public class Exam2 {
    // can't delete Snippets: memory increasing
    public static void main(String[] args) {
        try (JShell js = JShell.create()) {
            js.eval("var a = 3;");
            var overrides = js.eval("a = 2;");
            for (SnippetEvent ev : js.eval("a;")) {
                System.out.println("#1: " + ev.value());
            }
            for (SnippetEvent ev : overrides) {
                js.drop(ev.snippet());
            }
            for (SnippetEvent ev : js.eval("a;")) {
                System.out.println("#2: " + ev.value());
            }
            js.snippets().forEach((Snippet s) -> {
                System.out.println(s.source());
            });
        }
    }
}
