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
Eval_01_New.baseObject           thrpt    5  2766085883.025 ± 1218645915.531  ops/s
Eval_01_New.evalexExpression     thrpt    5      302475.225 ±       5569.765  ops/s
Eval_01_New.jepJep               thrpt    5      113618.868 ±       5872.462  ops/s
Eval_01_New.jexlEngine           thrpt    5      247880.551 ±       2765.692  ops/s
Eval_01_New.jexlExpression       thrpt    5       59490.711 ±      12413.502  ops/s
Eval_02_Eval.evalexEval          thrpt    5      224968.554 ±       3061.182  ops/s
Eval_02_Eval.jepEval             thrpt    5       50352.092 ±       5619.090  ops/s
Eval_02_Eval.jexlEval            thrpt    5       45470.195 ±       1574.396  ops/s
Eval_03_Reuse.evalexReuse        thrpt    5     2294690.430 ±      38412.226  ops/s
Eval_03_Reuse.jepReuse           thrpt    5    20188431.079 ±     511584.618  ops/s
Eval_03_Reuse.jexlReuse          thrpt    5     3246440.548 ±      82957.406  ops/s
Eval_04_MultiThread.evalexEval2  thrpt    5      438463.788 ±       9124.432  ops/s
Eval_04_MultiThread.evalexEval4  thrpt    5      858251.720 ±       5896.510  ops/s
Eval_04_MultiThread.evalexEval8  thrpt    5     1714173.948 ±      10395.281  ops/s
Eval_04_MultiThread.jepEval2     thrpt    5       89089.766 ±       1575.176  ops/s
Eval_04_MultiThread.jepEval4     thrpt    5      134542.912 ±       4121.231  ops/s
Eval_04_MultiThread.jepEval8     thrpt    5      143729.124 ±      10090.770  ops/s
Eval_04_MultiThread.jexlEval2    thrpt    5       83790.762 ±       9064.280  ops/s
Eval_04_MultiThread.jexlEval4    thrpt    5       94071.705 ±      16363.136  ops/s
Eval_04_MultiThread.jexlEval8    thrpt    5       51887.606 ±       4261.213  ops/s
```

at 8 threads:

* EvalEx: x7.5
* JEP: x3.3
* JEXL: x1.2
