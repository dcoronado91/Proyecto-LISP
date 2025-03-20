import java.util.List;

public class LispList implements LispExpression {
    private final List<LispExpression> expressions;

    public LispList(List<LispExpression> expressions) {
        if (expressions == null || expressions.isEmpty()) {
            throw new IllegalArgumentException("La lista de expresiones no puede ser nula o vacía.");
        }
        this.expressions = expressions;
    }

    @Override
    public Object evaluate(Environment env) {
        if (expressions.isEmpty()) {
            throw new RuntimeException("Expresión vacía.");
        }
        LispExpression first = expressions.get(0);
        if (!(first instanceof LispSymbol)) {
            throw new RuntimeException("Se esperaba un símbolo como primer elemento de la lista, pero se encontró: " + first);
        }
        String functionName = ((LispSymbol) first).getName();
        List<LispExpression> args = expressions.subList(1, expressions.size());
        return LispBuiltins.call(functionName, args, env);
    }

    public List<LispExpression> getExpressions() {
        return expressions;
    }

    @Override
    public String toString() {
        return expressions.toString();
    }
}