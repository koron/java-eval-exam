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

## Extra investigations

### Synchronize eval engine

```
Eval_05_MultiThread_Reuse.evalexEval2  thrpt    5    3934125.081 ±   246037.486  ops/s
Eval_05_MultiThread_Reuse.evalexEval4  thrpt    5    7194421.140 ±   806521.832  ops/s
Eval_05_MultiThread_Reuse.evalexEval8  thrpt    5   13353807.796 ±   992707.901  ops/s
Eval_06_MultiThread_Sync.evalexEval2   thrpt    5    1727998.310 ±    65808.053  ops/s
Eval_06_MultiThread_Sync.evalexEval4   thrpt    5    1498609.159 ±   133966.243  ops/s
Eval_06_MultiThread_Sync.evalexEval8   thrpt    5    1736502.174 ±    77143.004  ops/s

Eval_05_MultiThread_Reuse.jepEval2     thrpt    5   44485078.375 ±  4880035.592  ops/s
Eval_05_MultiThread_Reuse.jepEval4     thrpt    5   83766744.245 ± 13306784.108  ops/s
Eval_05_MultiThread_Reuse.jepEval8     thrpt    5  148182175.981 ± 13031574.820  ops/s
Eval_06_MultiThread_Sync.jepEval2      thrpt    5    7718265.287 ±   185483.343  ops/s
Eval_06_MultiThread_Sync.jepEval4      thrpt    5    8491847.583 ±  2186456.327  ops/s
Eval_06_MultiThread_Sync.jepEval8      thrpt    5    7918188.215 ±   518408.885  ops/s

Eval_05_MultiThread_Reuse.jexlEval2    thrpt    5    6797417.324 ±   480425.452  ops/s
Eval_05_MultiThread_Reuse.jexlEval4    thrpt    5   12997428.096 ±   952946.391  ops/s
Eval_05_MultiThread_Reuse.jexlEval8    thrpt    5   23426943.729 ±   487036.764  ops/s
Eval_06_MultiThread_Sync.jexlEval2     thrpt    5    2599733.588 ±    97252.541  ops/s
Eval_06_MultiThread_Sync.jexlEval4     thrpt    5    2593430.000 ±   130930.024  ops/s
Eval_06_MultiThread_Sync.jexlEval8     thrpt    5    2609402.923 ±   403389.896  ops/s
```

論理的には1スレッド相当になると推測される。

EvalExとJEXLは1スレッド相当より若干劣る性能になる。
劣化分はsynchronize待ちのオーバーヘッドだと考えられる。

Jepの劣化は1スレッドを遥かに下回り、1/3程度になる。
妥当な説明は思いつかない。

### Jep with Object pool and ThreadLocal

* Object pool implementation
  * Common pool 2 (07)
  * Stormpot pool (07a)
  * Fast object pool (07b)
* ThreadLocal (08)

```
Benchmark                        Mode  Cnt          Score          Error  Units
Eval_07_Pooling.jepEval1        thrpt    5    4749310.534 ±   227262.368  ops/s
Eval_07_Pooling.jepEval2        thrpt    5    4924929.663 ±  4762844.461  ops/s
Eval_07_Pooling.jepEval4        thrpt    5    3284716.580 ±   338783.035  ops/s
Eval_07_Pooling.jepEval8        thrpt    5    3284722.780 ±   398465.344  ops/s

Eval_07a_Pooling.stormpotEval1  thrpt    5   11545564.876 ±   461323.591  ops/s
Eval_07a_Pooling.stormpotEval2  thrpt    5   22952489.758 ±   390699.458  ops/s
Eval_07a_Pooling.stormpotEval4  thrpt    5   43362913.964 ±   867632.228  ops/s
Eval_07a_Pooling.stormpotEval8  thrpt    5   81609398.122 ± 22578299.321  ops/s

Eval_07b_Pooling.eval1          thrpt    5   12176898.786 ±   332250.566  ops/s
Eval_07b_Pooling.eval2          thrpt    5   24112828.849 ±  1697339.810  ops/s
Eval_07b_Pooling.eval4          thrpt    5   43880407.492 ±  2501592.022  ops/s
Eval_07b_Pooling.eval8          thrpt    5   30102524.164 ± 11792376.598  ops/s

Eval_08_ThreadLocal.jepEval1    thrpt    5   16837388.232 ±   898890.114  ops/s
Eval_08_ThreadLocal.jepEval2    thrpt    5   32229808.054 ±  3430331.533  ops/s
Eval_08_ThreadLocal.jepEval4    thrpt    5   59676778.928 ±  2567407.760  ops/s
Eval_08_ThreadLocal.jepEval8    thrpt    5  118469039.264 ±  4723494.010  ops/s
```
