import java.util.*;

public class LispParser {
    public static LispExpression parseTokens(List<CustomToken2025> tokens) {
        if (tokens == null || tokens.isEmpty()) {
            throw new IllegalArgumentException("La lista de tokens no puede ser nula o vacía.");
        }
        
        Stack<List<LispExpression>> stack = new Stack<>();
        stack.push(new ArrayList<>());
        
        for (int i = 0; i < tokens.size(); i++) {
            CustomToken2025 token = tokens.get(i);
            CustomTokenType2025 tokenType = token.getTokenType();
            String value = token.getTokenValue();
            
            switch (tokenType) {
                case LEFT_PAREN:
                    stack.push(new ArrayList<>());
                    break;
                case RIGHT_PAREN:
                    if (stack.size() <= 1) {
                        throw new RuntimeException("Paréntesis desbalanceados.");
                    }
                    List<LispExpression> completedList = stack.pop();
                    if (completedList.isEmpty()) {
                        stack.peek().add(new LispSymbol("nil"));
                    } else {
                        stack.peek().add(new LispList(completedList));
                    }
                    break;
                case QUOTE:
                    // Manejar la comilla simple
                    if (i + 1 < tokens.size()) {
                        CustomToken2025 nextToken = tokens.get(i + 1);
                        List<LispExpression> quoteList = new ArrayList<>();
                        quoteList.add(new LispSymbol("quote"));
                        
                        if (nextToken.getTokenType() == CustomTokenType2025.LEFT_PAREN) {
                            // Encontrar el paréntesis de cierre correspondiente
                            int parenthesisCount = 1;
                            int j = i + 2;
                            while (j < tokens.size() && parenthesisCount > 0) {
                                if (tokens.get(j).getTokenType() == CustomTokenType2025.LEFT_PAREN) {
                                    parenthesisCount++;
                                } else if (tokens.get(j).getTokenType() == CustomTokenType2025.RIGHT_PAREN) {
                                    parenthesisCount--;
                                }
                                j++;
                            }
                            
                            // Analizar la sublista
                            List<CustomToken2025> subTokens = tokens.subList(i + 1, j);
                            LispExpression subExpr = parseTokens(subTokens);
                            quoteList.add(subExpr);
                            i = j - 1; // Saltar los tokens procesados
                        } else {
                            // Citar un símbolo o número
                            if (nextToken.getTokenType() == CustomTokenType2025.NUMBER) {
                                quoteList.add(new LispNumber(Double.parseDouble(nextToken.getTokenValue())));
                            } else {
                                quoteList.add(new LispSymbol(nextToken.getTokenValue()));
                            }
                            i++; // Saltar el token procesado
                        }
                        
                        stack.peek().add(new LispList(quoteList));
                    } else {
                        throw new RuntimeException("Se esperaba una expresión después de '");
                    }
                    break;
                case NUMBER:
                    stack.peek().add(new LispNumber(Double.parseDouble(value)));
                    break;
                case SYMBOL:
                    stack.peek().add(new LispSymbol(value));
                    break;
                default:
                    throw new RuntimeException("Tipo de token desconocido: " + tokenType);
            }
        }
        
        if (stack.size() != 1) {
            throw new RuntimeException("Paréntesis desbalanceados.");
        }
        
        List<LispExpression> finalList = stack.pop();
        
        if (finalList.isEmpty()) {
            throw new IllegalArgumentException("Error de parsing: la lista está vacía.");
        } else if (finalList.size() == 1) {
            return finalList.get(0);
        } else {
            return new LispList(finalList);
        }
    }
}