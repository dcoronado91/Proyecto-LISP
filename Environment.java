import java.util.HashMap;
import java.util.Map;

/**
 * Representa un entorno de ejecución con variables y funciones.
 * Mantiene un mapa de variables y una referencia a un entorno padre
 * para soportar ámbitos anidados.
 * 
 * @author Derek Coronado, Emilio Chen, Tiffany Salazar
 * @since 16/03/2025
 * @last_modified 19/03/2025
 */
public class Environment {
    private final Map<String, Object> variables;
    private final Environment parent;

    /**
     * Constructor por defecto que crea un entorno global sin padre.
     */
    public Environment() {
        this(null);
    }

    /**
     * Constructor que crea un entorno con un padre específico.
     * 
     * @param parent El entorno padre
     */
    public Environment(Environment parent) {
        this.variables = new HashMap<>();
        this.parent = parent;
    }

    /**
     * Define una nueva variable en este entorno.
     * 
     * @param name El nombre de la variable
     * @param value El valor a asignar
     */
    public void define(String name, Object value) {
        variables.put(name, value);
    }

    /**
     * Obtiene el valor de una variable desde este entorno o sus padres.
     * 
     * @param name El nombre de la variable a buscar
     * @return El valor de la variable
     * @throws RuntimeException Si la variable no está definida
     */
    public Object get(String name) {
        if (variables.containsKey(name)) {
            return variables.get(name);
        }
        if (parent != null) {
            return parent.get(name);
        }
        throw new RuntimeException("Variable no definida: " + name);
    }

    /**
     * Establece un nuevo valor para una variable existente.
     * 
     * @param name El nombre de la variable
     * @param value El nuevo valor
     */
    public void set(String name, Object value) {
        if (variables.containsKey(name) || parent == null) {
            variables.put(name, value);
        } else {
            parent.set(name, value);
        }
    }

    /**
     * Crea un nuevo entorno hijo que hereda variables de este entorno.
     * 
     * @return Un nuevo entorno hijo
     */
    public Environment createChild() {
        return new Environment(this);
    }
}