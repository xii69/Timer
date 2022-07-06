package me.xii69.timer.command;

import me.xii69.timer.Timer;
import me.xii69.timer.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class TimerCommand implements CommandExecutor {

    int time = 0;
    boolean paused = false;
    boolean started = false;
    BukkitTask runnable = null;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.colorize("&cThis command is just for in-game players."));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(Utils.colorize("&aRunning Timer v1.0.0, &e/timer help &afor help."));
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "start":
                if ((args.length != 2)) {
                    player.sendMessage(Utils.colorize("&cYou need to enter a time like &e3h&c, &e36m &cor &e28s"));
                    return true;
                }
                if ((!args[1].contains("h")) || (!args[1].contains("m")) || (!args[1].contains("s"))) {
                    player.sendMessage(Utils.colorize("&cEnter a valid time like &e3h&c, &e36m &cor &e28s"));
                    return true;
                }
                if (started) {
                    player.sendMessage(Utils.colorize("&cTimer has been started already."));
                    return true;
                }
                switch (String.valueOf(args[1].charAt(args[1].length() - 1))) {
                    case "h":
                        time = Integer.parseInt(args[1].substring(0, args[1].length() - 1)) * 60 * 60;
                        break;
                    case "m":
                        time = Integer.parseInt(args[1].substring(0, args[1].length() - 1)) * 60;
                        break;
                    case "s":
                        time = Integer.parseInt(args[1].substring(0, args[1].length() - 1));
                        break;
                }
                started = true;
                runnable = new BukkitRunnable() {
                    @Override
                    public void run() {
                        Utils.sendActionBar(player, Utils.format(time));
                        if (!paused) time--;
                    }
                }.runTaskTimer(Timer.getInstance(), 0, 20);
                return true;
            case "pause":
                if (paused) {
                    player.sendMessage(Utils.colorize("&cTimer has been paused already."));
                    return true;
                }
                paused = true;
                player.sendMessage(Utils.colorize("&bTimer has been paused."));
                return true;
            case "resume":
                if (!paused) {
                    player.sendMessage(Utils.colorize("&cTimer has been resumed already."));
                    return true;
                }
                paused = false;
                player.sendMessage(Utils.colorize("&bTimer has been resumed."));
                return true;
            case "stop":
                if (!started) {
                    player.sendMessage(Utils.colorize("&bFirst start a timer, need help? &e/timer help"));
                    return true;
                }
                started = false;
                runnable.cancel();
                player.sendMessage(Utils.colorize("&bTimer has been stoped."));
                return true;
            case "help":
                player.sendMessage(Utils.colorize(
                        "&7&m-----------------------------------\n"
                                + "&e/timer start <time> &7(like 5h, 29m or 100s)\n"
                                + "&e/timer pause &bpauses the timer\n"
                                + "&e/timer resume &bresumes the paused timer\n"
                                + "&e/timer stop &bstop and clears the timer\n"
                                + "&7&m-----------------------------------"
                ));
                return true;
        }

        player.sendMessage(Utils.colorize("&cUnknown argument, &e/timer help &cfor help."));

        return false;

    }

}
