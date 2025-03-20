import java.util.*;

/**
 * Clase que realiza el análisis sintáctico de tokens LISP y construye
 * el árbol de expresiones correspondiente.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 16/03/2025
 * @last_modified 19/03/2025
 */
public class LispParser {
    /**
     * Analiza una lista de tokens y construye el árbol de expresiones LISP.
     * 
     * @param tokens Lista de tokens a analizar
     * @return Una expresión LISP que representa el árbol sintáctico
     * @throws RuntimeException Si hay errores de sintaxis
     */
    public static LispExpression parseTokens(List<CustomToken2025> tokens) {
        Stack<List<LispExpression>> stack = new Stack<>();
        stack.push(new ArrayList<>()); // Pila de listas anidadas

        for (CustomToken2025 token : tokens) {
            CustomTokenType2025 tokenType = token.getTokenType();
            String value = token.getTokenValue();

            switch (tokenType) {
                case LEFT_PAREN:
                    List<LispExpression> newList = new ArrayList<>();
                    stack.peek().add(new LispList(newList)); // Agregar lista nueva a la actual
                    stack.push(newList); // Hacer esta la lista actual
                    break;
                case RIGHT_PAREN:
                    if (stack.size() > 1) {
                        stack.pop(); // Cierra la lista actual
                    } else {
                        throw new RuntimeException("Paréntesis desbalanceados.");
                    }
                    break;
                case NUMBER:
                    stack.peek().add(new LispNumber(Double.parseDouble(value)));
                    break;
                case SYMBOL:
                    stack.peek().add(new LispSymbol(value)); // 🔥 Asegura que `+` es un `LispSymbol`
                    break;
                default:
                    throw new RuntimeException("Tipo de token desconocido: " + tokenType);
            }
        }

        if (stack.size() != 1) {
            throw new RuntimeException("Paréntesis desbalanceados.");
        }

        // Tomar la lista final y asegurarse de que no está vacía
        List<LispExpression> finalList = stack.pop();
        if (finalList.isEmpty()) {
            throw new IllegalArgumentException("Error de parsing: la lista está vacía.");
        }

        return new LispList(finalList); // Devolver la lista principal correctamente
    }
}