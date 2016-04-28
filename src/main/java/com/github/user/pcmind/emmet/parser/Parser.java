package com.github.user.pcmind.emmet.parser;

import com.github.user.pcmind.emmet.lexer.Token;
import com.github.user.pcmind.emmet.lexer.TokenType;
import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;

/**
 * Created by honor on 27/04/2016.
 */
public class Parser {
    public static ENode parse(Iterable<Token> tokens) {
        PeekingIterator<Token> iterator = Iterators.peekingIterator(tokens.iterator());
        return walk(iterator);
    }

    private static ENode walk(PeekingIterator<Token> iterator) {
        ENode node = walk2(iterator);
        if (iterator.hasNext() && iterator.peek().getType() == TokenType.plus) {
            ESibling sibling = new ESibling();
            sibling.add(node);
            while (iterator.hasNext() && iterator.peek().getType() == TokenType.plus) {
                iterator.next(); //consume +
                sibling.add(walk(iterator));
            }
            return sibling;
        }
        return node;
    }

    private static ENode walk2(PeekingIterator<Token> iterator) {
        if (iterator.hasNext()) {
            Token token = iterator.next();
            switch (token.getType()) {
                case gt:
                    return new ENestNode(walk(iterator));
                case oPar:
                    return walkGroup(iterator);
                case oBracket:
                    return walkContent(iterator);
                case oBrace:
                    return walkAttributes(iterator);
                case text:
                    return walkElement(iterator, token);
            }
            throw new ParseException("Illegal token" + token);
        }
        throw new ParseException("Illegal ending");
    }

    private static ENode walkElement(final PeekingIterator<Token> iterator, final Token token) {
        final EElem nodeElem = new EElem(token.getValue());
        while (iterator.hasNext()) {
            TokenType type = iterator.peek().getType();
            if (type == TokenType.oBrace || type == TokenType.oBracket || type == TokenType.gt) {
                nodeElem.getContent().add(walk(iterator));
                continue;
            }
            break;
        }
        return nodeElem;
    }

    private static ENode walkGroup(final PeekingIterator<Token> iterator) {
        final EGroup group = new EGroup(walk(iterator));
        if (!iterator.hasNext() || iterator.next().getType() != TokenType.cPar) {
            throw new ParseException("Incorrect parent close");
        }
        return group;
    }

    private static ENode walkAttributes(final PeekingIterator<Token> iterator) {
        //attributes
        final EAttributes attributes = new EAttributes();
        while (iterator.hasNext() && iterator.peek().getType() != TokenType.cBrace) {
            if (!iterator.hasNext() || iterator.peek().getType() != TokenType.text) {
                throw new ParseException("Incorrect attribute assignment");
            }
            Token attr = iterator.next();
            if (!iterator.hasNext() || iterator.next().getType() != TokenType.eq) {
                throw new ParseException("Incorrect attribute assignment");
            }
            if (!iterator.hasNext() || iterator.peek().getType() != TokenType.text) {
                throw new ParseException("Incorrect attribute assignment");
            }
            Token value = iterator.next();
            attributes.getContent().add(new EAttribute(attr.getValue(), value.getValue()));
        }
        if (!iterator.hasNext() || iterator.next().getType() != TokenType.cBrace) {
            throw new ParseException("Missing closing brace");
        }
        return attributes;
    }

    private static ENode walkContent(final PeekingIterator<Token> iterator) {
        if (!iterator.hasNext() || iterator.peek().getType() != TokenType.text) {
            throw new ParseException("Unexpected node content");
        }
        final EContent content = new EContent(iterator.next().getValue());
        if (!iterator.hasNext() || iterator.next().getType() != TokenType.cBracket) {
            throw new ParseException("Incorrect parent close");
        }
        return content;
    }
}
