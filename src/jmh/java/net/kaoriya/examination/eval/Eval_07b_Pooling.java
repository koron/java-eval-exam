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

import cn.danielw.fop.ObjectFactory;
import cn.danielw.fop.ObjectPool;
import cn.danielw.fop.PoolConfig;

@State(Scope.Benchmark)
public class Eval_07b_Pooling {

    public static class Evaluator {
        Jep jep;
        public Evaluator() {
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
                jep.addVariable("x", x);
                return jep.evaluate();
            } catch (JepException e) {
                throw new RuntimeException(e);
            }
        }
    }

    ObjectPool<Evaluator> pool = new ObjectPool<>(
            new PoolConfig()
                .setPartitionSize(5)
                .setMaxSize(10)
                .setMinSize(5)
                .setMaxIdleMilliseconds(300*1000),
            new ObjectFactory<Evaluator>() {
                @Override
                public Evaluator create() {
                    return new Evaluator();
                }
                @Override
                public void destroy(Evaluator o) {}
                @Override
                public boolean validate(Evaluator o) {
                    return true;
                }
            });

    Object evalRun(double x) throws Exception {
        try (var obj = pool.borrowObject()) {
            return obj.getObject().eval(x);
        }
    }

    @Threads(1)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void eval1(Blackhole bh) throws Exception {
        bh.consume(evalRun(123f));
    }

    @Threads(2)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void eval2(Blackhole bh) throws Exception {
        bh.consume(evalRun(123f));
    }

    @Threads(4)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void eval4(Blackhole bh) throws Exception {
        bh.consume(evalRun(123f));
    }

    @Threads(8)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void eval8(Blackhole bh) throws Exception {
        bh.consume(evalRun(123f));
    }
}
