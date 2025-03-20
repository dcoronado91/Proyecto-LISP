/**
 * Representa un símbolo en el intérprete LISP.
 * Un símbolo se evalúa buscando su valor en el entorno.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 16/03/2025
 * @last_modified 19/03/2025
 */
public class LispSymbol implements LispExpression {
    private final String name;

    /**
     * Constructor para crear un símbolo LISP.
     * 
     * @param name El nombre del símbolo
     */
    public LispSymbol(String name) {
        this.name = name;
    }

    /**
     * Evalúa el símbolo buscando su valor en el entorno.
     * 
     * @param env El entorno donde buscar el valor del símbolo
     * @return El valor asociado al símbolo
     */
    @Override
    public Object evaluate(Environment env) {
        return env.get(name);
    }

    /**
     * Obtiene el nombre del símbolo.
     * 
     * @return El nombre como cadena de texto
     */
    public String getName() {
        return name;
    }

    /**
     * Representación en texto del símbolo.
     * 
     * @return El nombre del símbolo
     */
    @Override
    public String toString() {
        return name;
    }
}