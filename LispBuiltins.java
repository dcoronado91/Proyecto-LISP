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
                return args.stream()
                           .mapToDouble(arg -> ((LispNumber) arg.evaluate(env)).getValue())
                           .sum();
            case "-":
                return args.stream()
                           .mapToDouble(arg -> ((LispNumber) arg.evaluate(env)).getValue())
                           .reduce((a, b) -> a - b)
                           .orElseThrow(() -> new RuntimeException("Operación '-' requiere al menos un argumento."));
            case "*":
                return args.stream()
                           .mapToDouble(arg -> ((LispNumber) arg.evaluate(env)).getValue())
                           .reduce(1, (a, b) -> a * b);
            case "/":
                return args.stream()
                           .mapToDouble(arg -> ((LispNumber) arg.evaluate(env)).getValue())
                           .reduce((a, b) -> a / b)
                           .orElseThrow(() -> new RuntimeException("Operación '/' requiere al menos un argumento."));
            default:
                throw new RuntimeException("Función no definida: " + functionName);
        }
    }
}