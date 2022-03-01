## Exam1: JShell.create & close の時間計測

1回あたり0.1秒かかる。
なので使いまわす必要がある。
用途次第ではかなり苦しい。

```console
$ javac Exam1.java && java Exam1
#0 134217000 ns/round
#1 110665940 ns/round
#2 109624240 ns/round
#3 110509240 ns/round
#4 110146090 ns/round
#5 110212710 ns/round
#6 114382690 ns/round
#7 110145360 ns/round
#8 110828800 ns/round
#9 109811210 ns/round
```

a * x + b
実行時間は20msecくらい

# Exam3: ScriptingEngine (JDK11)

実行オーダーはミリ秒

エンジンの作成は4msec
シンプルな評価は5~8msec

JDK14以降はNashornが消えるので
GraalJSに移行する必要があり、
また違ってくるかも

```console
$ javac Exam3.java && java Exam3
Warning: Nashorn engine is planned to be removed from a future JDK release
25.0
t2-t1=196955500
t3-t2=68766500
Warning: Nashorn engine is planned to be removed from a future JDK release
25.0
t2-t1=4115500
t3-t2=5498300
Warning: Nashorn engine is planned to be removed from a future JDK release
25.0
t2-t1=3808800
t3-t2=8701000
```
