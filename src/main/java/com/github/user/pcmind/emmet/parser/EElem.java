package com.github.user.pcmind.emmet.parser;

import com.google.common.base.Joiner;

/**
 * Created by honor on 28/04/2016.
 */
public class EElem extends ENodeImpl {
    private String name;

    public EElem(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + Joiner.on("").join(getContent());
    }
}
