package com.github.user.pcmind.emmet.parser;

import com.google.common.base.Joiner;

/**
 * Created by honor on 28/04/2016.
 */
public class EAttributes extends ENodeImpl {
    @Override
    public String toString() {
        return "[" + Joiner.on(' ').join(getContent()) + "]";
    }
}
