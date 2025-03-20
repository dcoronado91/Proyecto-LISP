import java.util.*;

public class LispParser {
    public static LispExpression parseTokens(List<CustomToken2025> tokens) {
        if (tokens == null || tokens.isEmpty()) {
            throw new IllegalArgumentException("La lista de tokens no puede ser nula o vacía.");
        }
        
        Stack<List<LispExpression>> stack = new Stack<>();
        stack.push(new ArrayList<>());
        
        for (CustomToken2025 token : tokens) {
            CustomTokenType2025 tokenType = token.getTokenType();
            String value = token.getTokenValue();
            
            switch (tokenType) {
                case LEFT_PAREN:
                    // Solo agregamos una nueva lista a la pila
                    stack.push(new ArrayList<>());
                    break;
                case RIGHT_PAREN:
                    if (stack.size() <= 1) {
                        throw new RuntimeException("Paréntesis desbalanceados.");
                    }
                    // Sacamos la lista completada
                    List<LispExpression> completedList = stack.pop();
                    // Si está vacía, usamos un símbolo nil
                    if (completedList.isEmpty()) {
                        stack.peek().add(new LispSymbol("nil"));
                    } else {
                        // Si no está vacía, creamos una LispList
                        stack.peek().add(new LispList(completedList));
                    }
                    break;
                case NUMBER:
                    stack.peek().add(new LispNumber(Double.parseDouble(value)));
                    break;
                case SYMBOL:
                    stack.peek().add(new LispSymbol(value));
                    break;
                default:
                    throw new RuntimeException("Tipo de token desconocido: " + tokenType);
            }
        }
        
        if (stack.size() != 1) {
            throw new RuntimeException("Paréntesis desbalanceados.");
        }
        
        List<LispExpression> finalList = stack.pop();
        
        if (finalList.isEmpty()) {
            throw new IllegalArgumentException("Error de parsing: la lista está vacía.");
        } else if (finalList.size() == 1) {
            return finalList.get(0);
        } else {
            // Si hay múltiples expresiones en el nivel superior, las envolvemos en una nueva lista
            return new LispList(finalList);
        }
    }
}