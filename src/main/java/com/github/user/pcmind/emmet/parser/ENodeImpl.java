package com.github.user.pcmind.emmet.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by honor on 28/04/2016.
 */
class ENodeImpl implements ENode {
    private List<ENode> content = new ArrayList<ENode>();

    public List<ENode> getContent() {
        return content;
    }
}
