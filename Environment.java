import java.util.HashMap;
import java.util.Map;

/**
 * Representa un entorno de ejecución con variables y funciones.
 */
public class Environment {
    private final Map<String, Object> variables;
    private final Environment parent;

    public Environment() {
        this(null);
    }

    public Environment(Environment parent) {
        this.variables = new HashMap<>();
        this.parent = parent;
    }

    public void define(String name, Object value) {
        variables.put(name, value);
    }

    public Object get(String name) {
        if (variables.containsKey(name)) {
            return variables.get(name);
        }
        if (parent != null) {
            return parent.get(name);
        }
        throw new RuntimeException("Variable no definida: " + name);
    }

    public void set(String name, Object value) {
        if (variables.containsKey(name) || parent == null) {
            variables.put(name, value);
        } else {
            parent.set(name, value);
        }
    }

    public Environment createChild() {
        return new Environment(this);
    }
}
