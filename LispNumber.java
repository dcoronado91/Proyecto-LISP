/**
 * Representa un número en el intérprete LISP.
 * Es una expresión que se evalúa a sí misma.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 16/03/2025
 * @last_modified 19/03/2025
 */
public class LispNumber implements LispExpression {
    private final double value;

    /**
     * Constructor para crear un número LISP.
     * 
     * @param value El valor numérico
     */
    public LispNumber(double value) {
        this.value = value;
    }

    /**
     * Evalúa el número, que simplemente retorna su valor.
     * 
     * @param env El entorno (no utilizado para números)
     * @return El valor numérico
     */
    @Override
    public Object evaluate(Environment env) {
        return value;
    }

    /**
     * Obtiene el valor numérico.
     * 
     * @return El valor como double
     */
    public double getValue() {
        return value;
    }

    /**
     * Representación en texto del número.
     * 
     * @return El valor como cadena de texto
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}