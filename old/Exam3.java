import javax.script.*;

public class Exam3 {

    public static void run(ScriptEngineManager m) {
        long t1 = 0, t2 = 0, t3 = 0;
        t1 = System.nanoTime();
        var e = m.getEngineByName("javascript");
        t2 = System.nanoTime();
        try {
            var r = e.eval("a * x + b");
            t3 = System.nanoTime();
            System.out.println(r.toString());
        } catch (ScriptException ex) {
            ex.printStackTrace();
        }
        System.out.println("t2-t1=" + (t2-t1));
        System.out.println("t3-t2=" + (t3-t2));
    }

    public static void main(String[] args) throws Exception {
        double a = 2.0;
        double x = 10.0;
        double b = 5.0;

        var m = new ScriptEngineManager();
        m.put("a", a);
        m.put("x", x);
        m.put("b", b);

        run(m);
        run(m);
        run(m);
    }

}
