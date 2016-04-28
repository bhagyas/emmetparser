package com.github.user.pcmind.emmet.lexer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by honor on 27/04/2016.
 */
public class Lexer {
    public static Iterable<Token> from(CharSequence value) {
        int current = 0;
        final List<Token> content = new ArrayList<Token>();
        while (current < value.length()) {
            char c = value.charAt(current);
            TokenType charToken = TokenType.getCharToken(c);
            if (charToken != null) {
                current++;
                content.add(new Token(charToken, c));
                continue;
            }
            if (c == ' ' || c == '\t' || c == '\r' || c == '\n') {
                current++;
                continue; //skip space
            }
            StringBuilder stringBuilder = new StringBuilder();
            while (c != ' ' && c != '\t' && TokenType.getCharToken(c) == null) {
                stringBuilder.append(c);
                current++;
                if (current >= value.length()) {
                    break;
                }
                c = value.charAt(current);
            }
            content.add(new Token(TokenType.text, stringBuilder.toString()));
        }
        return content;
    }

}
