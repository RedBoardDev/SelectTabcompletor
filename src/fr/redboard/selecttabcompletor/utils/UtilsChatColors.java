package fr.redboard.selecttabcompletor.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class UtilsChatColors {

	public static void formatColorChat(CommandSender sender, String str) {
		if (!(str.equals(""))) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', str));
		}
	}

	public static void formatColorChat(Player p, String str) {
		if (!(str.equals(""))) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', str));
		}
	}
}