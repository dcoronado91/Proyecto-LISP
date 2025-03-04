import java.util.*;

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
        
        if (validateParentheses(fullExpression)) {
            System.out.println("Expresión válida: paréntesis correctamente balanceados.");
        } else {
            System.out.println("Expresión inválida: desbalance en los paréntesis.");
        }
        
        List<String> extractedTokens = extractTokens(fullExpression);
        System.out.println("Tokens encontrados: " + extractedTokens);
    }

    private static boolean validateParentheses(String content) {
        int counter = 0;
        for (char character : content.toCharArray()) {
            if (character == '(') counter++;
            if (character == ')') counter--;
            if (counter < 0) return false;
        }
        return counter == 0;
    }

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
