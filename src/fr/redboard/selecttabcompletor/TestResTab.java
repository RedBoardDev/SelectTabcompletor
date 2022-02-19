package fr.redboard.selecttabcompletor;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TestResTab implements TabCompleter {

	List<String> arguments1 = new ArrayList<>();

	public List<String> onTabComplete(CommandSender sender, Command cmd, String msg, String[] args) {
		if (this.arguments1.isEmpty()) {
			this.arguments1.add("give");
			this.arguments1.add("remove");
			this.arguments1.add("changePseudo");
			this.arguments1.add("check");
		}

		List<String> resultat = new ArrayList<>();
		if (args.length == 1) {
			for (String str : this.arguments1) {
				if (str.toLowerCase().startsWith(args[0].toLowerCase())) {
					resultat.add(str);
				}
			}
			return resultat;
		}
		return null;
	}
}