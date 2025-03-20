import java.util.List;

public class LispBuiltins {
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
