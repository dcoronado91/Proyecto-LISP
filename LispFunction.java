import java.util.List;

/**
 * Implementa una función LISP definida por el usuario.
 * Almacena parámetros, cuerpo y entorno de clausura.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 16/03/2025
 * @last_modified 19/03/2025
 */
public class LispFunction implements Callable {
    private final List<String> parameters;
    private final List<LispExpression> body;
    private final Environment closure;

    /**
     * Constructor para crear una función LISP.
     * 
     * @param parameters Lista de nombres de parámetros
     * @param body Lista de expresiones que forman el cuerpo de la función
     * @param closure El entorno en el que se creó la función
     */
    public LispFunction(List<String> parameters, List<LispExpression> body, Environment closure) {
        this.parameters = parameters;
        this.body = body;
        this.closure = closure;
    }

    /**
     * Ejecuta la función con los argumentos proporcionados.
     * 
     * @param arguments Lista de expresiones como argumentos
     * @param env El entorno desde el que se llama la función
     * @return El resultado de la última expresión evaluada
     */
    @Override
    public Object call(List<LispExpression> arguments, Environment env) {
        if (arguments.size() != parameters.size()) {
            throw new RuntimeException("Número incorrecto de argumentos.");
        }

        Environment localEnv = closure.createChild();
        for (int i = 0; i < parameters.size(); i++) {
            localEnv.define(parameters.get(i), arguments.get(i).evaluate(env));
        }

        Object result = null;
        for (LispExpression expr : body) {
            result = expr.evaluate(localEnv);
        }
        return result;
    }
}