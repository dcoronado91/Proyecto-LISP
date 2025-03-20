import java.util.*;

/**
 * Clase que realiza el análisis léxico de código LISP.
 * Convierte una cadena de texto en una lista de tokens.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 16/03/2025
 * @last_modified 19/03/2025
 */
public class Tokenizer {
    /**
     * Extrae tokens de una cadena de texto LISP.
     * 
     * @param input La cadena de texto a tokenizar
     * @return Lista de tokens extraídos
     */
    public static List<CustomToken2025> extractTokens(String input) {
        List<CustomToken2025> tokensList = new ArrayList<>();
        StringTokenizer tokenProcessor = new StringTokenizer(input, "() ", true);

        while (tokenProcessor.hasMoreTokens()) {
            String nextToken = tokenProcessor.nextToken().trim();
            if (!nextToken.isEmpty()) {
                CustomTokenType2025 tokenType = determineTokenType(nextToken);
                tokensList.add(new CustomToken2025(tokenType, nextToken));
            }
        }
        return tokensList;
    }

    /**
     * Determina el tipo de token basado en su contenido.
     * 
     * @param token La cadena de texto del token
     * @return El tipo de token correspondiente
     */
    private static CustomTokenType2025 determineTokenType(String token) {
        if (token.equals("(")) {
            return CustomTokenType2025.LEFT_PAREN;
        } else if (token.equals(")")) {
            return CustomTokenType2025.RIGHT_PAREN;
        } else if (isNumeric(token)) {
            return CustomTokenType2025.NUMBER;
        } else {
            return CustomTokenType2025.SYMBOL;
        }
    }

    /**
     * Verifica si una cadena representa un número válido.
     * 
     * @param str La cadena a verificar
     * @return true si es un número, false en caso contrario
     */
    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}