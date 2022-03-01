package net.kaoriya.examination.eval;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

import com.udojava.evalex.Expression;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import com.singularsys.jep.Jep;
import com.singularsys.jep.JepException;
import com.singularsys.jep.parser.Node;

public class Eval_03_Reuse {

    final static Expression evalexEx = new Expression("a*x+b")
        .with("a", "2.0")
        .with("b", "5.0");

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void evalexReuse() {
        var r = evalexEx.with("x", "10.0").eval();
    }

    final static JexlExpression jexlEx;
    final static MapContext jexlCtx;

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void jexlReuse() {
        jexlCtx.set("x", 10.0f);
        var r = jexlEx.evaluate(jexlCtx);
    }

    final static Jep jepJep;
    final static Node jepNode;

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void jepReuse() {
        try {
            jepJep.addVariable("x", 10.0f);
            var r = jepJep.evaluate(jepNode);
        } catch (JepException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        var eng = new JexlBuilder().create();
        jexlEx = eng.createExpression("a*x+b");
        jexlCtx = new MapContext();
        jexlCtx.set("a", 2.0f);
        jexlCtx.set("b", 5.0f);

        jepJep = new Jep();
        try {
            jepNode = jepJep.parse("a*x+b");
            jepJep.addConstant("a", 2.0f);
            jepJep.addConstant("b", 5.0f);
        } catch (JepException e) {
            throw new RuntimeException(e);
        }
    }

}
