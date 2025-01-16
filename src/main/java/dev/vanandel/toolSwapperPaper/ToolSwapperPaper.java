package dev.vanandel.toolSwapperPaper;

import dev.vanandel.toolSwapperPaper.commands.*;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ToolSwapperPaper extends JavaPlugin {

    private static ToolSwapperPaper instance;

    private static Permission perms = null;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        setupPermissions();
        registerCommands();
        getLogger().info("ToolSwapperPaper enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("ToolSwapperPaper disabled!");
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("test")).setExecutor(new TestCommand());
        Objects.requireNonNull(getCommand("send")).setExecutor(new SendCommand());
    }

    private boolean setupPermissions() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            getLogger().warning("- Disabled because Vault is not installed!");
            getServer().getPluginManager().disablePlugin(this);
            return false;
        }
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        if (rsp == null) {
            return false;
        }
        perms = rsp.getProvider();
        return perms != null;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static ToolSwapperPaper getInstance() {
        return instance;
    }
}
