/**
 * Interfaz que define el comportamiento básico de todas las expresiones LISP.
 * Todas las expresiones deben poder ser evaluadas en un entorno.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 16/03/2025
 * @last_modified 19/03/2025
 */
public interface LispExpression {
    /**
     * Evalúa la expresión en el entorno proporcionado.
     * 
     * @param env El entorno en el que se evalúa la expresión
     * @return El resultado de la evaluación
     */
    Object evaluate(Environment env);
}