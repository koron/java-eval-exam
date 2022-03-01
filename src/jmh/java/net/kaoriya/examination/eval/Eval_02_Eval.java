package net.kaoriya.examination.eval;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

import com.udojava.evalex.Expression;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.MapContext;
import com.singularsys.jep.Jep;
import com.singularsys.jep.JepException;

public class Eval_02_Eval {

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void evalexEval() {
        var r = new Expression("a*x+b")
            .with("a", "2.0")
            .with("x", "10.0")
            .with("b", "5.0")
            .eval();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void jexlEval() {
        var eng = new JexlBuilder().create();
        var ex = eng.createExpression("a*x+b");
        var ctx = new MapContext();
        ctx.set("a", 2.0f);
        ctx.set("x", 10.0f);
        ctx.set("b", 5.0f);
        var r = ex.evaluate(ctx);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void jepEval() {
        var jep = new Jep();
        try {
            jep.addVariable("a", 2.0f);
            jep.addVariable("x", 10.0f);
            jep.addVariable("b", 5.0f);
            jep.parse("a*x+b");
            var r = jep.evaluate();
        } catch (JepException e) {
            throw new RuntimeException(e);
        }
    }
}
