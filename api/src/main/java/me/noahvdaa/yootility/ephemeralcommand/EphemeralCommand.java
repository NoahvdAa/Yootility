package me.noahvdaa.yootility.ephemeralcommand;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * A non-permanent command.
 */
public class EphemeralCommand {

    private String name = null;
    private Consumer<CommandSender> consumer;

    private EphemeralCommand() {
    }

    /**
     * Creates a named ephemeral command. To create an ephemeral command without a name, use {@link #unnamed(Consumer)}.
     *
     * @param name     the name of the new command
     * @param consumer the consumer to run when the command is executed
     * @return the ephemeral command
     */
    @NotNull
    public static EphemeralCommand named(@NotNull final String name, @NotNull final Consumer<CommandSender> consumer) {
        Objects.requireNonNull(name, "name may not be null");
        Objects.requireNonNull(consumer, "consumer may not be null");

        EphemeralCommand command = new EphemeralCommand();
        command.name = name;
        command.consumer = consumer;

        return command;
    }

    /**
     * Creates an unnamed ephemeral command. To create an ephemeral command with a name, use {@link #named(String, Consumer)}.
     *
     * @param consumer the consumer to run when the command is executed
     * @return the ephemeral command
     */
    @NotNull
    public static EphemeralCommand unnamed(@NotNull final Consumer<CommandSender> consumer) {
        Objects.requireNonNull(consumer, "consumer may not be null");

        EphemeralCommand command = new EphemeralCommand();
        command.consumer = consumer;

        return command;
    }

    /**
     * Gets the name of this command.
     *
     * @return the name of this command, or null if it doesn't have one
     */
    @Nullable
    public String getName() {
        return this.name;
    }

    /**
     * Gets the consumer that is run when this command is executed.
     *
     * @return the consumer that is run when this command is executed
     */
    @NotNull
    public Consumer<CommandSender> getConsumer() {
        return this.consumer;
    }

}
