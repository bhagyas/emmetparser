package com.github.user.pcmind.emmet.parser;

import com.google.common.base.Joiner;

/**
 * Created by honor on 28/04/2016.
 */
public class ESibling extends ENodeImpl {
    public <T extends ENode> T add(T ast) {
        getContent().add(ast);
        return ast;
    }

    @Override
    public String toString() {
        return Joiner.on('+').join(getContent());
    }
}
