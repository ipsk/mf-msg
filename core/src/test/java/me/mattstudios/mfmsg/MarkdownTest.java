package me.mattstudios.mfmsg;

import me.mattstudios.mfmsg.base.MessageOptions;
import me.mattstudios.mfmsg.base.internal.components.MessageNode;
import me.mattstudios.mfmsg.base.internal.parser.MarkdownParser;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class MarkdownTest {

    private final MarkdownParser markdownParser = new MarkdownParser(MessageOptions.builder().build());

    @Test
    public void test_bold() {
        final List<MessageNode> nodes = markdownParser.parse("**Hello**");
        final String nodeString = nodes.stream().map(Object::toString).collect(Collectors.joining(", "));

        assertNotNull(nodeString);
        assertEquals(nodeString, "Hello - BOLD");
    }

    @Test
    public void test_italic() {
        final List<MessageNode> nodes = markdownParser.parse("*Hello*");
        final String nodeString = nodes.stream().map(Object::toString).collect(Collectors.joining(", "));

        assertNotNull(nodeString);
        assertEquals(nodeString, "Hello - ITALIC");
    }

    @Test
    public void test_strikethrough() {
        final List<MessageNode> nodes = markdownParser.parse("~~Hello~~");
        final String nodeString = nodes.stream().map(Object::toString).collect(Collectors.joining(", "));

        assertNotNull(nodeString);
        assertEquals(nodeString, "Hello - STRIKETHROUGH");
    }

    @Test
    public void test_underlined() {
        final List<MessageNode> nodes = markdownParser.parse("__Hello__");
        final String nodeString = nodes.stream().map(Object::toString).collect(Collectors.joining(", "));

        assertNotNull(nodeString);
        assertEquals(nodeString, "Hello - UNDERLINED");
    }

    @Test
    public void test_obfuscated() {
        final List<MessageNode> nodes = markdownParser.parse("||Hello||");
        final String nodeString = nodes.stream().map(Object::toString).collect(Collectors.joining(", "));

        assertNotNull(nodeString);
        assertEquals(nodeString, "Hello - OBFUSCATED");
    }

    @Test
    public void test_action() {
        final List<MessageNode> nodes = markdownParser.parse("[Hello](hover: Hello!)");
        final String nodeString = nodes.stream().map(Object::toString).collect(Collectors.joining(", "));

        assertNotNull(nodeString);
        assertEquals(nodeString, "Hello - ACTIONS");
    }

    @Test
    public void test_complex() {
        final List<MessageNode> nodes = markdownParser.parse("**Bold** *italic* ~~strike~~ __underline__ ||obfuscated|| [action](hover: Hello!)");
        final String nodeString = nodes.stream().map(Object::toString).collect(Collectors.joining());

        assertNotNull(nodeString);
        assertEquals(nodeString, "Bold - BOLD italic - ITALIC strike - STRIKETHROUGH underline - UNDERLINED obfuscated - OBFUSCATED action - ACTIONS");
    }

}
