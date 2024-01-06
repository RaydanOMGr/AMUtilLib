package me.andreasmelone.amutillib.events;

import org.bukkit.Server;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ServerTickEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Server server;

    public ServerTickEvent(Server server) {
        this.server = server;
    }

    public Server getServer() {
        return server;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
