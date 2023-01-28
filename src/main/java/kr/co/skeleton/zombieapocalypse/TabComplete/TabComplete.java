package kr.co.skeleton.zombieapocalypse.TabComplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TabComplete implements TabCompleter {

    static List<String> arguments = new ArrayList<>();

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (arguments.isEmpty()) {
            if (args.length == 1) {
                arguments.add("report");
                arguments.add("fixed");
                arguments.add("fixing");
            }
        }
        return arguments;
    }
}
