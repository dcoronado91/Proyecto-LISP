/**
 * Representa un token en el intérprete LISP.
 * Contiene un tipo y un valor.
 *
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 16/03/2025
 * @last_modified 19/03/2025
 */
public class CustomToken2025 {
    private final CustomTokenType2025 tokenType;
    private final String tokenValue;
    
    public CustomToken2025(CustomTokenType2025 tokenType, String tokenValue) {
        this.tokenType = tokenType;
        this.tokenValue = tokenValue;
    }
    
    public CustomTokenType2025 getTokenType() {
        return tokenType;
    }
    
    public String getTokenValue() {
        return tokenValue;
    }
    
    @Override
    public String toString() {
        return tokenType + ":" + tokenValue;
    }
}
