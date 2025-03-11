import java.util.*;

//Clase que no está en la primera versión del UML.
/**
 * Descripción: clase encargada de hacer el parser para el Lisp.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 06/03/2025
 * @last_modified 10/03/2025
 */

public class LispParser{
    public static Object parseTokens(List<CustomToken2025> tokens){
        Stack<List<Object>> stack = new Stack<>();
        stack.push(new ArrayList<>());

        for (CustomToken2025 token : tokens){
            CustomTokenType2025 tokenType = token.getTokenType(); 

            if (tokenType == CustomTokenType2025.LEFT_PAREN){
                List<Object> newList = new ArrayList<>();
                stack.peek().add(newList);
                stack.push(newList);
            }else if (tokenType == CustomTokenType2025.RIGHT_PAREN){
                if (stack.size() > 1){
                    stack.pop();
                }else{
                    throw new RuntimeException("Los parentesis deben estar balanceados para continuar...");
                }
            }else{
                stack.peek().add(token.getTokenValue());
            }
        }

        if (stack.size() != 1){
            throw new RuntimeException("Los parentesis deben estar balanceados para continuar...");
        }

        return stack.pop();
    }
}
