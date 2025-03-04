/**
 * Analizador léxico (Lexer) para expresiones en LISP.
 * Este programa lee una expresión LISP desde la entrada estándar,
 * verifica si los paréntesis están correctamente balanceados
 * y extrae los tokens de la expresión.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 27/02/2025
 * @last_modified 03/03/2025
 */
import java.util.*;

public class LispLexer {
    /**
     * Método principal que ejecuta el lexer.
     * Solicita al usuario una expresión LISP, valida la estructura de paréntesis
     * y extrae los tokens de la expresión.
     * 
     * @param args Argumentos de línea de comandos (no utilizados).
     */
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
        
        if (validateParentheses(fullExpression)) {
            System.out.println("Expresión válida: paréntesis correctamente balanceados.");
        } else {
            System.out.println("Expresión inválida: desbalance en los paréntesis.");
        }
        
        List<String> extractedTokens = extractTokens(fullExpression);
        System.out.println("Tokens encontrados: " + extractedTokens);
    }

    /**
     * Valida si los paréntesis en la expresión están balanceados.
     * 
     * @param content Expresión a evaluar.
     * @return true si los paréntesis están balanceados, false en caso contrario.
     */
    private static boolean validateParentheses(String content) {
        int counter = 0;
        for (char character : content.toCharArray()) {
            if (character == '(') counter++;
            if (character == ')') counter--;
            if (counter < 0) return false;
        }
        return counter == 0;
    }

    /**
     * Extrae los tokens de la expresión LISP.
     * 
     * @param input Expresión de entrada.
     * @return Lista de tokens extraídos.
     */
    private static List<String> extractTokens(String input) {
        List<String> tokensList = new ArrayList<>();
        StringTokenizer tokenProcessor = new StringTokenizer(input, "() ", true);
        
        while (tokenProcessor.hasMoreTokens()) {
            String nextToken = tokenProcessor.nextToken().trim();
            if (!nextToken.isEmpty()) {
                tokensList.add(nextToken);
            }
        }
        return tokensList;
    }
}
