package com.github.user.pcmind.emmet.lexer;

/**
 * Created by honor on 27/04/2016.
 */
public class Token {
    private TokenType type;
    private String value;

    public Token(TokenType type, char value) {
        this(type, String.valueOf(value));
    }

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[" + type +
                "]='" + value + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        if (type != token.type) return false;
        return value != null ? value.equals(token.value) : token.value == null;

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
