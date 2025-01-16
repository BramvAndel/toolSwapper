package dev.vanandel.toolSwapperPaper.toolLogic;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.UUID;

public class SwitchTool {

    public static void switchToPickaxe(PlayerInventory inventory, UUID playerId) {
        switchToTool(inventory, playerId, "_PICKAXE");
    }

    public static void switchToAxe(PlayerInventory inventory, UUID playerId) {
        switchToTool(inventory, playerId, "_AXE");
    }

    public static void switchToHoe(PlayerInventory inventory, UUID playerId) {
        switchToTool(inventory, playerId, "_HOE");
    }

    public static void switchToShovel(PlayerInventory inventory, UUID playerId) {
        switchToTool(inventory, playerId, "_SHOVEL");
    }

    public static void switchToTool(PlayerInventory inventory, UUID playerId, String toolSuffix) {
        ItemStack currentItem = inventory.getItemInMainHand();
        if (currentItem.getType().toString().endsWith(toolSuffix)) {
            return;
        }

        int firstAvailableSlot = SearchInventory.findFirstAvailableSlot(inventory);
        int toolSlot = SearchInventory.findToolInInventory(inventory, toolSuffix);

        if (toolSlot != -1) {
            // Check if there is already a preferred tool in the hotbar
            for (int i = 0; i < 9; i++) {
                ItemStack hotbarItem = inventory.getItem(i);
                if (hotbarItem != null && hotbarItem.getType().toString().endsWith(toolSuffix)) {
                    inventory.setHeldItemSlot(i);
                    return;
                }
            }

            ItemStack toolItem = inventory.getItem(toolSlot);
            ItemStack secondSlotItem = inventory.getItem(1);
            inventory.setItem(toolSlot, secondSlotItem);
            // No preferred tool in the hotbar, replace the first available slot with the preferred tool
            if (firstAvailableSlot != -1 && firstAvailableSlot < 9) {
                inventory.setItem(firstAvailableSlot, toolItem);
                inventory.setHeldItemSlot(firstAvailableSlot);
            } else {
                // No available slot in the hotbar, swap the second slot with the tool
                inventory.setItem(1, toolItem);
                inventory.setHeldItemSlot(1);
            }
        }
    }
}