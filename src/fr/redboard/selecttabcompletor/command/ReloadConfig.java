package fr.redboard.selecttabcompletor.command;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import fr.redboard.selecttabcompletor.SelectTabcompletor;
import fr.redboard.selecttabcompletor.utils.ManagerConfig;

public class ReloadConfig {
    private ManagerConfig config;
    private SelectTabcompletor stc;

    public ReloadConfig(ManagerConfig managerconfig, SelectTabcompletor stc) {
        this.config = managerconfig;
        this.stc = stc;
    }

    public void reloadconfig(CommandSender sender) {
        this.config.reload();
        sender.sendMessage(String.valueOf(SelectTabcompletor.namePlugin) + " \u00a7freloading successfully completed !");
        this.stc.selectMode((Plugin)this.stc, (Plugin)this.stc);
    }
}

