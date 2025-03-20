import java.util.List;

public class LispFunction implements Callable {
    private final List<String> parameters;
    private final List<LispExpression> body;
    private final Environment closure;

    public LispFunction(List<String> parameters, List<LispExpression> body, Environment closure) {
        this.parameters = parameters;
        this.body = body;
        this.closure = closure;
    }

    @Override
    public Object call(List<LispExpression> arguments, Environment env) {
        if (arguments.size() != parameters.size()) {
            throw new RuntimeException("Número incorrecto de argumentos.");
        }

        Environment localEnv = closure.createChild();
        for (int i = 0; i < parameters.size(); i++) {
            localEnv.define(parameters.get(i), arguments.get(i).evaluate(env));
        }

        Object result = null;
        for (LispExpression expr : body) {
            result = expr.evaluate(localEnv);
        }
        return result;
    }
}
