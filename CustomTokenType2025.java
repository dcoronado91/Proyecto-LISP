/**
 * Enum que representa los tipos de tokens utilizados en el intérprete LISP.
 * Cada token tiene un tipo específico que determina cómo se interpreta en el lenguaje.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 16/03/2025
 * @last_modified 19/03/2025
 */
public enum CustomTokenType2025 {
    LEFT_PAREN, RIGHT_PAREN, SYMBOL, NUMBER, STRING, QUOTE;

    /**
     * Convierte una cadena de texto en su tipo de token correspondiente.
     * 
     * @param token La cadena de texto a convertir
     * @return El tipo de token correspondiente
     */
    public static CustomTokenType2025 fromString(String token) {
        if ("(".equals(token)) return LEFT_PAREN;
        if (")".equals(token)) return RIGHT_PAREN;
        if ("QUOTE".equalsIgnoreCase(token)) return QUOTE;
        if (token.matches("-?\\d+(\\.\\d+)?")) return NUMBER;
        if (token.startsWith("\"") && token.endsWith("\"")) return STRING;
        return SYMBOL;
    }
}