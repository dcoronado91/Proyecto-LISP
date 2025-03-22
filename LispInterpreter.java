import java.util.List;
import java.util.Scanner;

/**
 * Descripción: clase encargada de hacer tests para métodos de parseTokens y extractTokens en tokenizer.
 * 
 * Este intérprete básico de LISP permite ingresar expresiones LISP por consola,
 * tokenizarlas, analizarlas y evaluarlas en un entorno global.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 10/03/2025
 * @last_modified 19/03/2025
 */
public class LispInterpreter {

    /**
     * Método principal que ejecuta el intérprete LISP.
     * Inicializa el entorno global, define funciones y constantes básicas, 
     * y permite la evaluación de expresiones ingresadas por el usuario.
     * 
     * @param args argumentos de línea de comandos (no utilizados en este caso)
     */
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
