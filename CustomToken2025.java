
/**
 * Descripción: clase que sirve como objeto de token con sus getters y comprobaciones para el token.
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 06/03/2025
 * @last_modified 10/03/2025
 */

public class CustomToken2025{
    private final CustomTokenType2025 tokenType;
    private final String tokenValue;

    public CustomToken2025(CustomTokenType2025 tokenType, String tokenValue){
        this.tokenType = tokenType;
        this.tokenValue = tokenValue;
    }

    public CustomTokenType2025 getTokenType(){
        return tokenType;
    }

    public String getTokenValue(){
        return tokenValue;
    }

    public boolean isNumber(){
        return tokenType == CustomTokenType2025.NUMBER;
    }

    public boolean isSymbol(){
        return tokenType == CustomTokenType2025.SYMBOL;
    }

    public boolean isParenthesis(){
        return tokenType == CustomTokenType2025.LEFT_PAREN || tokenType == CustomTokenType2025.RIGHT_PAREN;
    }

    @Override
    public String toString(){
        return "Token{" + "type=" + tokenType + ", value='" + tokenValue + "'}";
    }
}
