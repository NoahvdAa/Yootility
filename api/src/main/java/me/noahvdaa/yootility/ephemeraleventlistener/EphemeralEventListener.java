package me.noahvdaa.yootility.ephemeraleventlistener;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * A non-permanent event listener.
 *
 * @param <T> the event to listen to
 */
public class EphemeralEventListener<T extends Event> implements Listener {

    private Class<T> event;
    private Predicate<T> filter = null;
    private Consumer<T> consumer;
    private boolean automaticallyUnregister = true;

    private EphemeralEventListener() {
    }

    /**
     * Creates an ephemeral event listener that automatically unregisters itself after it has fired.
     * Note: You must register the listener yourself using the {@link #register(Plugin)} method.
     *
     * @param event    the event to listen to
     * @param consumer the consumer to run when the event is fired
     * @param <T>      the event to listen to
     * @return the ephemeral event listener
     */
    @NotNull
    public static <T extends Event> EphemeralEventListener<T> automaticallyUnregistering(@NotNull Class<T> event, @NotNull Consumer<T> consumer) {
        Objects.requireNonNull(event, "event may not be null");
        Objects.requireNonNull(consumer, "consumer may not be null");

        EphemeralEventListener<T> listener = new EphemeralEventListener<>();
        listener.event = event;
        listener.consumer = consumer;

        return listener;
    }

    /**
     * Creates an ephemeral event listener that does not automatically unregister itself after it has fired.
     * To unregister, simply call the {@link #unregister()} method.
     * Note: You must register the listener yourself using the {@link #register(Plugin)} method.
     *
     * @param event    the event to listen to
     * @param consumer the consumer to run when the event is fired
     * @param <T>      the event to listen to
     * @return the ephemeral event listener
     */
    @NotNull
    public static <T extends Event> EphemeralEventListener<T> manualUnregistering(@NotNull Class<T> event, @NotNull Consumer<T> consumer) {
        EphemeralEventListener<T> listener = automaticallyUnregistering(event, consumer);

        listener.automaticallyUnregister = false;

        return listener;
    }

    /**
     * Creates an ephemeral event listener that automatically unregisters itself after it has fired.
     * The consumer (and un-registration) is only called when the predicate matches.
     * Note: You must register the listener yourself using the {@link #register(Plugin)} method.
     *
     * @param event    the event to listen to
     * @param filter   the predicate the event must match before the consumer gets called
     * @param consumer the consumer to run when the event is fired
     * @param <T>      the event to listen to
     * @return the ephemeral event listener
     */
    @NotNull
    public static <T extends Event> EphemeralEventListener<T> automaticallyUnregistering(@NotNull Class<T> event, @Nullable Predicate<T> filter, @NotNull Consumer<T> consumer) {
        Objects.requireNonNull(event, "event may not be null");
        Objects.requireNonNull(consumer, "consumer may not be null");

        EphemeralEventListener<T> listener = new EphemeralEventListener<>();
        listener.event = event;
        listener.filter = filter;
        listener.consumer = consumer;

        return listener;
    }

    /**
     * Creates an ephemeral event listener that does not automatically unregister itself after it has fired.
     * The consumer is only called when the predicate matches.
     * To unregister, simply call the {@link #unregister()} method.
     * Note: You must register the listener yourself using the {@link #register(Plugin)} method.
     *
     * @param event    the event to listen to
     * @param filter   the predicate the event must match before the consumer gets called
     * @param consumer the consumer to run when the event is fired
     * @param <T>      the event to listen to
     * @return the ephemeral event listener
     */
    @NotNull
    public static <T extends Event> EphemeralEventListener<T> manualUnregistering(@NotNull Class<T> event, @Nullable Predicate<T> filter, @NotNull Consumer<T> consumer) {
        EphemeralEventListener<T> listener = automaticallyUnregistering(event, filter, consumer);

        listener.automaticallyUnregister = false;

        return listener;
    }

    /**
     * Registers the event listener.
     * This method is chainable, it returns the same instance as it was called on.
     *
     * @param plugin the plugin to listen to the event to as
     * @return the current instance
     */
    @NotNull
    public EphemeralEventListener<T> register(@NotNull Plugin plugin) {
        register(plugin, EventPriority.NORMAL);

        return this;
    }

    /**
     * Registers the event listener with a specific priority.
     * This method is chainable, it returns the same instance as it was called on.
     *
     * @param plugin   the plugin to listen to the event to as
     * @param priority the priority to listen at
     * @return the current instance
     */
    @NotNull
    @SuppressWarnings("unchecked")
    public EphemeralEventListener<T> register(@NotNull Plugin plugin, @NotNull EventPriority priority) {
        Objects.requireNonNull(plugin, "plugin may not be null");
        Objects.requireNonNull(priority, "priority may not be null");

        plugin.getServer().getPluginManager().registerEvent(this.event, this, priority, (listener, event) -> ((EphemeralEventListener<T>) listener).onEvent((T) event), plugin);

        return this;
    }

    /**
     * Un-registers the event listener.
     * This method is chainable, it returns the same instance as it was called on.
     *
     * @return the current instance
     */
    @NotNull
    public EphemeralEventListener<T> unregister() {
        HandlerList.unregisterAll(this);

        return this;
    }

    /**
     * Gets the event that this listener can listen to.
     *
     * @return the event that this listener can listen to
     */
    @NotNull
    public Class<T> getEvent() {
        return this.event;
    }

    /**
     * Gets the filter that is checked before the consumer is called.
     *
     * @return the filter that is checked before the consumer is called, or null if this listener doesn't have a filter
     */
    @Nullable
    public Predicate<T> getFilter() {
        return this.filter;
    }

    /**
     * Gets the consumer that is run when the event is fired.
     *
     * @return the consumer that is run when the event is fired
     */
    @NotNull
    public Consumer<T> getConsumer() {
        return this.consumer;
    }

    /**
     * Gets whether the listener automatically unregisters after the consumer is called.
     *
     * @return whether the listener automatically unregisters after the consumer is called
     */
    public boolean automaticallyUnregisters() {
        return this.automaticallyUnregister;
    }

    @EventHandler
    private void onEvent(T event) {
        if (!this.filter.test(event)) return;

        if (this.automaticallyUnregister) event.getHandlers().unregister(this);
        this.consumer.accept(event);
    }

}
