package me.noahvdaa.yootility.testplugin.command;

import me.noahvdaa.yootility.spacingfont.SpacingFont;
import me.noahvdaa.yootility.spacingfont.SpacingFontTagResolver;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MiniMessageCommand implements CommandExecutor {

    private static final TagResolver RESOLVER = TagResolver.resolver(
            SpacingFontTagResolver.of(SpacingFont.AMBERWAT_SPACE_REGULAR, "asr"),
            SpacingFontTagResolver.of(SpacingFont.AMBERWAT_SPACE_NO_SPLIT, "asns")
    );

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage(MiniMessage.miniMessage().deserialize(String.join(" ", args), RESOLVER));

        return true;
    }

}
