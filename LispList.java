import java.util.List;

/**
 * Representa una lista de expresiones LISP.
 * Una lista puede ser una expresión compuesta o una llamada a función
 * donde el primer elemento es el operador y los demás son argumentos.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 16/03/2025
 * @last_modified 19/03/2025
 */
public class LispList implements LispExpression {
    private final List<LispExpression> expressions;

    /**
     * Constructor para crear una lista LISP.
     * 
     * @param expressions Lista de expresiones que componen esta lista
     * @throws IllegalArgumentException Si la lista es nula o vacía
     */
    public LispList(List<LispExpression> expressions) {
        if (expressions == null || expressions.isEmpty()) {
            throw new IllegalArgumentException("La lista de expresiones no puede ser nula o vacía.");
        }
        this.expressions = expressions;
    }

    /**
     * Evalúa la lista como una expresión LISP.
     * Si el primer elemento es un símbolo, lo interpreta como una llamada a función.
     * 
     * @param env El entorno en el que se evalúa la expresión
     * @return El resultado de la evaluación
     */
    @Override
    public Object evaluate(Environment env) {
        if (expressions.isEmpty()) {
            throw new RuntimeException("Expresión vacía.");
        }

        LispExpression first = expressions.get(0);

        if (!(first instanceof LispSymbol)) {
            throw new RuntimeException("Se esperaba un símbolo como primer elemento de la lista, pero se encontró: " + first);
        }

        String functionName = ((LispSymbol) first).getName();
        List<LispExpression> args = expressions.subList(1, expressions.size());

        return LispBuiltins.call(functionName, args, env);
    }

    /**
     * Obtiene la lista de expresiones contenidas.
     * 
     * @return Lista de expresiones LISP
     */
    public List<LispExpression> getExpressions() {
        return expressions;
    }

    /**
     * Representación en texto de la lista de expresiones.
     * 
     * @return Representación como cadena de texto
     */
    @Override
    public String toString() {
        return expressions.toString();
    }
}