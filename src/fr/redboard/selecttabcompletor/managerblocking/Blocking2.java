package fr.redboard.selecttabcompletor.managerblocking;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;

import fr.redboard.selecttabcompletor.utils.ManagerConfig;
import fr.redboard.selecttabcompletor.utils.UtilsChatColors;

public class Blocking2
implements Listener {
    private ManagerConfig config;

    public Blocking2(ManagerConfig managerconfig) {
        this.config = managerconfig;
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onCommandSend(PlayerCommandSendEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission("stc.admin") || !p.isOp()) {
            e.getCommands().clear();
            for (String group : this.config.getGroups()) {
                List<String> whiteList;
                if (!p.hasPermission("stc." + group) || (whiteList = this.config.getGroupList(group)).isEmpty()) continue;
                e.getCommands().addAll(whiteList);
            }
        }
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if (!this.config.getPermOff()) {
            Player p = e.getPlayer();
            String[] message = e.getMessage().replaceFirst("/", "").split(" ");
            if (!p.hasPermission("stc.admin") || !p.isOp()) {
                this.config.reload();
                for (String group : this.config.getGroups()) {
                    List<String> whiteList;
                    if (!p.hasPermission("stc." + group) || (whiteList = this.config.getGroupList(group)).isEmpty() || whiteList.contains(message[0])) continue;
                    UtilsChatColors.formatColorChat(p, this.config.getAlert());
                    e.setCancelled(true);
                }
            }
        }
    }
}

