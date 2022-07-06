package me.xii69.timer.utils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void sendActionBar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(colorize(message)));
    }

    public static String format(int time) {
        int s = time % 60, m = (time / 60) % 60, h = (time / 60) / 60;
        String sec = (s < 10) ? "0" + s : Integer.toString(s);
        String min = (m < 10) ? "0" + m : Integer.toString(m);
        String hour = (h < 10) ? "0" + h : Integer.toString(h);
        return "&a" + hour + "&7:" + "&a" + min + "&7:" + "&a" + sec;
    }

}
