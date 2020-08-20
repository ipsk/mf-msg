package me.mattstudios.mfmsg.base;

import me.mattstudios.mfmsg.base.bungee.BungeeComponent;
import me.mattstudios.mfmsg.base.internal.Format;
import me.mattstudios.mfmsg.base.internal.MessageComponent;
import me.mattstudios.mfmsg.base.internal.component.MessagePart;
import me.mattstudios.mfmsg.base.internal.parser.MessageParser;
import me.mattstudios.mfmsg.base.internal.util.Regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class Message {

    private final Set<Format> formats;

    private Message(final FormatOptions formatOptions) {
        formats = formatOptions.getFormats();
    }

    public static Message create(final FormatOptions formatOptions) {
        return new Message(formatOptions);
    }

    public static Message create() {
        return new Message(new FormatOptions());
    }

    public MessageComponent parse(final String message) {
        final List<List<MessagePart>> parts = new ArrayList<>();

        for (final String line : Regex.NEW_LINE.split(message)) {
            parts.add(new MessageParser(line, formats).parse());
        }

        return new BungeeComponent(parts);
    }

}
