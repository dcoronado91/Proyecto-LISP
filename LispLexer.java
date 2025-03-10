import java.util.*;
//Nombre de la clase en el UML: CustomListInterpreter2025
/**
 * Clase principal que ejecuta el lexer para expresiones LISP.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 27/02/2025
 * @last_modified 03/03/2025
 */
public class LispLexer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la expresión LISP:");
        
        StringBuilder input = new StringBuilder();
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            if (currentLine.isEmpty()) break;
            input.append(currentLine).append(" ");
        }
        scanner.close();
        
        String fullExpression = input.toString().trim();

        if (ParenthesisValidator.validate(fullExpression)) {
            System.out.println("Expresión válida: paréntesis correctamente balanceados.");
        } else {
            System.out.println("Expresión inválida: desbalance en los paréntesis.");
        }
        
        List<String> extractedTokens = Tokenizer.extractTokens(fullExpression);
        System.out.println("Tokens encontrados: " + extractedTokens);
    }
}
