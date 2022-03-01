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
Eval_01_New.baseObject        thrpt    5  2769846743.211 ± 1251017024.918  ops/s
Eval_01_New.evalexExpression  thrpt    5      304593.756 ±       4089.709  ops/s
Eval_01_New.jepJep            thrpt    5      113158.785 ±       3633.124  ops/s
Eval_01_New.jexlEngine        thrpt    5      243558.972 ±      20123.833  ops/s
Eval_01_New.jexlExpression    thrpt    5       58524.740 ±       9748.931  ops/s

Eval_02_Eval.evalexEval       thrpt    5      219957.978 ±       6077.643  ops/s
Eval_02_Eval.jepEval          thrpt    5       46113.180 ±       1709.243  ops/s
Eval_02_Eval.jexlEval         thrpt    5       44076.983 ±        613.771  ops/s

Eval_03_Reuse.evalexReuse     thrpt    5     2290644.521 ±      33731.424  ops/s
Eval_03_Reuse.jepReuse        thrpt    5    20230248.068 ±     993112.064  ops/s
Eval_03_Reuse.jexlReuse       thrpt    5     3282707.760 ±      56835.932  ops/s
```
