package net.kaoriya.examination.eval;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.singularsys.jep.Jep;
import com.singularsys.jep.JepException;

public class JepTest {
    @Test
    public void simple() throws JepException {
        var jep = new Jep();
        jep.addVariable("a", 2.0f);
        jep.addVariable("x", 10.0f);
        jep.addVariable("b", 5.0f);
        jep.parse("a*x+b");
        var got = jep.evaluate();
        var want = 25.0d;
        assertEquals(want, got);
    }

    @Test
    public void reuse() throws JepException {
        var jep = new Jep();
        var node = jep.parse("a*x+b");
        jep.addConstant("a", 2.0f);
        jep.addConstant("b", 5.0f);

        jep.addVariable("x", 10.0f);
        var got1 = jep.evaluate(node);
        assertEquals(25.0d, got1);
        jep.addVariable("x", 20.0f);
        var got2 = jep.evaluate(node);
        assertEquals(45.0d, got2);
    }
}
