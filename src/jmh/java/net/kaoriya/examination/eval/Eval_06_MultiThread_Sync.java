package net.kaoriya.examination.eval;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import java.util.concurrent.TimeUnit;

import com.udojava.evalex.Expression;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import com.singularsys.jep.Jep;
import com.singularsys.jep.JepException;

public class Eval_06_MultiThread_Sync {

    @State(Scope.Benchmark)
    public static class EvalexState {
        Expression expr;
        public EvalexState() {
            expr = new Expression("a*x+b")
                .with("a", "2.0")
                .with("b", "5.0");
        }
        public Object eval(double x) {
            synchronized (expr) {
                return expr.with("x", Double.toString(x)).eval();
            }
        }
    }

    @Threads(2)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void evalexEval2(EvalexState s, Blackhole bh) {
        bh.consume(s.eval(123f));
    }

    @Threads(4)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void evalexEval4(EvalexState s, Blackhole bh) {
        bh.consume(s.eval(123f));
    }

    @Threads(8)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void evalexEval8(EvalexState s, Blackhole bh) {
        bh.consume(s.eval(123f));
    }

    @State(Scope.Benchmark)
    public static class JexlState {
        JexlEngine engine;
        JexlExpression expr;
        MapContext ctx;
        public JexlState() {
            engine = new JexlBuilder().create();
            expr = engine.createExpression("a*x+b");
            ctx = new MapContext();
            ctx.set("a", 2.0f);
            ctx.set("b", 5.0f);
        }
        public Object eval(double x) {
            synchronized (ctx) {
                ctx.set("x", x);
                return expr.evaluate(ctx);
            }
        }
    }

    @Threads(2)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void jexlEval2(JexlState s, Blackhole bh) {
        bh.consume(s.eval(123f));
    }

    @Threads(4)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void jexlEval4(JexlState s, Blackhole bh) {
        bh.consume(s.eval(123f));
    }

    @Threads(8)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void jexlEval8(JexlState s, Blackhole bh) {
        bh.consume(s.eval(123f));
    }

    @State(Scope.Benchmark)
    public static class JepState {
        Jep jep;
        public JepState() {
            jep = new Jep();
            try {
                jep.addVariable("a", 2.0f);
                jep.addVariable("b", 5.0f);
                jep.parse("a*x+b");
            } catch (JepException e) {
                throw new RuntimeException(e);
            }
        }
        public Object eval(double x) {
            try {
                synchronized (jep) {
                    jep.addVariable("x", x);
                    return jep.evaluate();
                }
            } catch (JepException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Threads(2)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void jepEval2(JepState s, Blackhole bh) {
        bh.consume(s.eval(123f));
    }

    @Threads(4)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void jepEval4(JepState s, Blackhole bh) {
        bh.consume(s.eval(123f));
    }

    @Threads(8)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void jepEval8(JepState s, Blackhole bh) {
        bh.consume(s.eval(123f));
    }
}
