package com.github.user.pcmind.emmet.parser;

import com.github.user.pcmind.emmet.lexer.Lexer;
import static com.google.common.truth.Truth.assert_;
import org.junit.Test;

/**
 * Created by honor on 28/04/2016.
 */
public class ParserTest {
    @Test(expected = ParseException.class)
    public void baseContentEnding() throws Exception {
        parse("element{content");
    }

    @Test
    public void elementWithContent() throws Exception {
        assert_().that(parse("element{content}").toString()).isEqualTo("element{content}");
    }

    @Test
    public void success() throws Exception {
        assertParse("aaa>(bbb+ccc)");
        assertParse("aaa>(bbb+ccc>dsfsdf)");
        assertParse("aaa{aaa}[aasas=sdfsdf]>(bbb+ccc>dsfsdf)");
    }

    private void assertParse(final String value) {
        assert_().that(parse(value).toString()).isEqualTo(value);
    }

    private ENode parse(final String value) {
        return Parser.parse(Lexer.from(value));
    }
}