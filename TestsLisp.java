/*
 * Descripción: clase encargada de hacer tests para métodos de parseTokens y extractTokens en tokenizer.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 10/03/2025
 * @last_modified 19/03/2025
 */

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Test;

public class TestsLisp{
    @Test
    public void parseTokensTest(){
        List<CustomToken2025> tokens = Tokenizer.extractTokens("( + 1 ( * 2 3 ) )");
        Object result = LispParser.parseTokens(tokens);

        assertEquals(List.of(List.of("+", "1", List.of("*", "2", "3"))), result);
    }

    @Test
    public void extractTokensTest(){
        List<CustomToken2025> tokens = Tokenizer.extractTokens("( + 1 2 )");

        assertEquals(5, tokens.size());
        assertEquals(CustomTokenType2025.LEFT_PAREN, tokens.get(0).getTokenType());
        assertEquals(CustomTokenType2025.SYMBOL, tokens.get(1).getTokenType());
        assertEquals(CustomTokenType2025.NUMBER, tokens.get(2).getTokenType());
        assertEquals("1", tokens.get(2).getTokenValue());
    }

}
