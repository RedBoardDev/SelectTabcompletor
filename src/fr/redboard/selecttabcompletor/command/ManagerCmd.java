package fr.redboard.selecttabcompletor.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.redboard.selecttabcompletor.SelectTabcompletor;
import fr.redboard.selecttabcompletor.utils.ManagerConfig;
import fr.redboard.selecttabcompletor.utils.UtilsChatColors;

public class ManagerCmd
implements CommandExecutor {
    private ReloadConfig reload;
    private ManagerConfig config;
    private Addcommand add;
    private Removecommand remove;

    public ManagerCmd(ManagerConfig managerconfig, ReloadConfig reload, Addcommand addcmd, Removecommand removecmd) {
        this.config = managerconfig;
        this.reload = reload;
        this.add = addcmd;
        this.remove = removecmd;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (cmd.getName().equalsIgnoreCase("stc") && sender.hasPermission("stc.admin")) {
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("add")) {
                    this.add.addCmd(args, sender);
                }
                if (args[0].equalsIgnoreCase("remove")) {
                    this.remove.removeCmd(args, sender);
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    this.reload.reloadconfig(sender);
                }
            } else {
                sender.sendMessage(String.valueOf(SelectTabcompletor.namePlugin) + "\u00a77: \u00a7fIncorrect syntax: /stc add/remove <group> <command>");
            }
        } else {
            UtilsChatColors.formatColorChat(sender, this.config.getAlert());
        }
        return false;
    }
}

