import java.util.List;
import java.util.Scanner;
import java.util.Scanner;

public class LispInterpreter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Environment globalEnv = new Environment();

        while (true) {
            System.out.print("Lisp> ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) break;

            List<CustomToken2025> tokens = Tokenizer.extractTokens(input);
            LispExpression parsedExpression = LispParser.parseTokens(tokens);
            Object result = parsedExpression.evaluate(globalEnv);
            System.out.println("Resultado: " + result);
        }
    }
}
