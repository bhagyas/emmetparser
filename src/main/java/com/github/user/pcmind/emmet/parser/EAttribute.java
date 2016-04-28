package com.github.user.pcmind.emmet.parser;

/**
 * Created by honor on 28/04/2016.
 */
public class EAttribute implements ENode {

    private final String attribute;
    private final String value;

    public EAttribute(String attribute, String value) {
        this.attribute = attribute;
        this.value = value;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return attribute + "=" + value;
    }
}
