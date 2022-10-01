package me.noahvdaa.yootility.testplugin;

import me.noahvdaa.yootility.ephemeralcommands.EphemeralCommand;
import me.noahvdaa.yootility.ephemeralcommands.EphemeralCommandManager;
import me.noahvdaa.yootility.ephemeralcommands.EphemeralCommandSet;
import me.noahvdaa.yootility.ephemeraleventlisteners.EphemeralEventListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record FriendRequestCommand(YootilityTestPlugin plugin) implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Component.text("This command is only available to players.", NamedTextColor.RED));
            return true;
        }

        EphemeralCommandManager cm = plugin.getEphemeralCommandManager();
        EphemeralCommandSet set = EphemeralCommandSet.empty();

        EphemeralEventListener<PlayerQuitEvent> leaveListener = EphemeralEventListener.automaticallyUnregistering(PlayerQuitEvent.class, (p) -> p.getPlayer() == player, (e) -> cm.removeSet(set)).register(plugin);

        set.add(
                EphemeralCommand.named("accept", (clicker) -> {
                    clicker.sendMessage(Component.text("Accepted the friend request!", NamedTextColor.GREEN));
                    cm.removeSet(set); // the other actions should no longer do anything, unregister.
                    leaveListener.unregister();
                }),
                EphemeralCommand.named("deny", (clicker) -> {
                    clicker.sendMessage(Component.text("Denied the friend request!", NamedTextColor.RED));
                    cm.removeSet(set); // the other actions should no longer do anything, unregister.
                    leaveListener.unregister();
                }),
                EphemeralCommand.named("ignore", (clicker) -> {
                    clicker.sendMessage(Component.text("Ignored the friend request!", NamedTextColor.GRAY));
                    cm.removeSet(set); // the other actions should no longer do anything, unregister.
                    leaveListener.unregister();
                })
        );
        UUID setUUID = cm.addSet(set);
        String baseCommand = "/" + cm.getCommand().getName() + " " + setUUID + " ";

        sender.sendMessage(
                MiniMessage.miniMessage().deserialize("<yellow>Friend request received from <aqua>jeb_</aqua>\nActions: ")
                        .append(Component.text("[accept] ", NamedTextColor.GREEN, TextDecoration.BOLD).clickEvent(ClickEvent.runCommand(baseCommand + "accept")))
                        .append(Component.text("[deny] ", NamedTextColor.RED, TextDecoration.BOLD).clickEvent(ClickEvent.runCommand(baseCommand + "deny")))
                        .append(Component.text("[ignore] ", NamedTextColor.GRAY, TextDecoration.BOLD).clickEvent(ClickEvent.runCommand(baseCommand + "ignore")))
        );
        return true;
    }

}
