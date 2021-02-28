package cn.yescallop.essentialsnk;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityPortalEnterEvent;
import cn.nukkit.event.player.*;
import cn.nukkit.level.Location;
import cn.nukkit.utils.TextFormat;
import cn.yescallop.essentialsnk.util.PortalRegion;

import java.util.Iterator;

public class EventListener implements Listener {

    private final EssentialsAPI api;

    public EventListener(EssentialsAPI api) {
        this.api = api;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        api.setLastLocation(event.getPlayer(), event.getFrom());
    }

    @EventHandler
    public void onPlayerPortal(EntityPortalEnterEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        for (PortalRegion r : api.getPortalRegion()) {
            if (!r.inRegion(player)) {
                continue;
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        Block bed = event.getBed();
        api.setHome(event.getPlayer(), "bed", Location.fromObject(bed, bed.level, 0, 0));
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        for (Player p : api.getServer().getOnlinePlayers().values()) {
            if (api.isVanished(p)) {
                player.hidePlayer(p);
            }
            if (api.isVanished(player)) {
                p.hidePlayer(player);
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        api.removeTPRequest(event.getPlayer());
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        Iterator<CommandSender> iter = event.getRecipients().iterator();

        while (iter.hasNext()) {
            CommandSender sender = iter.next();
            if (!(sender instanceof Player)) {
                continue;
            }

            if (api.isIgnoring(((Player) sender).getUniqueId(), player.getUniqueId())) {
                iter.remove();
            }
        }

        if (api.isMuted(player)) {
            event.setCancelled();
            player.sendMessage(Language.translate("commands.generic.muted", api.getUnmuteTimeMessage(player)));
        }
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (api.isMuted(player)) {
            event.setCancelled();
            player.sendMessage(Language.translate("commands.generic.muted", api.getUnmuteTimeMessage(player)));
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(event.getBlock().getId() == Block.ENCHANTING_TABLE) {
            event.getPlayer().sendMessage(TextFormat.colorize('&', "&7[&aSkyBlock&ePE&7]&r &cEnchanting tables are disabled due to the large amount of bugs. Buy enchants from players instead!"));
            event.setCancelled(true);
            return;
        }
        if(event.getBlock().getId() == Block.ANVIL) {
            event.getPlayer().sendMessage(TextFormat.colorize('&', "&7[&aSkyBlock&ePE&7]&r &cAnvils are disabled due to the large amount of bugs!"));
            event.setCancelled(true);
            return;
        }
    }
}