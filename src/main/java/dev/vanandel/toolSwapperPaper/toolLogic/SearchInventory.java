package dev.vanandel.toolSwapperPaper.toolLogic;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class SearchInventory {
    public static int findFirstAvailableSlot(PlayerInventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item == null || item.getType() == Material.AIR ||
                    item.getType().toString().endsWith("_SHOVEL") ||
                    item.getType().toString().endsWith("_PICKAXE") ||
                    item.getType().toString().endsWith("_AXE") ||
                    item.getType().toString().endsWith("_HOE")) {
                return i;
            }
        }
        return -1; // No space found
    }

    public static int findToolInInventory(PlayerInventory inventory, String toolSuffix) {
        int bestSlot = -1;
        double bestEfficiency = Double.MAX_VALUE;

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null && item.getType().toString().endsWith(toolSuffix)) {
                double efficiency = getToolEfficiency(item);
                if (efficiency < bestEfficiency) {
                    bestEfficiency = efficiency;
                    bestSlot = i;
                }
            }
        }

        return bestSlot;
    }

    private static double getToolEfficiency(ItemStack item) {
        Material material = item.getType();
        switch (material) {
            case NETHERITE_SHOVEL:
            case NETHERITE_PICKAXE:
            case NETHERITE_AXE:
            case NETHERITE_HOE:
                return 0.5; // Highest tier
            case DIAMOND_SHOVEL:
            case DIAMOND_PICKAXE:
            case DIAMOND_AXE:
            case DIAMOND_HOE:
                return 1.0; // Second highest tier
            case IRON_SHOVEL:
            case IRON_PICKAXE:
            case IRON_AXE:
            case IRON_HOE:
                return 2.0; // Third highest tier
            case STONE_SHOVEL:
            case STONE_PICKAXE:
            case STONE_AXE:
            case STONE_HOE:
                return 3.0; // Fourth highest tier
            case WOODEN_SHOVEL:
            case WOODEN_PICKAXE:
            case WOODEN_AXE:
            case WOODEN_HOE:
            case GOLDEN_SHOVEL:
            case GOLDEN_PICKAXE:
            case GOLDEN_AXE:
            case GOLDEN_HOE:
                return 4.0; // Lowest tier
            default:
                return Double.MAX_VALUE; // Not a tool
        }
    }
}
