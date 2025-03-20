import java.util.*;

public class Tokenizer {
    public static List<CustomToken2025> extractTokens(String input) {
        List<CustomToken2025> tokensList = new ArrayList<>();
        StringTokenizer tokenProcessor = new StringTokenizer(input, "() ", true);

        while (tokenProcessor.hasMoreTokens()) {
            String nextToken = tokenProcessor.nextToken().trim();
            if (!nextToken.isEmpty()) {
                CustomTokenType2025 tokenType = determineTokenType(nextToken);
                tokensList.add(new CustomToken2025(tokenType, nextToken));
            }
        }
        return tokensList;
    }

    private static CustomTokenType2025 determineTokenType(String token) {
        if (token.equals("(")) {
            return CustomTokenType2025.LEFT_PAREN;
        } else if (token.equals(")")) {
            return CustomTokenType2025.RIGHT_PAREN;
        } else if (isNumeric(token)) {
            return CustomTokenType2025.NUMBER;
        } else {
            return CustomTokenType2025.SYMBOL;
        }
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
