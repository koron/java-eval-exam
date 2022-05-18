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

import stormpot.Allocator;
import stormpot.BasePoolable;
import stormpot.Pool;
import stormpot.Slot;
import stormpot.Timeout;

@State(Scope.Benchmark)
public class Eval_07a_Pooling {

    public static class PoolableEvaluator extends BasePoolable {
        Jep jep;
        public PoolableEvaluator(Slot slot) {
            super(slot);
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

    public static class EvaluatorAllocator implements Allocator<PoolableEvaluator> {
        public PoolableEvaluator allocate(Slot slot) throws Exception {
            return new PoolableEvaluator(slot);
        }
        public void deallocate(PoolableEvaluator poolable) {
            // nothing to do.
        }
    }

    Pool<PoolableEvaluator> stormpotPool = Pool.from(new EvaluatorAllocator()).build();
    Timeout stormpotTimeout = new Timeout(1, TimeUnit.SECONDS);

    Object stormpotEvalRun(double x) throws Exception {
        var ex = stormpotPool.claim(stormpotTimeout);
        try {
            return ex.eval(x);
        } finally {
            if (ex != null) {
                ex.release();
            }
        }
    }

    @Threads(1)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void stormpotEval1(Blackhole bh) throws Exception {
        bh.consume(stormpotEvalRun(123f));
    }

    @Threads(2)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void stormpotEval2(Blackhole bh) throws Exception {
        bh.consume(stormpotEvalRun(123f));
    }

    @Threads(4)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void stormpotEval4(Blackhole bh) throws Exception {
        bh.consume(stormpotEvalRun(123f));
    }

    @Threads(8)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void stormpotEval8(Blackhole bh) throws Exception {
        bh.consume(stormpotEvalRun(123f));
    }
}
