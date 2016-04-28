package com.github.user.pcmind.emmet.parser;

/**
 * Created by honor on 28/04/2016.
 */
public class ENestNode implements ENode {
    private ENode content;

    public ENestNode(ENode content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return ">" + content;
    }
}
