package net.kaoriya.examination.eval2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

import com.udojava.evalex.Expression;
import com.udojava.evalex.AbstractFunction;

@State(Scope.Benchmark)
public class EvalSegment {
    @Param({"10", "100", "1000", "10000"})
    private int size;

    private List<Expression> segmentExpressions;

    @Setup
    public void setup() {
        segmentExpressions = new ArrayList<Expression>(size);
        for (int i = 0; i < size; i++) {
            var ex = new Expression("(ge(results, 1588, 0) && le(results, 1588, 10000)) && (ge(results, 1589, 0) && le(results, 1589, 10000)) && (ge(results, 1590, 0) && le(results, 1590, 10000)) && (ge(results, 1591, 0) && le(results, 1591, 10000)) && (ge(results, 1592, 0) && le(results, 1592, 10000)) && NOT(ge(results, 1593, 0) && le(results, 1593, 10000)) && (ge(results, 230, 0) && le(results, 230, 10000)) && (ge(results, 231, 0) && le(results, 231, 10000)) && (ge(results, 232, 0) && le(results, 232, 10000)) && (ge(results, 233, 0) && le(results, 233, 10000)) && (ge(results, 234, 0) && le(results, 234, 10000)) && (ge(results, 235, 0) && le(results, 235, 10000)) && (ge(results, 236, 0) && le(results, 236, 10000)) && (ge(results, 237, 0) && le(results, 237, 10000)) && (ge(results, 238, 0) && le(results, 238, 10000)) && (ge(results, 239, 0) && le(results, 239, 10000)) && NOT(ge(results, 241, 0) && le(results, 241, 10000))");
            ex.addFunction(new AbstractFunction("ge", 3, true) {
                @Override
                public BigDecimal eval(List<BigDecimal> params) {
                    return BigDecimal.ONE;
                }
            });
            ex.addFunction(new AbstractFunction("le", 3, true) {
                @Override
                public BigDecimal eval(List<BigDecimal> params) {
                    return BigDecimal.ONE;
                }
            });
            ex.setVariable("results", BigDecimal.ZERO);
            segmentExpressions.add(ex);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3)
    @Fork(value = 1)
    @Measurement(iterations = 3, timeUnit = TimeUnit.NANOSECONDS)
    public void evalEx() {
        for (var ex : segmentExpressions) {
            ex.eval();
        }
    }
}
