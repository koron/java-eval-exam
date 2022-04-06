package net.kaoriya.examination.eval2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

import com.singularsys.jep.Jep;
import com.singularsys.jep.JepException;

@State(Scope.Benchmark)
public class EvalSegmentJEP4 {
    @Param({"100", "1000", "10000", "100000"})
    private int size;

    // Keep background Jep objects.
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3)
    @Fork(value = 1)
    @Measurement(iterations = 3, timeUnit = TimeUnit.NANOSECONDS)
    public void evalJep() throws JepException {
        var backgroundJeps = new Jep[size];
        for (int i = 0; i < size; i++) {
            backgroundJeps[i] = new Jep();
        }
        var expr = new JepExpr("(ge(args, 1588, 0) && le(args, 1588, 10000)) && (ge(args, 1589, 0) && le(args, 1589, 10000)) && (ge(args, 1590, 0) && le(args, 1590, 10000)) && (ge(args, 1591, 0) && le(args, 1591, 10000)) && (ge(args, 1592, 0) && le(args, 1592, 10000)) && !(ge(args, 1593, 0) && le(args, 1593, 10000)) && (ge(args, 230, 0) && le(args, 230, 10000)) && (ge(args, 231, 0) && le(args, 231, 10000)) && (ge(args, 232, 0) && le(args, 232, 10000)) && (ge(args, 233, 0) && le(args, 233, 10000)) && (ge(args, 234, 0) && le(args, 234, 10000)) && (ge(args, 235, 0) && le(args, 235, 10000)) && (ge(args, 236, 0) && le(args, 236, 10000)) && (ge(args, 237, 0) && le(args, 237, 10000)) && (ge(args, 238, 0) && le(args, 238, 10000)) && (ge(args, 239, 0) && le(args, 239, 10000)) && !(ge(args, 241, 0) && le(args, 241, 10000))");
        for (int i = 0; i < size; i++) {
            expr.eval(i);
        }
    }
}

/* Example outputs:

Benchmark                (size)  Mode  Cnt  Score    Error  Units
EvalSegmentJEP4.evalJep     100  avgt    3  0.001 ±  0.001   s/op
EvalSegmentJEP4.evalJep    1000  avgt    3  0.010 ±  0.001   s/op
EvalSegmentJEP4.evalJep   10000  avgt    3  0.092 ±  0.006   s/op
EvalSegmentJEP4.evalJep  100000  avgt    3  1.784 ±  0.413   s/op
*/
