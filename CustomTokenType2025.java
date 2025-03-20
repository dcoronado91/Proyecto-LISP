public enum CustomTokenType2025 {
    LEFT_PAREN, RIGHT_PAREN, SYMBOL, NUMBER, STRING, QUOTE;

    public static CustomTokenType2025 fromString(String token) {
        if ("(".equals(token)) return LEFT_PAREN;
        if (")".equals(token)) return RIGHT_PAREN;
        if ("QUOTE".equalsIgnoreCase(token)) return QUOTE;
        if (token.matches("-?\\d+(\\.\\d+)?")) return NUMBER;
        if (token.startsWith("\"") && token.endsWith("\"")) return STRING;
        return SYMBOL;
    }
}