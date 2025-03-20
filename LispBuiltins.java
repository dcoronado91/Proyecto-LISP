import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa las funciones integradas de LISP.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 16/03/2025
 * @last_modified 19/03/2025
 */
public class LispBuiltins {
    
    /**
     * Llama a una función incorporada por nombre.
     * 
     * @param name El nombre de la función
     * @param args Los argumentos de la función
     * @param env El entorno actual
     * @return El resultado de la función
     */
    public static Object call(String name, List<LispExpression> args, Environment env) {
        switch (name.toLowerCase()) {
            // Operaciones aritméticas
            case "+": return add(args, env);
            case "-": return subtract(args, env);
            case "*": return multiply(args, env);
            case "/": return divide(args, env);
            
            // Predicados
            case "atom": return isAtom(args, env);
            case "list": return isList(args, env);
            case "equal": return isEqual(args, env);
            case "=": return equals(args, env);
            case "<": return lessThan(args, env);
            case ">": return greaterThan(args, env);
            
            // Manipulación de variables
            case "setq": return setq(args, env);
            case "defun": return defun(args, env);
            
            // Evaluación especial
            case "quote": return quote(args, env);
            case "'": return quote(args, env);
            
            // Condicionales
            case "cond": return cond(args, env);
            
            default:
                throw new RuntimeException("Función desconocida: " + name);
        }
    }
    
    /**
     * Implementa la suma de números.
     */
    private static Object add(List<LispExpression> args, Environment env) {
        double result = 0;
        for (LispExpression arg : args) {
            Object value = arg.evaluate(env);
            if (value instanceof Number) {
                result += ((Number) value).doubleValue();
            } else {
                throw new RuntimeException("Se esperaba un número en la suma");
            }
        }
        return result;
    }
    
    /**
     * Implementa la resta de números.
     */
    private static Object subtract(List<LispExpression> args, Environment env) {
        if (args.isEmpty()) {
            throw new RuntimeException("La resta requiere al menos un argumento");
        }
        
        Object firstValue = args.get(0).evaluate(env);
        if (!(firstValue instanceof Number)) {
            throw new RuntimeException("Se esperaba un número en la resta");
        }
        
        double result = ((Number) firstValue).doubleValue();
        
        if (args.size() == 1) {
            return -result; // Negación si solo hay un argumento
        }
        
        for (int i = 1; i < args.size(); i++) {
            Object value = args.get(i).evaluate(env);
            if (value instanceof Number) {
                result -= ((Number) value).doubleValue();
            } else {
                throw new RuntimeException("Se esperaba un número en la resta");
            }
        }
        
        return result;
    }
    
    /**
     * Implementa la multiplicación de números.
     */
    private static Object multiply(List<LispExpression> args, Environment env) {
        double result = 1;
        for (LispExpression arg : args) {
            Object value = arg.evaluate(env);
            if (value instanceof Number) {
                result *= ((Number) value).doubleValue();
            } else {
                throw new RuntimeException("Se esperaba un número en la multiplicación");
            }
        }
        return result;
    }
    
    /**
     * Implementa la división de números.
     */
    private static Object divide(List<LispExpression> args, Environment env) {
        if (args.isEmpty()) {
            throw new RuntimeException("La división requiere al menos un argumento");
        }
        
        Object firstValue = args.get(0).evaluate(env);
        if (!(firstValue instanceof Number)) {
            throw new RuntimeException("Se esperaba un número en la división");
        }
        
        double result = ((Number) firstValue).doubleValue();
        
        if (args.size() == 1) {
            return 1 / result; // Recíproco si solo hay un argumento
        }
        
        for (int i = 1; i < args.size(); i++) {
            Object value = args.get(i).evaluate(env);
            if (value instanceof Number) {
                double divisor = ((Number) value).doubleValue();
                if (divisor == 0) {
                    throw new RuntimeException("División por cero");
                }
                result /= divisor;
            } else {
                throw new RuntimeException("Se esperaba un número en la división");
            }
        }
        
        return result;
    }
    
    /**
     * Implementa el predicado =.
     * Compara si dos valores son iguales.
     */
    private static Object equals(List<LispExpression> args, Environment env) {
        if (args.size() != 2) {
            throw new RuntimeException("= requiere exactamente dos argumentos");
        }
        
        Object val1 = args.get(0).evaluate(env);
        Object val2 = args.get(1).evaluate(env);
        
        if (val1 == null && val2 == null) return true;
        if (val1 == null || val2 == null) return false;
        
        if (val1 instanceof Number && val2 instanceof Number) {
            return ((Number) val1).doubleValue() == ((Number) val2).doubleValue();
        }
        
        return val1.equals(val2);
    }
    
    /**
     * Implementa el predicado ATOM.
     * Verifica si un objeto es un átomo (no es una lista).
     */
    private static Object isAtom(List<LispExpression> args, Environment env) {
        if (args.size() != 1) {
            throw new RuntimeException("ATOM requiere exactamente un argumento");
        }
        
        Object value = args.get(0).evaluate(env);
        return !(value instanceof LispList);
    }
    
    /**
     * Implementa el predicado LIST.
     * Verifica si un objeto es una lista.
     */
    private static Object isList(List<LispExpression> args, Environment env) {
        if (args.size() != 1) {
            throw new RuntimeException("LIST requiere exactamente un argumento");
        }
        
        Object value = args.get(0).evaluate(env);
        return value instanceof LispList;
    }
    
