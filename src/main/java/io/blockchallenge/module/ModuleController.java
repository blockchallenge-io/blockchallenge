package io.blockchallenge.module;

import io.blockchallenge.BlockChallengePlugin;
import io.blockchallenge.base.BlockChallengeModule;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;

public class ModuleController {

    private final BlockChallengePlugin plugin;
    private final Map<Class<? extends BlockChallengeModule>, BlockChallengeModule> modules = new HashMap<>();

    public ModuleController(BlockChallengePlugin plugin) {
        this.plugin = plugin;
    }

    private void findModules() {
        Reflections reflections = new Reflections("io.blockchallenge");
        reflections.getSubTypesOf(BlockChallengeModule.class).forEach(cls -> {
            modules.put(cls, null);
        });
    }

    public void loadModules() {
        findModules();
        for(Map.Entry<Class<? extends BlockChallengeModule>, BlockChallengeModule> entry : modules.entrySet()) {
            if(entry.getValue() == null) {
                try {
                    entry.setValue(entry.getKey().getConstructor().newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(entry.getValue() != null) {
                entry.getValue().onLoad();
            }
        }
    }

    public void unloadModules() {
        for(Map.Entry<Class<? extends BlockChallengeModule>, BlockChallengeModule> entry : modules.entrySet()) {
            if(entry.getValue() != null) {
                entry.getValue().onUnload();
            }
        }
    }

}
