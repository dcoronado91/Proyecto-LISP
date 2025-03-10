//Clase que no aparece en primera versión del UML
/**
 * Clase que valida si los paréntesis en la expresión están balanceados.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 27/02/2025
 * @last_modified 03/03/2025
 */
public class ParenthesisValidator {
    public static boolean validate(String content) {
        int counter = 0;
        for (char character : content.toCharArray()) {
            if (character == '(') counter++;
            if (character == ')') counter--;
            if (counter < 0) return false;
        }
        return counter == 0;
    }
}
