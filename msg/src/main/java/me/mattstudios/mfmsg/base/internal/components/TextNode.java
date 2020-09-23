package me.mattstudios.mfmsg.base.internal.components;

import me.mattstudios.mfmsg.base.internal.action.MessageAction;
import me.mattstudios.mfmsg.base.internal.color.FlatColor;
import me.mattstudios.mfmsg.base.internal.color.MessageColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class TextNode implements MessageNode {

    @NotNull
    private final String text;

    @NotNull
    private MessageColor color = new FlatColor("white");

    private boolean bold = false;
    private boolean italic = false;
    private boolean strike = false;
    private boolean underlined = false;
    private boolean obfuscated = false;

    @Nullable
    private List<MessageAction> messageActions = null;

    /**
     * Main part constructor with all it's attributes
     *
     * @param text The text literal
     */
    public TextNode(@NotNull final String text) {
        this.text = text;
    }

    /**
     * Gets the text literal
     *
     * @return The text
     */
    @NotNull
    public String getText() {
        return text;
    }

    /**
     * Gets the message color
     *
     * @return The message color
     */
    @NotNull
    public MessageColor getColor() {
        return color;
    }

    /**
     * Checks if the part is bold or not
     *
     * @return Whether or not it's bold
     */
    public boolean isBold() {
        return bold;
    }

    /**
     * Checks if the part is italic or not
     *
     * @return Whether or not it's italic
     */
    public boolean isItalic() {
        return italic;
    }

    /**
     * Checks if the part is strikethrough or not
     *
     * @return Whether or not it's strikethrough
     */
    public boolean isStrike() {
        return strike;
    }

    /**
     * Checks if the part is underlined or not
     *
     * @return Whether or not it's underlined
     */
    public boolean isUnderlined() {
        return underlined;
    }

    /**
     * Checks if the part is bold or not
     *
     * @return Whether or not it's bold
     */
    public boolean isObfuscated() {
        return obfuscated;
    }

    /**
     * Gets the actions the message has
     *
     * @return The message actions
     */
    @Nullable
    public List<MessageAction> getActions() {
        return messageActions;
    }

    public void setColor(@NotNull final MessageColor color) {
        this.color = color;
    }

    public void setBold(final boolean bold) {
        this.bold = bold;
    }

    public void setItalic(final boolean italic) {
        this.italic = italic;
    }

    public void setStrike(final boolean strike) {
        this.strike = strike;
    }

    public void setUnderlined(final boolean underlined) {
        this.underlined = underlined;
    }

    public void setObfuscated(final boolean obfuscated) {
        this.obfuscated = obfuscated;
    }

    public void setActions(final List<MessageAction> messageActions) {
        this.messageActions = messageActions;
    }

    /**
     * For testing, mostly
     *
     * @return Testing String
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(text);
        if (bold) builder.append(" - BOLD");
        if (italic) builder.append(" - ITALIC");
        if (strike) builder.append(" - STRIKETHROUGH");
        if (underlined) builder.append(" - UNDERLINED");
        if (obfuscated) builder.append(" - OBFUSCATED");
        if (messageActions != null) builder.append(" - ACTIONS");
        return builder.toString();
    }

}
