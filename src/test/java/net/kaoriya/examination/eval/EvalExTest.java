package net.kaoriya.examination.eval;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import com.udojava.evalex.Expression;

public class EvalExTest {
    @Test
    public void simple() {
        var got = new Expression("a*x+b")
            .with("a", "2.0")
            .with("x", "10.0")
            .with("b", "5.0")
            .eval();
        var want = new BigDecimal(25.0f);
        assertEquals(want, got);
    }

    @Test
    public void reuse() {
        var ex = new Expression("a*x+b")
            .with("a", "2.0")
            .with("b", "5.0");

        var got1 = ex.with("x", "10.0").eval();
        assertEquals(new BigDecimal(25.0f), got1);
        var got2 = ex.with("x", "20.0").eval();
        assertEquals(new BigDecimal(45.0f), got2);
    }
}
