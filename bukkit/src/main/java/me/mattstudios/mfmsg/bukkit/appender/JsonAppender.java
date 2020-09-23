package me.mattstudios.mfmsg.bukkit.appender;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.mattstudios.mfmsg.base.internal.action.ClickMessageAction;
import me.mattstudios.mfmsg.base.internal.action.HoverMessageAction;
import me.mattstudios.mfmsg.base.internal.action.MessageAction;
import me.mattstudios.mfmsg.base.internal.components.MessageNode;
import me.mattstudios.mfmsg.base.serializer.Appender;
import me.mattstudios.mfmsg.bukkit.NmsMessage;
import me.mattstudios.mfmsg.base.serializer.scanner.ScanUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class JsonAppender implements Appender {

    private static final Gson GSON = new Gson();
    private final JsonArray jsonArray = new JsonArray();

    @Override
    public void append(@NotNull final String value) {
        jsonArray.add(value);
    }

    @Override
    public void appendNode(@NotNull final String text, @Nullable final String color, final boolean bold, final boolean italic, final boolean strike, final boolean underline, final boolean obfuscated, @Nullable final List<MessageAction> actions) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("text", text);

        if (bold) jsonObject.addProperty("bold", true);
        if (italic) jsonObject.addProperty("italic", true);
        if (strike) jsonObject.addProperty("strikethrough", true);
        if (underline) jsonObject.addProperty("underlined", true);
        if (obfuscated) jsonObject.addProperty("obfuscated", true);

        if (color != null) jsonObject.addProperty("color", color);

        if (actions == null || actions.isEmpty()) {
            jsonArray.add(jsonObject);
            return;
        }

        for (final MessageAction messageAction : actions) {
            if (messageAction instanceof HoverMessageAction) {
                final JsonObject hoverObject = new JsonObject();
                hoverObject.addProperty("action", "show_text");

                final List<MessageNode> nodes = ((HoverMessageAction) messageAction).getNodes();

                final JsonAppender appender = new JsonAppender();
                ScanUtils.scan(nodes, appender);
                final JsonArray array = appender.getJsonArray();

                if (NmsMessage.CURRENT_VERSION.isColorLegacy()) {
                    hoverObject.add("value", array);
                } else {
                    hoverObject.add("contents", array);
                }

                jsonObject.add("hoverEvent", hoverObject);
                continue;
            }

            final ClickMessageAction clickAction = (ClickMessageAction) messageAction;

            switch (clickAction.getActionType()) {
                case ACTION_COMMAND:
                    jsonObject.add("clickEvent", getClickEvent(clickAction, "run_command"));
                    continue;

                case ACTION_SUGGEST:
                    jsonObject.add("clickEvent", getClickEvent(clickAction, "suggest_command"));
                    continue;

                case ACTION_URL:
                    jsonObject.add("clickEvent", getClickEvent(clickAction, "open_url"));
                    continue;

                case ACTION_CLIPBOARD:
                    jsonObject.add("clickEvent", getClickEvent(clickAction, "copy_to_clipboard"));
            }
        }

        jsonArray.add(jsonObject);
    }

    @Override
    public String build() {
        return GSON.toJson(jsonArray);
    }

    public JsonArray getJsonArray() {
        return jsonArray;
    }

    @NotNull
    private JsonObject getClickEvent(@NotNull final ClickMessageAction clickAction, @NotNull final String type) {
        final JsonObject clickObject = new JsonObject();
        clickObject.addProperty("action", type);
        clickObject.addProperty("value", clickAction.getAction());
        return clickObject;
    }

}
