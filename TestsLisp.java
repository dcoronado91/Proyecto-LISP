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
 
 public class TestsLisp {
 
     /**
      * Prueba que verifica si el parser convierte correctamente una cadena LISP
      * en una estructura de expresión. Se compara usando toString() para evitar
      * problemas de igualdad entre objetos personalizados.
      */
     @Test
     public void parseTokensTest() {
         List<CustomToken2025> tokens = Tokenizer.extractTokens("( + 1 ( * 2 3 ) )");
         Object result = LispParser.parseTokens(tokens);
 
         // Comparamos la representación en texto, no la estructura de objetos
         assertEquals("(+ 1.0 (* 2.0 3.0))", result.toString());
     }
 
     /**
      * Prueba que verifica si el tokenizer identifica correctamente los tokens
      * de una expresión LISP simple.
      */
     @Test
     public void extractTokensTest() {
         List<CustomToken2025> tokens = Tokenizer.extractTokens("( + 1 2 )");
 
         assertEquals(5, tokens.size());
         assertEquals(CustomTokenType2025.LEFT_PAREN, tokens.get(0).getTokenType());
         assertEquals(CustomTokenType2025.SYMBOL, tokens.get(1).getTokenType());
         assertEquals(CustomTokenType2025.NUMBER, tokens.get(2).getTokenType());
         assertEquals("1", tokens.get(2).getTokenValue());
     }
 }
 