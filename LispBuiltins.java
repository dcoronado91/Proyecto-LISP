import java.util.List;

/**
 * Clase que implementa las funciones incorporadas del lenguaje LISP.
 * Provee operaciones básicas como suma, resta, multiplicación y división.
 *
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 16/03/2025
 * @last_modified 19/03/2025
 */
public class LispBuiltins {
    /**
     * Ejecuta una función incorporada según el nombre proporcionado.
     *
     * @param functionName El nombre de la función a ejecutar
     * @param args Los argumentos de la función
     * @param env El entorno de ejecución
     * @return El resultado de la operación
     * @throws RuntimeException Si la función no está definida
     */
    public static Object call(String functionName, List<LispExpression> args, Environment env) {
        switch (functionName) {
            case "+":
                double sum = 0;
                for (LispExpression arg : args) {
                    Object evaluated = arg.evaluate(env);
                    if (evaluated instanceof Double) {
                        sum += (Double) evaluated;
                    } else if (evaluated instanceof LispNumber) {
                        sum += ((LispNumber) evaluated).getValue();
                    } else {
                        throw new RuntimeException("Se esperaba un número para '+', pero se encontró: " + evaluated);
                    }
                }
                return sum;
            case "-":
                if (args.isEmpty()) {
                    throw new RuntimeException("Operación '-' requiere al menos un argumento.");
                }
                
                Object firstEval = args.get(0).evaluate(env);
                double result;
                
                if (firstEval instanceof Double) {
                    result = (Double) firstEval;
                } else if (firstEval instanceof LispNumber) {
                    result = ((LispNumber) firstEval).getValue();
                } else {
                    throw new RuntimeException("Se esperaba un número para '-', pero se encontró: " + firstEval);
                }
                
                // Si hay solo un argumento, devolvemos su negativo
                if (args.size() == 1) {
                    return -result;
                }
                
                // Si hay más de un argumento, restamos los demás
                for (int i = 1; i < args.size(); i++) {
                    Object eval = args.get(i).evaluate(env);
                    if (eval instanceof Double) {
                        result -= (Double) eval;
                    } else if (eval instanceof LispNumber) {
                        result -= ((LispNumber) eval).getValue();
                    } else {
                        throw new RuntimeException("Se esperaba un número para '-', pero se encontró: " + eval);
                    }
                }
                return result;
            case "*":
                double product = 1;
                for (LispExpression arg : args) {
                    Object evaluated = arg.evaluate(env);
                    if (evaluated instanceof Double) {
                        product *= (Double) evaluated;
                    } else if (evaluated instanceof LispNumber) {
                        product *= ((LispNumber) evaluated).getValue();
                    } else {
                        throw new RuntimeException("Se esperaba un número para '*', pero se encontró: " + evaluated);
                    }
                }
                return product;
            case "/":
                if (args.isEmpty()) {
                    throw new RuntimeException("Operación '/' requiere al menos un argumento.");
                }
                
                Object firstDivEval = args.get(0).evaluate(env);
                double divResult;
                
                if (firstDivEval instanceof Double) {
                    divResult = (Double) firstDivEval;
                } else if (firstDivEval instanceof LispNumber) {
                    divResult = ((LispNumber) firstDivEval).getValue();
                } else {
                    throw new RuntimeException("Se esperaba un número para '/', pero se encontró: " + firstDivEval);
                }
                
                // Si hay solo un argumento, devolvemos su inverso
                if (args.size() == 1) {
                    if (divResult == 0) {
                        throw new RuntimeException("División por cero.");
                    }
                    return 1 / divResult;
                }
                
                // Si hay más de un argumento, dividimos
                for (int i = 1; i < args.size(); i++) {
                    Object eval = args.get(i).evaluate(env);
                    double divisor;
                    
                    if (eval instanceof Double) {
                        divisor = (Double) eval;
                    } else if (eval instanceof LispNumber) {
                        divisor = ((LispNumber) eval).getValue();
                    } else {
                        throw new RuntimeException("Se esperaba un número para '/', pero se encontró: " + eval);
                    }
                    
                    if (divisor == 0) {
                        throw new RuntimeException("División por cero.");
                    }
                    
                    divResult /= divisor;
                }
                return divResult;
            default:
                throw new RuntimeException("Función no definida: " + functionName);
        }
    }
}

