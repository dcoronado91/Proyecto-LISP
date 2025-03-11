import java.util.HashMap;
import java.util.Map;

/**
 * Descripción: clase de Custom Token Type creada con HashMap.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 06/03/2025
 * @last_modified 10/03/2025
 */

public class CustomTokenType2025{
    private static final Map<String, CustomTokenType2025> TYPES = new HashMap<>();

    public static final CustomTokenType2025 LEFT_PAREN = register("LEFT_PAREN");
    public static final CustomTokenType2025 RIGHT_PAREN = register("RIGHT_PAREN");
    public static final CustomTokenType2025 SYMBOL = register("SYMBOL");
    public static final CustomTokenType2025 NUMBER = register("NUMBER");
    public static final CustomTokenType2025 STRING = register("STRING");
    public static final CustomTokenType2025 QUOTE = register("QUOTE");

    private final String name;

    private CustomTokenType2025(String name){
        this.name = name;
    }

    private static CustomTokenType2025 register(String name){
        CustomTokenType2025 type = new CustomTokenType2025(name);
        TYPES.put(name, type);
        return type;
    }

    public static CustomTokenType2025 getOrCreate(String token){
        return TYPES.computeIfAbsent(token, CustomTokenType2025::new);
    }

    @Override
    public String toString(){
        return name;
    }

    public static CustomTokenType2025 fromString(String token){
        if ("(".equals(token)) return LEFT_PAREN;
        if (")".equals(token)) return RIGHT_PAREN;
        if ("QUOTE".equalsIgnoreCase(token)) return QUOTE;
        if (token.matches("-?\\d+(\\.\\d+)?")) return NUMBER;
        if (token.startsWith("\"") && token.endsWith("\"")) return STRING;
        return SYMBOL;
    }
}
