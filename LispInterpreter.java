import java.util.List;
import java.util.Scanner;

public class LispInterpreter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Environment globalEnv = new Environment();

        // Inicializar el entorno con valores básicos
        globalEnv.define("T", true);
        globalEnv.define("NIL", null);
        globalEnv.define("t", true);  // Añadir versión en minúscula
        
        // Definir operadores como funciones básicas
        globalEnv.define("+", "+");
        globalEnv.define("-", "-");
        globalEnv.define("*", "*");
        globalEnv.define("/", "/");
        globalEnv.define("<", "<");
        globalEnv.define(">", ">");
        globalEnv.define("=", "=");
        
        // Agregar las funciones atom y list
        globalEnv.define("atom", "atom");
        globalEnv.define("list", "list");
        globalEnv.define("listp", "listp");
        
        // Imprimir mensaje de bienvenida
        System.out.println("Intérprete LISP v1.0");
        System.out.println("Ingrese 'exit' para salir");
        
        while (true) {
            System.out.print("Lisp> ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) break;
            try {
                List<CustomToken2025> tokens = Tokenizer.extractTokens(input);
                LispExpression parsedExpression = LispParser.parseTokens(tokens);
                Object result = parsedExpression.evaluate(globalEnv);
                System.out.println("=> " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}