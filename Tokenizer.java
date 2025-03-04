import java.util.*;

/**
 * Clase encargada de extraer los tokens de una expresión LISP.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 27/02/2025
 * @last_modified 03/03/2025
 */
public class Tokenizer {
    public static List<String> extractTokens(String input) {
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
