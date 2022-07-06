package me.xii69.timer;

import me.xii69.timer.command.TimerCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Timer extends JavaPlugin {

    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getCommand("timer").setExecutor(new TimerCommand());
    }

    @Override
    public void onDisable() {
        plugin = null;
    }

    public static Plugin getInstance() {
        return plugin;
    }

}
