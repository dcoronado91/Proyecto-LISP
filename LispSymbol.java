public class LispSymbol implements LispExpression {
    private final String name;

    public LispSymbol(String name) {
        this.name = name;
    }

    @Override
    public Object evaluate(Environment env) {
        return env.get(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
