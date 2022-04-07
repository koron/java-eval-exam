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
Benchmark                               Mode  Cnt           Score            Error  Units
Eval_01_New.baseObject                 thrpt    5  2490266796.847 ± 1325717439.299  ops/s
Eval_01_New.evalexExpression           thrpt    5      279708.553 ±       9244.544  ops/s
Eval_01_New.jepJep                     thrpt    5      114788.733 ±       1600.668  ops/s
Eval_01_New.jexlEngine                 thrpt    5      313393.169 ±       2584.883  ops/s
Eval_01_New.jexlExpression             thrpt    5       28578.890 ±       1059.973  ops/s
Eval_02_Eval.evalexEval                thrpt    5      227577.512 ±       6562.914  ops/s
Eval_02_Eval.jepEval                   thrpt    5       54327.699 ±       1709.915  ops/s
Eval_02_Eval.jexlEval                  thrpt    5       25140.340 ±        556.277  ops/s
Eval_03_Reuse.evalexReuse              thrpt    5     2300800.840 ±     174685.322  ops/s
Eval_03_Reuse.jepReuse                 thrpt    5    26335090.096 ±    1028274.807  ops/s
Eval_03_Reuse.jexlReuse                thrpt    5     3099649.975 ±     291877.250  ops/s
Eval_04_MultiThread.evalexEval2        thrpt    5      407344.577 ±      45966.304  ops/s
Eval_04_MultiThread.evalexEval4        thrpt    5      777837.376 ±      88251.333  ops/s
Eval_04_MultiThread.evalexEval8        thrpt    5     1443633.117 ±     121250.675  ops/s
Eval_04_MultiThread.jepEval2           thrpt    5      100343.281 ±       6044.022  ops/s
Eval_04_MultiThread.jepEval4           thrpt    5      175551.249 ±       5664.987  ops/s
Eval_04_MultiThread.jepEval8           thrpt    5      208415.578 ±       3572.318  ops/s
Eval_04_MultiThread.jexlEval2          thrpt    5       46656.107 ±       2145.552  ops/s
Eval_04_MultiThread.jexlEval4          thrpt    5       91334.013 ±       8344.512  ops/s
Eval_04_MultiThread.jexlEval8          thrpt    5      180454.276 ±      13908.107  ops/s
Eval_05_MultiThread_Reuse.evalexEval2  thrpt    5     4032092.534 ±     179572.596  ops/s
Eval_05_MultiThread_Reuse.evalexEval4  thrpt    5     7506913.925 ±     551191.105  ops/s
Eval_05_MultiThread_Reuse.evalexEval8  thrpt    5    14722908.350 ±    1714358.729  ops/s
Eval_05_MultiThread_Reuse.jepEval2     thrpt    5    44592106.017 ±    3335104.262  ops/s
Eval_05_MultiThread_Reuse.jepEval4     thrpt    5    87787555.060 ±    7085435.880  ops/s
Eval_05_MultiThread_Reuse.jepEval8     thrpt    5   152326671.324 ±   10647251.629  ops/s
Eval_05_MultiThread_Reuse.jexlEval2    thrpt    5     6706348.580 ±     392654.243  ops/s
Eval_05_MultiThread_Reuse.jexlEval4    thrpt    5    12234884.588 ±     626363.129  ops/s
Eval_05_MultiThread_Reuse.jexlEval8    thrpt    5    24256910.945 ±    1792408.408  ops/s
```

## Conclusion

Jep's evaluation is not slow.
It requires right strategy of reusing Jep related objects.

The compiling of Jep and Jexl is not fast.

The compiling of Jep and Jexl generates big expression tree.
It inculdes many objects, it may make GC slow when many engines and compiled nodes are kept.

Under correct object reusing strategy...

* Jep is faster x10 than EvalEx
* Jexl is falster x1.65 than EvalEx
