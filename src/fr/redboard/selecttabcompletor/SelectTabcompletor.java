package fr.redboard.selecttabcompletor;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.redboard.selecttabcompletor.command.Addcommand;
import fr.redboard.selecttabcompletor.command.ManagerCmd;
import fr.redboard.selecttabcompletor.command.ReloadConfig;
import fr.redboard.selecttabcompletor.command.Removecommand;
import fr.redboard.selecttabcompletor.command.TabComplete;
import fr.redboard.selecttabcompletor.managerblocking.Blocking1;
import fr.redboard.selecttabcompletor.managerblocking.Blocking2;
import fr.redboard.selecttabcompletor.managerblocking.BlockingDoublemsg;
import fr.redboard.selecttabcompletor.utils.ManagerConfig;

public class SelectTabcompletor extends JavaPlugin implements Listener {
	public static String namePlugin = ChatColor.DARK_AQUA + "Select" + ChatColor.GREEN + "Tab" + ChatColor.AQUA
			+ "completor";
	private ManagerConfig managerconfig;
	private ReloadConfig reloadconfig;
	private Addcommand addcommand;
	private Removecommand removecommand;

	@Override
	public void onEnable() {
        this.saveDefaultConfig();
        this.managerconfig = new ManagerConfig((Plugin)this);
        this.reloadconfig = new ReloadConfig(this.managerconfig, this);
        this.addcommand = new Addcommand(this.managerconfig);
        this.removecommand = new Removecommand(this.managerconfig);
        new Addcommand(this.managerconfig);
        new Removecommand(this.managerconfig);
        this.getCommand("stc").setExecutor((CommandExecutor)new ManagerCmd(this.managerconfig, this.reloadconfig, this.addcommand, this.removecommand));
        this.getCommand("stc").setTabCompleter((TabCompleter)new TabComplete(this.managerconfig));
        
//        this.getCommand("res").setExecutor(new TestRes());
        this.getCommand("res").setTabCompleter(new TestResTab());
        
        this.selectMode((Plugin)this, (Plugin)this);
        this.managerconfig.loadData();
        int pluginId = 10509;
        MetricsLite metricsLite = new MetricsLite((Plugin)this, pluginId);
	}

	public void selectMode(Plugin Blocking2, Plugin Blocking1) {
		switch (getConfig().getString("Mode")) {
		case "1":
			// event blocking
			HandlerList.unregisterAll(Blocking2);
			// The orders placed are the orders that will be blocked
			this.getServer().getPluginManager().registerEvents(new Blocking1(managerconfig), this);
			this.getServer().getPluginManager().registerEvents(new BlockingDoublemsg(managerconfig), this);
			break;
		case "2":
			HandlerList.unregisterAll(Blocking1);
			// The Orders placed are orders that will not be blocked
			this.getServer().getPluginManager().registerEvents(new Blocking2(managerconfig), this);
			this.getServer().getPluginManager().registerEvents(new BlockingDoublemsg(managerconfig), this);
			break;
		default:
			this.getPluginLoader().disablePlugin(this);
			getServer().getConsoleSender().sendMessage(namePlugin + ChatColor.DARK_RED + " ERROR MODE" + ChatColor.GRAY
					+ ": " + ChatColor.WHITE + "Please choose between 1 or 2 !");
			break;
		}
	}
}