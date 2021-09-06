package io.blockchallenge;

import io.blockchallenge.module.ModuleController;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockChallengePlugin extends JavaPlugin {

    private final ModuleController moduleController = new ModuleController(this);

    @Override
    public void onEnable() {
        saveDefaultConfig();

        moduleController.loadModules();
    }

    @Override
    public void onDisable() {
        moduleController.unloadModules();
    }
}
