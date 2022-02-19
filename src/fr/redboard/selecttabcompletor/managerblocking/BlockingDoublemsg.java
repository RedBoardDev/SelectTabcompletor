package fr.redboard.selecttabcompletor.managerblocking;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;

import fr.redboard.selecttabcompletor.utils.ManagerConfig;
import fr.redboard.selecttabcompletor.utils.UtilsChatColors;

public class BlockingDoublemsg implements Listener {
	private ManagerConfig config;

	public BlockingDoublemsg(ManagerConfig managerconfig) {
		this.config = managerconfig;
	}

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onCommandSends(PlayerCommandSendEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission("stc.admin") || !p.isOp()) {
            this.config.reload();
            System.out.println("debug0");
            for (String group : this.config.getGroups()) {
                List<String> whiteList;
                System.out.println("debug1");
                System.out.println(this.config.getGroupList(group));
                if (!p.hasPermission("stc." + group) || (whiteList = this.config.getGroupList(group)).isEmpty()) continue;
                e.getCommands().removeIf(str -> whiteList.contains(str));
            }
        }
    }

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommand(PlayerCommandPreprocessEvent e) {
		if (!this.config.getPermOff()) {
			Player p = e.getPlayer();

			List<String> whiteList = new ArrayList<String>();
			whiteList.add("res");
			whiteList.add("bank");

			String[] message = e.getMessage().replaceFirst("/", "").split(" ");
			if (!p.hasPermission("stc.admin") || !p.isOp()) {
				this.config.reload();

				try {
					if (message[1] != null || !message[1].isEmpty()) {

						if (whiteList.contains(message[0]) && whiteList.contains(message[1])) {
							UtilsChatColors.formatColorChat(p, this.config.getAlert());
							e.setCancelled(true);
						}
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		}
	}
}
