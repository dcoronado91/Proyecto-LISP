import java.util.List;

@FunctionalInterface
public interface Callable {
    Object call(List<LispExpression> arguments, Environment env);
}
