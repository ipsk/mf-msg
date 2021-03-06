package me.mattstudios.msg.base.internal;

import java.util.EnumSet;
import java.util.Set;

/**
 * All formats that are available to disable
 */
public enum Format {

    BOLD,
    LEGACY_BOLD,
    ITALIC,
    LEGACY_ITALIC,
    STRIKETHROUGH,
    LEGACY_STRIKETHROUGH,
    UNDERLINE,
    LEGACY_UNDERLINE,
    OBFUSCATED,
    LEGACY_OBFUSCATED,
    COLOR,
    HEX,
    GRADIENT,
    RAINBOW,
    ACTION_HOVER,
    ACTION_COMMAND,
    ACTION_URL,
    ACTION_CLIPBOARD,
    ACTION_SUGGEST,
    LINE_BREAK;

    public static final Set<Format> ALL = EnumSet.allOf(Format.class);
    public static final Set<Format> NONE = EnumSet.noneOf(Format.class);
    public static final Set<Format> ACTIONS = EnumSet.of(ACTION_HOVER, ACTION_CLIPBOARD, ACTION_COMMAND, ACTION_SUGGEST, ACTION_URL);

}
