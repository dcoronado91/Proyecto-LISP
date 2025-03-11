import java.util.*;
//Clase de UML llamada CustomTokenizer2025
/**
 * Clase encargada de extraer los tokens de una expresión LISP.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 27/02/2025
 * @last_modified 10/03/2025
 */
public class Tokenizer {
    public static List<CustomToken2025> extractTokens(String input){ //Cambio de List<String> a List<CustomToken2025>
        List<CustomToken2025> tokensList = new ArrayList<>();
        StringTokenizer tokenProcessor = new StringTokenizer(input, "() ", true);
        
        while (tokenProcessor.hasMoreTokens()) {
            String nextToken = tokenProcessor.nextToken().trim();
            if (!nextToken.isEmpty()) {
                tokensList.add(new CustomToken2025(CustomTokenType2025.fromString(nextToken), nextToken)); //Cambios para implementación del Parser
            }
        }
        return tokensList;
    }
}
