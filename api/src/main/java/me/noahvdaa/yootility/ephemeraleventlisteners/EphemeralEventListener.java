package me.noahvdaa.yootility.ephemeraleventlisteners;

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

public class EphemeralEventListener<T extends Event> implements Listener {

    private Class<T> event;
    private Predicate<T> filter = null;
    private Consumer<T> consumer;
    private boolean automaticallyUnregister = true;

    private EphemeralEventListener() {
    }

    @NotNull
    public static <T extends Event> EphemeralEventListener<T> automaticallyUnregistering(@NotNull Class<T> event, @NotNull Consumer<T> consumer) {
        Objects.requireNonNull(event, "event may not be null");
        Objects.requireNonNull(consumer, "consumer may not be null");

        EphemeralEventListener<T> listener = new EphemeralEventListener<>();
        listener.event = event;
        listener.consumer = consumer;

        return listener;
    }

    @NotNull
    public static <T extends Event> EphemeralEventListener<T> manualUnregistering(@NotNull Class<T> event, @NotNull Consumer<T> consumer) {
        EphemeralEventListener<T> listener = automaticallyUnregistering(event, consumer);

        listener.automaticallyUnregister = false;

        return listener;
    }

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

    @NotNull
    public static <T extends Event> EphemeralEventListener<T> manualUnregistering(@NotNull Class<T> event, @Nullable Predicate<T> filter, @NotNull Consumer<T> consumer) {
        EphemeralEventListener<T> listener = automaticallyUnregistering(event, filter, consumer);

        listener.automaticallyUnregister = false;

        return listener;
    }

    public void register(@NotNull Plugin plugin) {
        register(plugin, EventPriority.NORMAL);
    }

    @SuppressWarnings("unchecked")
    public void register(@NotNull Plugin plugin, @NotNull EventPriority priority) {
        Objects.requireNonNull(plugin, "plugin may not be null");
        Objects.requireNonNull(priority, "priority may not be null");

        plugin.getServer().getPluginManager().registerEvent(this.event, this, priority, (listener, event) -> ((EphemeralEventListener<T>) listener).onEvent((T) event), plugin);
    }

    public void unregister(@NotNull Plugin plugin) {
        Objects.requireNonNull(plugin, "plugin may not be null");

        HandlerList.unregisterAll(plugin);
    }

    @EventHandler
    public void onEvent(T event) {
        if (!this.filter.test(event)) return;

        if (this.automaticallyUnregister) event.getHandlers().unregister(this);
        this.consumer.accept(event);
    }

}
