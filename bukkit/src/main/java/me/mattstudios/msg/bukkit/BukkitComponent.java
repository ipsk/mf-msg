package me.mattstudios.msg.bukkit;

import me.mattstudios.msg.base.internal.nodes.MessageNode;
import me.mattstudios.msg.bukkit.serializer.NodeSerializer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * {@link MessageComponent} for Bukkit specific methods
 */
public final class BukkitComponent implements MessageComponent {

    @NotNull
    private final List<MessageNode> nodes;

    /**
     * Main constructor that takes in the parsed Lines
     *
     * @param nodes The parsed lines from {@link BukkitMessage}
     */
    public BukkitComponent(@NotNull final List<MessageNode> nodes) {
        this.nodes = nodes;
    }

    /**
     * Sends the json message to the {@link Player}
     *
     * @param player The {@link Player}
     */
    @Override
    public void sendMessage(@NotNull final Player player) {
        NmsMessage.sendMessage(player, toJson());
    }

    /**
     * Sends a title to the {@link Player}
     *
     * @param player  The {@link Player}
     * @param fadeIn  An {@link Integer} in ticks for the fade in time
     * @param stay    An {@link Integer} in ticks for the stay time
     * @param fadeOut An {@link Integer} in ticks for the fade out time
     */
    @Override
    public void sendTitle(@NotNull final Player player, final int fadeIn, final int stay, final int fadeOut) {
        NmsMessage.sendTitle(player, toJson(), NmsMessage.TitleType.TITLE, fadeIn, stay, fadeOut);
    }

    /**
     * Sends a SubTitle to the {@link Player}
     *
     * @param player  The {@link Player}
     * @param fadeIn  An {@link Integer} in ticks for the fade in time
     * @param stay    An {@link Integer} in ticks for the stay time
     * @param fadeOut An {@link Integer} in ticks for the fade out time
     */
    @Override
    public void sendSubTitle(@NotNull final Player player, final int fadeIn, final int stay, final int fadeOut) {
        NmsMessage.sendTitle(player, toJson(), NmsMessage.TitleType.SUBTITLE, fadeIn, stay, fadeOut);
    }

    /**
     * Sends an ActionBar to the {@link Player}
     *
     * @param player  The {@link Player}
     * @param fadeIn  An {@link Integer} in ticks for the fade in time
     * @param stay    An {@link Integer} in ticks for the stay time
     * @param fadeOut An {@link Integer} in ticks for the fade out time
     */
    @Override
    public void sendActionBar(@NotNull final Player player, final int fadeIn, final int stay, final int fadeOut) {
        NmsMessage.sendTitle(player, toJson(), NmsMessage.TitleType.ACTIONBAR, fadeIn, stay, fadeOut);
    }

    /**
     * Parses the lines to {@link String} to be used on items etc
     *
     * @return A parsed {@link String}
     */
    @NotNull
    @Override
    public String toString() {
        return NodeSerializer.toString(nodes);
    }

    /**
     * Gets the raw json {@link String}
     *
     * @return The raw json
     */
    @NotNull
    @Override
    public String toJson() {
        return NodeSerializer.toJson(nodes);
    }

    /**
     * Gets the raw {@link MessageNode}
     *
     * @return The {@link MessageNode} that were parsed
     */
    @Override
    @NotNull
    public List<MessageNode> getNodes() {
        return nodes;
    }

}
