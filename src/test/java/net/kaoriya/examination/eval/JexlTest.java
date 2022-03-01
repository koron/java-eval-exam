package net.kaoriya.examination.eval;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.MapContext;

public class JexlTest {
    @Test
    public void simple() {
        var eng = new JexlBuilder().create();
        var ex = eng.createExpression("a*x+b");
        var ctx = new MapContext();
        ctx.set("a", 2.0f);
        ctx.set("x", 10.0f);
        ctx.set("b", 5.0f);
        var got = ex.evaluate(ctx);
        var want = 25.0d;
        assertEquals(want, got);
    }

    @Test
    public void reuse() {
        var eng = new JexlBuilder().create();
        var ex = eng.createExpression("a*x+b");
        var ctx = new MapContext();
        ctx.set("a", 2.0f);
        ctx.set("b", 5.0f);

        ctx.set("x", 10.0f);
        var got1 = ex.evaluate(ctx);
        assertEquals(25.0d, got1);
        ctx.set("x", 20.0f);
        var got2 = ex.evaluate(ctx);
        assertEquals(45.0d, got2);
    }
}
