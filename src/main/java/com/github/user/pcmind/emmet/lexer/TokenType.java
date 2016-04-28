package com.github.user.pcmind.emmet.lexer;

/**
 * Created by honor on 27/04/2016.
 */
public enum TokenType {
    oPar('('),
    cPar(')'),
    eq('='),
    gt('>'),
    plus('+'),
    times('*'),
    oBracket('{'),
    cBracket('}'),
    oBrace('['),
    cBrace(']'),
    text('\0');

    private char c;

    TokenType(char c) {
        this.c = c;
    }
    public static TokenType getCharToken(final char c) {
        for (TokenType tokenType : values()) {
            if(tokenType.c == c) {
                return tokenType;
            }
        }
        return null;
    }
}
