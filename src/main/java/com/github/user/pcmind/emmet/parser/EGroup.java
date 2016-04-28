package com.github.user.pcmind.emmet.parser;

/**
 * Created by honor on 28/04/2016.
 */
public class EGroup implements ENode {
    private ENode content;

    public EGroup(ENode content) {
        this.content = content;
    }

    public ENode getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "(" + content + ")";
    }
}
