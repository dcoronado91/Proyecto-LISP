import java.util.List;

/**
 * Evaluador de expresiones LISP que implementa la lógica recursiva de interpretación.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 10/03/2025
 * @last_modified 19/03/2025
 */
public class CustomEvaluator2025 {
    
    /**
     * Evalúa una expresión LISP en el contexto de un entorno dado.
     * 
     * @param expr La expresión a evaluar
     * @param env El entorno donde evaluar la expresión
     * @return El resultado de la evaluación
     */
    public static Object evaluate(LispExpression expr, Environment env) {
        if (expr instanceof LispNumber) {
            return ((LispNumber) expr).getValue();
        } else if (expr instanceof LispSymbol) {
            String symbolName = ((LispSymbol) expr).getName();
            return env.get(symbolName);
        } else if (expr instanceof LispList) {
            return evaluateList((LispList) expr, env);
        }
        
        throw new RuntimeException("Tipo de expresión desconocido: " + expr.getClass().getName());
    }
    
    /**
     * Evalúa una lista LISP como una llamada a función o una forma especial.
     * 
     * @param list La lista a evaluar
     * @param env El entorno donde evaluar la lista
     * @return El resultado de la evaluación
     */
    private static Object evaluateList(LispList list, Environment env) {
        List<LispExpression> expressions = list.getExpressions();
        
        if (expressions.isEmpty()) {
            return null; // Lista vacía evalúa a nil
        }
        
        LispExpression first = expressions.get(0);
        
        // Manejar formas especiales primero
        if (first instanceof LispSymbol) {
            String name = ((LispSymbol) first).getName();
            
            // Forma especial 'define'
            if ("define".equalsIgnoreCase(name)) {
                if (expressions.size() < 3) {
                    throw new RuntimeException("'define' requiere al menos 2 argumentos");
                }
                
                if (expressions.get(1) instanceof LispSymbol) {
                    String varName = ((LispSymbol) expressions.get(1)).getName();
                    Object varValue = evaluate(expressions.get(2), env);
                    env.define(varName, varValue);
                    return varValue;
                } else {
                    throw new RuntimeException("Primer argumento de 'define' debe ser un símbolo");
                }
            }
            
            // Forma especial 'if'
            if ("if".equalsIgnoreCase(name)) {
                if (expressions.size() != 4) {
                    throw new RuntimeException("'if' requiere exactamente 3 argumentos");
                }
                
                Object condition = evaluate(expressions.get(1), env);
                boolean conditionResult = isTruthy(condition);
                
                if (conditionResult) {
                    return evaluate(expressions.get(2), env);
                } else {
                    return evaluate(expressions.get(3), env);
                }
            }
            
            // Forma especial 'lambda'
            if ("lambda".equalsIgnoreCase(name) || "fn".equalsIgnoreCase(name)) {
                if (expressions.size() < 3) {
                    throw new RuntimeException("'lambda' requiere al menos 2 argumentos");
                }
                
                LispExpression paramsExpr = expressions.get(1);
                if (!(paramsExpr instanceof LispList)) {
                    throw new RuntimeException("El primer argumento de 'lambda' debe ser una lista de parámetros");
                }
                
                List<LispExpression> paramsList = ((LispList) paramsExpr).getExpressions();
                List<String> paramNames = new java.util.ArrayList<>();
                
                for (LispExpression param : paramsList) {
                    if (!(param instanceof LispSymbol)) {
                        throw new RuntimeException("Los parámetros de 'lambda' deben ser símbolos");
                    }
                    paramNames.add(((LispSymbol) param).getName());
                }
                
                List<LispExpression> body = expressions.subList(2, expressions.size());
                return new LispFunction(paramNames, body, env);
            }
        }
        
        // Evaluar la primera expresión (que debería devolver algo invocable)
        Object function = evaluate(first, env);
        
        // Preparar los argumentos (no evaluados aún)
        List<LispExpression> args = expressions.subList(1, expressions.size());
        
        // Invocar la función con los argumentos
        if (function instanceof Callable) {
            return ((Callable) function).call(args, env);
        } else if (function instanceof String && 
                  (LispBuiltins.class.getSimpleName().equals(function) || 
                   "LispBuiltins".equals(function))) {
            // Intentar llamar a una función incorporada si la primera expresión evalúa a "LispBuiltins"
            String functionName = ((LispSymbol) first).getName();
            return LispBuiltins.call(functionName, args, env);
        } else {
            throw new RuntimeException("No se puede invocar: " + function);
        }
    }
    
    /**
     * Determina si un valor es considerado verdadero en el contexto LISP.
     * 
     * @param value El valor a comprobar
     * @return true si el valor es considerado verdadero, false en caso contrario
     */
    private static boolean isTruthy(Object value) {
        if (value == null) return false;
        if (value instanceof Boolean) return (Boolean) value;
        if (value instanceof Number) return ((Number) value).doubleValue() != 0;
        if (value instanceof String) return !((String) value).isEmpty();
        return true; // Todo lo demás es verdadero
    }
}