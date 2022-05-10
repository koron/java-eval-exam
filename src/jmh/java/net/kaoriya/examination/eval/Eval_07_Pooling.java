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

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;

@State(Scope.Benchmark)
public class Eval_07_Pooling {

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

    public static class Factory extends BasePooledObjectFactory<Evaluator> {
        public Evaluator create() {
            return new Evaluator();
        }
        public PooledObject<Evaluator> wrap(Evaluator obj) {
            return new DefaultPooledObject<>(obj);
        }
    }

    ObjectPool<Evaluator> pool = new GenericObjectPool<>(new Factory());

    Object jepEvalRun(double x) {
        try {
            var ex = pool.borrowObject();
            Object rv = ex.eval(x);
            pool.returnObject(ex);
            return rv;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Threads(1)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void jepEval1(Blackhole bh) {
        bh.consume(jepEvalRun(123f));
    }

    @Threads(2)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void jepEval2(Blackhole bh) {
        bh.consume(jepEvalRun(123f));
    }

    @Threads(4)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void jepEval4(Blackhole bh) {
        bh.consume(jepEvalRun(123f));
    }

    @Threads(8)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public void jepEval8(Blackhole bh) {
        bh.consume(jepEvalRun(123f));
    }
}
