package me.noahvdaa.yootility.ephemeralcommands;

import me.noahvdaa.yootility.validation.FormatRegexes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class EphemeralCommandManager {

    private final Map<UUID, EphemeralCommandSet> sets = new HashMap<>();
    private PluginCommand command;

    private EphemeralCommandManager() {
    }

    @NotNull
    public static EphemeralCommandManager ephemeralCommandManager(@NotNull final PluginCommand command) {
        Objects.requireNonNull(command, "command may not be null");

        EphemeralCommandManager manager = new EphemeralCommandManager();
        manager.command = command;
        command.setExecutor(new Executor(manager));

        return manager;
    }

    @NotNull
    public UUID addSet(@NotNull final EphemeralCommandSet set) {
        Objects.requireNonNull(set, "set may not be null");

        UUID uuid = UUID.randomUUID();
        this.sets.put(uuid, set);

        return uuid;
    }

    public boolean removeSet(@NotNull final UUID uuid) {
        Objects.requireNonNull(uuid, "uuid may not be null");

        return this.sets.remove(uuid) == null;
    }

    public boolean removeSet(@NotNull final EphemeralCommandSet set) {
        Objects.requireNonNull(set, "set may not be null");

        boolean removedAny = false;
        while (this.sets.values().remove(set)) {
            removedAny = true;
        }

        return removedAny;
    }

    @NotNull
    public PluginCommand getCommand() {
        return this.command;
    }

    @Nullable
    public EphemeralCommandSet getSet(@NotNull final UUID uuid) {
        Objects.requireNonNull(uuid, "uuid may not be null");

        return this.sets.get(uuid);
    }

    private record Executor(@NotNull EphemeralCommandManager manager) implements CommandExecutor {

        @Override
        public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
            if (args.length < 1) return true;

            String uuid = args[0];
            if (!FormatRegexes.DASHED_UUID.matcher(uuid).matches()) return true;

            String cmd = args.length > 1 ? String.join(" ", args).substring(uuid.length() + 1) : null;
            UUID parsedUUID = UUID.fromString(uuid);

            EphemeralCommandSet set = manager.getSet(parsedUUID);
            if (set == null) return true;

            EphemeralCommand execute = set.getCommand(cmd);
            if (execute == null) return true;

            execute.getConsumer().accept(sender);

            return true;
        }

    }

}
