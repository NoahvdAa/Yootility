package me.noahvdaa.yootility.ephemeralcommands;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;

public class EphemeralCommand {

    private String name = null;
    private Consumer<CommandSender> consumer;

    private EphemeralCommand() {
    }

    @NotNull
    public static EphemeralCommand named(@NotNull final String name, @NotNull final Consumer<CommandSender> consumer) {
        Objects.requireNonNull(name, "name may not be null");
        Objects.requireNonNull(consumer, "consumer may not be null");

        EphemeralCommand command = new EphemeralCommand();
        command.name = name;
        command.consumer = consumer;

        return command;
    }

    @NotNull
    public static EphemeralCommand unnamed(@NotNull final Consumer<CommandSender> consumer) {
        Objects.requireNonNull(consumer, "consumer may not be null");

        EphemeralCommand command = new EphemeralCommand();
        command.consumer = consumer;

        return command;
    }

    @Nullable
    public String getName() {
        return this.name;
    }

    @NotNull
    public Consumer<CommandSender> getConsumer() {
        return this.consumer;
    }

}
