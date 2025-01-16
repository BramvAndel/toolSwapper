package dev.vanandel.toolSwapperPaper.listeners;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import dev.vanandel.toolSwapperPaper.toolLogic.SwitchTool;

public class PlayerInteractEventListener implements Listener {



    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction().isLeftClick() && event.getClickedBlock() != null) {
            Material blockType = event.getClickedBlock().getType();
            if (Tag.MINEABLE_SHOVEL.isTagged(blockType)){
                SwitchTool.switchToShovel(event.getPlayer().getInventory(), event.getPlayer().getUniqueId());
            } else if (Tag.MINEABLE_PICKAXE.isTagged(blockType)) {
                SwitchTool.switchToPickaxe(event.getPlayer().getInventory(), event.getPlayer().getUniqueId());
            } else if (Tag.MINEABLE_AXE.isTagged(blockType)) {
                SwitchTool.switchToAxe(event.getPlayer().getInventory(), event.getPlayer().getUniqueId());
            } else if (Tag.MINEABLE_HOE.isTagged(blockType)) {
                SwitchTool.switchToHoe(event.getPlayer().getInventory(), event.getPlayer().getUniqueId());
            }
        }
    }
}