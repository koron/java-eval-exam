# Evals examination

## Preparation

1. Copy a `jep-java-4.0-trial.jar` from <http://www.singularsys.com/jep/download-trial.php> to libs

## Compile

```console
$ ./gradlew compileJmhJava
```

## Run all benchmarks

```console
$ ./gradlew jmh
```

## Result snapshot

```
Benchmark                      Mode  Cnt           Score            Error  Units
Eval_01_New.baseObject        thrpt    5  2733657967.398 ± 1259269177.687  ops/s
Eval_01_New.evalexExpression  thrpt    5      298424.327 ±       4498.390  ops/s
Eval_01_New.jepJep            thrpt    5      111941.275 ±       1430.380  ops/s
Eval_01_New.jexlEngine        thrpt    5      241220.382 ±       4984.511  ops/s
Eval_01_New.jexlExpression    thrpt    5       57791.790 ±      10454.004  ops/s
Eval_02_Eval.evalexEval       thrpt    5      224663.806 ±       2558.878  ops/s
Eval_02_Eval.jepEval          thrpt    5       48744.718 ±       1051.533  ops/s
Eval_02_Eval.jexlEval         thrpt    5       45237.653 ±       1357.799  ops/s
```
