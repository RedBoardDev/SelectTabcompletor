package fr.redboard.selecttabcompletor.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class ManagerConfig {

	private final Plugin plugin;
	private FileConfiguration config;
	private final HashMap<String, List<String>> groupList = new HashMap<String, List<String>>();
	private final List<String> groups = new ArrayList<String>();

	public ManagerConfig(Plugin plugin) {
		this.plugin = plugin;
		reload();
	}

	public void reload() {
		plugin.reloadConfig();
		config = plugin.getConfig();
	}

	public void save() {
		plugin.saveConfig();
	}

	public <T> T get(Function<MemoryConfiguration, T> call) {
		return call.apply(config);
	}

	public MemoryConfiguration get() {
		return config;
	}

	public String getAlert() {
		return get().getString("message");
	}

	public boolean getPermOff() {
		return get().getBoolean("permissionOff");
	}

	// get groups and command-groups

	public List<String> getGroupList(String group) {
		return this.groupList.get(group);
	}

	private void addGroupList(String group, List<String> commands) {
		this.groupList.put(group, commands);
	}

	private void addGroup(String group) {
		this.groups.add(group);
	}

	public void loadData() {
		this.groups.clear();
		this.groupList.clear();
		if (get().contains("Groups")) {
			for (String key : get().getConfigurationSection("Groups").getKeys(false)) {
				ArrayList<String> commands = new ArrayList<String>(get().getStringList("Groups." + key + ".Commands"));
				this.addGroupList(key, commands);
				this.addGroup(key);
			}
		}
	}

	public List<String> getGroups() {
		return this.groups;
	}

	public void setCmd(String args, List<String> list) {
		get().set("Groups." + args + ".Commands", list);
	}
}
