package com.github.user.pcmind.emmet.parser;

/**
 * Created by honor on 28/04/2016.
 */
public class EContent implements ENode {
    private String value;

    public EContent(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" + value + "}";
    }
}
