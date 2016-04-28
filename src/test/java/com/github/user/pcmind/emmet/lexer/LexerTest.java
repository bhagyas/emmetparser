package com.github.user.pcmind.emmet.lexer;

import com.google.common.truth.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assert_;


/**
 * Created by honor on 27/04/2016.
 */
public class LexerTest {
    @Test
    public void singleToken() throws Exception {
        Iterable<Token> aaa = Lexer.from("aaa");
        assert_().that(aaa).containsExactly(new Token(TokenType.text, "aaa"));
    }

    @Test
    public void nestedNode() throws Exception {
        Iterable<Token> aaa = Lexer.from("aaa>bbb");
        assert_().that(aaa).containsExactly(new Token(TokenType.text, "aaa"), new Token(TokenType.gt, ">"), new Token(TokenType.text, "bbb"));
    }
    @Test
    public void nestedWithParNode() throws Exception {
        Iterable<Token> aaa = Lexer.from("(aaa>bbb)");
        assert_().that(aaa).containsExactly(new Token(TokenType.oPar, "("), new Token(TokenType.text, "aaa"), new Token(TokenType.gt, ">"), new Token(TokenType.text, "bbb"), new Token(TokenType.cPar, ")"));
    }

    @Test
    public void nestedWithSubNode() throws Exception {
        Iterable<Token> aaa = Lexer.from("aaa{bbb}>ccc");
        assert_().that(aaa).containsExactly(
                new Token(TokenType.text, "aaa"),
                new Token(TokenType.oBracket, "{"),
                new Token(TokenType.text, "bbb"),
                new Token(TokenType.cBracket, "}"),
                new Token(TokenType.gt, ">"),
                new Token(TokenType.text, "ccc")
        );
    }

    @Test
    public void nestedWithAttributes() throws Exception {
        Iterable<Token> aaa = Lexer.from("aaa[bbb=ggg]>ccc");
        assert_().that(aaa).containsExactly(
                new Token(TokenType.text, "aaa"),
                new Token(TokenType.oBrace, "["),
                new Token(TokenType.text, "bbb"),
                new Token(TokenType.eq, "="),
                new Token(TokenType.text, "ggg"),
                new Token(TokenType.cBrace, "]"),
                new Token(TokenType.gt, ">"),
                new Token(TokenType.text, "ccc")
        );
    }

    @Test
    public void test1() throws Exception {
        Iterable<Token> aaa = Lexer.from("(aaa>bbb+ccc>ddd)");
        assert_().that(aaa).containsExactly(
                new Token(TokenType.oPar, "("),
                new Token(TokenType.text, "aaa"),
                new Token(TokenType.gt, ">"),
                new Token(TokenType.text, "bbb"),
                new Token(TokenType.plus, "+"),
                new Token(TokenType.text, "ccc"),
                new Token(TokenType.gt, ">"),
                new Token(TokenType.text, "ddd"),
                new Token(TokenType.cPar, ")")
                );
    }
}