    /**
     * Implementa el predicado EQUAL.
     * Compara dos objetos para determinar si son iguales.
     */
    private static Object isEqual(List<LispExpression> args, Environment env) {
        if (args.size() != 2) {
            throw new RuntimeException("EQUAL requiere exactamente dos argumentos");
        }
        
        Object val1 = args.get(0).evaluate(env);
        Object val2 = args.get(1).evaluate(env);
        
        if (val1 == null && val2 == null) return true;
        if (val1 == null || val2 == null) return false;
        
        return val1.equals(val2);
    }
    
    /**
     * Implementa el predicado <.
     * Compara si el primer número es menor que el segundo.
     */
    private static Object lessThan(List<LispExpression> args, Environment env) {
        if (args.size() != 2) {
            throw new RuntimeException("< requiere exactamente dos argumentos");
        }
        
        Object val1 = args.get(0).evaluate(env);
        Object val2 = args.get(1).evaluate(env);
        
        if (val1 instanceof Number && val2 instanceof Number) {
            return ((Number) val1).doubleValue() < ((Number) val2).doubleValue();
        }
        
        throw new RuntimeException("< requiere argumentos numéricos");
    }
    
    /**
     * Implementa el predicado >.
     * Compara si el primer número es mayor que el segundo.
     */
    private static Object greaterThan(List<LispExpression> args, Environment env) {
        if (args.size() != 2) {
            throw new RuntimeException("> requiere exactamente dos argumentos");
        }
        
        Object val1 = args.get(0).evaluate(env);
        Object val2 = args.get(1).evaluate(env);
        
        if (val1 instanceof Number && val2 instanceof Number) {
            return ((Number) val1).doubleValue() > ((Number) val2).doubleValue();
        }
        
        throw new RuntimeException("> requiere argumentos numéricos");
    }
    
    /**
     * Implementa SETQ para asignar valores a variables.
     */
    private static Object setq(List<LispExpression> args, Environment env) {
        if (args.size() != 2) {
            throw new RuntimeException("SETQ requiere exactamente dos argumentos");
        }
        
        if (!(args.get(0) instanceof LispSymbol)) {
            throw new RuntimeException("El primer argumento de SETQ debe ser un símbolo");
        }
        
        String varName = ((LispSymbol) args.get(0)).getName();
        Object value = args.get(1).evaluate(env);
        
        env.define(varName, value);
        return value;
    }
    
    /**
     * Implementa DEFUN para definir funciones.
     */
    private static Object defun(List<LispExpression> args, Environment env) {
        if (args.size() < 3) {
            throw new RuntimeException("DEFUN requiere al menos tres argumentos");
        }
        
        if (!(args.get(0) instanceof LispSymbol)) {
            throw new RuntimeException("El nombre de la función debe ser un símbolo");
        }
        
        String functionName = ((LispSymbol) args.get(0)).getName();
        
        if (!(args.get(1) instanceof LispList)) {
            throw new RuntimeException("La lista de parámetros debe ser una lista");
        }
        
        List<LispExpression> paramList = ((LispList) args.get(1)).getExpressions();
        List<String> paramNames = new ArrayList<>();
        
        for (LispExpression param : paramList) {
            if (!(param instanceof LispSymbol)) {
                throw new RuntimeException("Los parámetros deben ser símbolos");
            }
            paramNames.add(((LispSymbol) param).getName());
        }
        
        List<LispExpression> body = args.subList(2, args.size());
        LispFunction function = new LispFunction(paramNames, body, env);
        
        env.define(functionName, function);
        return functionName;
    }
    
    /**
     * Implementa QUOTE para evitar la evaluación de una expresión.
     */
    private static Object quote(List<LispExpression> args, Environment env) {
        if (args.size() != 1) {
            throw new RuntimeException("QUOTE requiere exactamente un argumento");
        }
        
        return args.get(0); // No evaluar, simplemente devolver la expresión
    }
    
    /**
     * Implementa COND para evaluación condicional.
     */
    private static Object cond(List<LispExpression> args, Environment env) {
        for (LispExpression arg : args) {
            if (!(arg instanceof LispList)) {
                throw new RuntimeException("Los argumentos de COND deben ser listas");
            }
            
            List<LispExpression> clause = ((LispList) arg).getExpressions();
            if (clause.isEmpty()) {
                throw new RuntimeException("Las cláusulas de COND no pueden estar vacías");
            }
            
            Object condition = clause.get(0).evaluate(env);
            if (isTruthy(condition)) {
                if (clause.size() == 1) {
                    return condition;
                }
                
                Object result = null;
                for (int i = 1; i < clause.size(); i++) {
                    result = clause.get(i).evaluate(env);
                }
                return result;
            }
        }
        
        return null; // Si ninguna condición es verdadera
    }
    
    /**
     * Determina si un valor es considerado verdadero.
     */
    private static boolean isTruthy(Object value) {
        if (value == null) return false;
        if (value instanceof Boolean) return (Boolean) value;
        if (value instanceof Number) return ((Number) value).doubleValue() != 0;
        if (value instanceof String) return !((String) value).isEmpty();
        return true; // Cualquier otro valor es verdadero
    }
}