/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.configuration.file.FileConfiguration
 */
package fr.redboard.selecttabcompletor.command;

import java.util.List;

import org.bukkit.command.CommandSender;

import fr.redboard.selecttabcompletor.SelectTabcompletor;
import fr.redboard.selecttabcompletor.utils.ManagerConfig;

public class Removecommand {
    private ManagerConfig config;

    public Removecommand(ManagerConfig managerconfig) {
        this.config = managerconfig;
    }

    public void removeCmd(String[] args, CommandSender sender) {
        if (args.length == 3) {
            if (this.config.getGroups().contains(args[1])) {
                List<String> whiteList = this.config.getGroupList(args[1]);
                if (whiteList.contains(args[2])) {
                    whiteList.remove(args[2]);
                    this.config.setCmd(args[1], whiteList);
                    this.config.save();
                    this.config.reload();
                    sender.sendMessage(String.valueOf(SelectTabcompletor.namePlugin) + " \u00a7fThe command \u00a76" + args[2] + " \u00a7fhas been deleted to the group \u00a76" + args[1] + "\u00a7f.");
                } else {
                    sender.sendMessage(String.valueOf(SelectTabcompletor.namePlugin) + "\u00a77: \u00a7fThis order has already been registered !");
                }
            } else {
                sender.sendMessage(String.valueOf(SelectTabcompletor.namePlugin) + "\u00a77: \u00a7fThere is no group of this name registered !");
            }
        } else {
            sender.sendMessage(String.valueOf(SelectTabcompletor.namePlugin) + "\u00a77: \u00a7fIncorrect syntax: /stc remove <group> <command>");
        }
    }
}

