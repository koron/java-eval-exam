package net.kaoriya.examination.eval2;

import com.singularsys.jep.EvaluationException;
import com.singularsys.jep.Jep;
import com.singularsys.jep.JepException;
import com.singularsys.jep.functions.NaryFunction;
import com.singularsys.jep.parser.Node;

public class JepExpr {
    Jep  jep;
    Node node;

    public JepExpr(String s) throws JepException {
        jep = new Jep();
        var ft = jep.getFunctionTable();
        ft.addFunction("ge", new NaryFunction() {
            @Override
            public Object eval(Object[] args) throws EvaluationException {
                // FIXME: implement me.
                return true;
            }
        });
        ft.addFunction("le", new NaryFunction() {
            @Override
            public Object eval(Object[] args) throws EvaluationException {
                // FIXME: implement me.
                return true;
            }
        });
        node = jep.parse(s);
    }

    public Object eval(Object arg) throws JepException {
        jep.addVariable("args", arg);
        return jep.evaluate(node);
    }
}
