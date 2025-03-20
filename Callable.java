import java.util.List;

/**
 * Interfaz funcional que define un objeto que puede ser llamado con argumentos.
 * Utilizada para representar funciones y procedimientos en el intérprete LISP.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 16/03/2025
 * @last_modified 19/03/2025
 */
@FunctionalInterface
public interface Callable {
    /**
     * Ejecuta esta función con los argumentos proporcionados.
     * 
     * @param arguments La lista de expresiones que sirven como argumentos
     * @param env El entorno en el que se evalúan los argumentos
     * @return El resultado de la ejecución
     */
    Object call(List<LispExpression> arguments, Environment env);
}