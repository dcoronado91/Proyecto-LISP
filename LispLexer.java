import java.util.*;
//Nombre de la clase en el UML: CustomListInterpreter2025
/**
 * Clase principal que ejecuta el lexer para expresiones LISP.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 27/02/2025
 * @last_modified 10/03/2025
 */
public class LispLexer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la expresión LISP:");
        String input = scanner.nextLine().trim(); //Entrada de texto para el usuario
        scanner.close();

        if (!ParenthesisValidator.validate(input)) { //Cambio de fullExpression a input
            System.out.println("Expresión inválida: desbalance en los paréntesis.");
            return;
        }
        
        List<CustomToken2025> extractedTokens = Tokenizer.extractTokens(input);//Cambio de List<String> a List<CustomToken2025>
        Object parsedExpression = LispParser.parseTokens(extractedTokens); //Proceso del parser de Lisp
        
        System.out.println("Tokens encontrados: " + parsedExpression); //Resultados
    }
}
