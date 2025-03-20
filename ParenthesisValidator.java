/**
 * Validador de paréntesis para expresiones LISP.
 * Verifica que los paréntesis estén correctamente balanceados.
 *
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 16/03/2025
 * @last_modified 19/03/2025
 */
public class ParenthesisValidator {
    /**
     * Valida que los paréntesis en la expresión estén balanceados.
     *
     * @param expression La expresión a validar
     * @return true si los paréntesis están balanceados, false en caso contrario
     */
    public static boolean validate(String expression) {
        int count = 0;
        for (char c : expression.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }
        return count == 0;
    }
}