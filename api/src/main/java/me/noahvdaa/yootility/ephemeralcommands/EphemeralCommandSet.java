package me.noahvdaa.yootility.ephemeralcommands;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EphemeralCommandSet {

    private final Map<String, EphemeralCommand> commands = new HashMap<>();

    private EphemeralCommandSet() {
    }

    @NotNull
    public static EphemeralCommandSet empty() {
        return new EphemeralCommandSet();
    }

    @NotNull
    public static EphemeralCommandSet of(@NotNull EphemeralCommand command) {
        Objects.requireNonNull(command, "command may not be null");

        EphemeralCommandSet set = new EphemeralCommandSet();

        set.commands.put(null, command);

        return set;
    }

    @NotNull
    public static EphemeralCommandSet of(@NotNull EphemeralCommand... commands) {
        Objects.requireNonNull(commands, "commands may not be null");

        EphemeralCommandSet set = new EphemeralCommandSet();

        for (EphemeralCommand command : commands) {
            if (set.commands.containsKey(command.getName()))
                throw new IllegalArgumentException("Command with name '" + command.getName() + "' already exists in this set");

            set.commands.put(command.getName(), command);
        }

        return set;
    }

    public void add(@NotNull EphemeralCommand command) {
        Objects.requireNonNull(command, "command may not be null");

        if (this.commands.containsKey(command.getName()))
            throw new IllegalArgumentException("Command with name '" + command.getName() + "' already exists in this set");

        this.commands.put(command.getName(), command);
    }

    public void add(@NotNull EphemeralCommand... commands) {
        Objects.requireNonNull(commands, "commands may not be null");

        for (EphemeralCommand command : commands) {
            if (this.commands.containsKey(command.getName()))
                throw new IllegalArgumentException("Command with name '" + command.getName() + "' already exists in this set");

            this.commands.put(command.getName(), command);
        }
    }

    @Nullable
    public EphemeralCommand getCommand(@Nullable String name) {
        return this.commands.get(name);
    }

}
