package net.kaoriya.examination.eval;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

import com.udojava.evalex.Expression;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import com.singularsys.jep.Jep;

public class Eval_01_New {

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void baseObject() {
        var o = new Object();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void evalexExpression() {
        var ex = new Expression("a*x+b");
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void jexlEngine() {
        var eng = new JexlBuilder().create();
    }

    final static JexlEngine engine = new JexlBuilder().create();

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void jexlExpression() {
        var ex = engine.createExpression("a*x+b");
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void jepJep() {
        var jep = new Jep();
    }
}
