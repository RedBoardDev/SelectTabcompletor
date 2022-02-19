package fr.redboard.selecttabcompletor.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import fr.redboard.selecttabcompletor.utils.ManagerConfig;

public class TabComplete
implements TabCompleter {
    private ManagerConfig config;
    List<String> arguments = new ArrayList<String>();

    public TabComplete(ManagerConfig managerconfig) {
        this.config = managerconfig;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String msg, String[] args) {
        if (this.arguments.isEmpty()) {
            this.arguments.add("add");
            this.arguments.add("remove");
            this.arguments.add("reload");
        }
        ArrayList<String> resultat = new ArrayList<String>();
        if (sender.hasPermission("stc.admin")) {
            if (args.length == 1) {
                for (String str : this.arguments) {
                    if (!str.toLowerCase().startsWith(args[0].toLowerCase())) continue;
                    resultat.add(str);
                }
                return resultat;
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("add")) {
                    List<String> groups = this.config.getGroups();
                    for (String str1 : groups) {
                        if (!str1.toLowerCase().startsWith(args[1].toLowerCase())) continue;
                        resultat.add(str1);
                    }
                    return resultat;
                }
                return resultat;
            }
            if (args.length == 3) {
                List<String> commands;
                if (args[0].equalsIgnoreCase("remove") && (commands = this.config.getGroupList(args[1])) != null) {
                    for (String str2 : commands) {
                        if (!str2.toLowerCase().startsWith(args[2].toLowerCase())) continue;
                        resultat.add(str2);
                    }
                    return resultat;
                }
                return resultat;
            }
            if (args.length > 3) {
                return resultat;
            }
        }
        return null;
    }
}