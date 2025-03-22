import java.util.ArrayList;
import java.util.List;

/**
 * Descripción: clase encargada de hacer tests para métodos de parseTokens y extractTokens en tokenizer.
 * 
 * Representa una lista de expresiones LISP. Esta clase implementa la evaluación
 * de listas, incluyendo formas especiales como quote, setq, defun y cond.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 10/03/2025
 * @last_modified 19/03/2025
 */
public class LispList implements LispExpression {
    private final List<LispExpression> expressions;

    /**
     * Constructor que inicializa la lista con expresiones LISP.
     * 
     * @param expressions Lista de expresiones LISP.
     */
    public LispList(List<LispExpression> expressions) {
        this.expressions = expressions;
    }

    /**
     * Evalúa la lista de expresiones en el entorno dado.
     * Soporta formas especiales (quote, setq, defun, cond) y llamadas a funciones.
     * 
     * @param env El entorno donde se evalúan las expresiones.
     * @return El resultado de la evaluación.
     */
    @Override
    public Object evaluate(Environment env) {
        if (expressions.isEmpty()) {
            return null; // Lista vacía evalúa a nil
        }

        LispExpression first = expressions.get(0);

        // Manejar formas especiales primero
        if (first instanceof LispSymbol) {
            String name = ((LispSymbol) first).getName();

            // Formas especiales que no evalúan todos sus argumentos
            switch (name.toLowerCase()) {
                case "quote":
                case "'":
                    if (expressions.size() != 2) {
                        throw new RuntimeException("QUOTE requiere exactamente un argumento");
                    }
                    return expressions.get(1); // Simplemente devuelve sin evaluar

                case "setq":
                    if (expressions.size() != 3) {
                        throw new RuntimeException("SETQ requiere exactamente dos argumentos");
                    }
                    if (!(expressions.get(1) instanceof LispSymbol)) {
                        throw new RuntimeException("El primer argumento de SETQ debe ser un símbolo");
                    }
                    String varName = ((LispSymbol) expressions.get(1)).getName();
                    Object value = expressions.get(2).evaluate(env);
                    env.define(varName, value);
                    return value;

                case "defun":
                    if (expressions.size() < 4) {
                        throw new RuntimeException("DEFUN requiere al menos tres argumentos");
                    }
                    if (!(expressions.get(1) instanceof LispSymbol)) {
                        throw new RuntimeException("El nombre de la función debe ser un símbolo");
                    }
                    String funcName = ((LispSymbol) expressions.get(1)).getName();
                    if (!(expressions.get(2) instanceof LispList)) {
                        throw new RuntimeException("La lista de parámetros debe ser una lista");
                    }
                    List<LispExpression> paramList = ((LispList) expressions.get(2)).getExpressions();
                    List<String> paramNames = new ArrayList<>();
                    for (LispExpression param : paramList) {
                        if (!(param instanceof LispSymbol)) {
                            throw new RuntimeException("Los parámetros deben ser símbolos");
                        }
                        paramNames.add(((LispSymbol) param).getName());
                    }
                    List<LispExpression> body = expressions.subList(3, expressions.size());
                    LispFunction function = new LispFunction(paramNames, body, env);
                    env.define(funcName, function);
                    return funcName;

                case "cond":
                    for (int i = 1; i < expressions.size(); i++) {
                        LispExpression clause = expressions.get(i);
                        if (!(clause instanceof LispList)) {
                            throw new RuntimeException("Las cláusulas de COND deben ser listas");
                        }
                        List<LispExpression> condPair = ((LispList) clause).getExpressions();
                        if (condPair.isEmpty()) {
                            throw new RuntimeException("Las cláusulas de COND no pueden estar vacías");
                        }
                        Object condition = condPair.get(0).evaluate(env);
                        if (isTrue(condition)) {
                            if (condPair.size() == 1) {
                                return condition;
                            }
                            Object result = null;
                            for (int j = 1; j < condPair.size(); j++) {
                                result = condPair.get(j).evaluate(env);
                            }
                            return result;
                        }
                    }
                    return null; // Ninguna condición fue verdadera
            }
        }

        // Evaluar el primer elemento
        Object function = first.evaluate(env);

        // Preparar los argumentos
        List<LispExpression> args = expressions.subList(1, expressions.size());

        // Invocar la función
        if (function instanceof Callable) {
            return ((Callable) function).call(args, env);
        } else if (function instanceof String) {
            String functionName = (String) function;
            return LispBuiltins.call(functionName, args, env);
        } else {
            throw new RuntimeException("No se puede invocar: " + function);
        }
    }

    /**
     * Verifica si una condición es verdadera para efectos de evaluación en LISP.
     * 
     * @param condition Objeto a evaluar.
     * @return true si se considera verdadero, false si no.
     */
    private boolean isTrue(Object condition) {
        if (condition == null) return false;
        if (condition instanceof Boolean) return (Boolean) condition;
        if (condition instanceof Number) return ((Number) condition).doubleValue() != 0;
        return true;
    }

    /**
     * Devuelve la lista de expresiones contenidas.
     * 
     * @return Lista de expresiones LISP.
     */
    public List<LispExpression> getExpressions() {
        return expressions;
    }

    /**
     * Devuelve una representación en cadena de la lista.
     * 
     * @return Representación tipo LISP de la lista.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < expressions.size(); i++) {
            sb.append(expressions.get(i));
            if (i < expressions.size() - 1) {
                sb.append(" ");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
