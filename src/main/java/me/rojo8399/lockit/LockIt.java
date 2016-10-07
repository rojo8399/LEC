package me.rojo8399.lockit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.spongepowered.api.GameState;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import com.google.inject.Inject;

import me.rojo8399.lockit.listeners.BlockListener;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

@Plugin(id = "lockit")
public class LockIt {
	
	public static boolean ENABLED = true;
	
	static Logger logger;
	private ConfigurationNode config = null;
	
	@Inject
	private PluginContainer pluginContainer;
	
	public LockIt() {
	}
	
	@Inject
	public static void getlogger(Logger logger) {
		LockIt.logger = logger;
	}
	
	@Inject
    @DefaultConfig(sharedRoot = true)
    private File defaultConfig;
	
    @Inject
    @DefaultConfig(sharedRoot = true)
    private ConfigurationLoader<CommentedConfigurationNode> configManager;
    
    public File getDefaultConfig() {
        return this.defaultConfig;
    }
    
    public ConfigurationLoader<CommentedConfigurationNode> getConfigManager() {
        return this.configManager;
    }
    
    
    @Inject // is this needed?
    @DefaultConfig(sharedRoot = false)
    private Path config2;
    
    File file = config2.toFile();
    
	
	@Listener
	public void onGameInit(GamePreInitializationEvent e) {
		
		
		try {
			
			if (!getDefaultConfig().exists()) {
				getDefaultConfig().createNewFile();
				this.config = getConfigManager().load();
				
				this.config.getNode("ConfigVersion").setValue("1");
				
			}
			
			this.config = getConfigManager().load();
			
		} catch (IOException exception) {

           logger.error("Couldn't create default configuration file!");

        }
		
		int version = this.config.getNode("ConfigVersion").getInt();
		
		switch (version) {
		case 1 : {
				logger.info("You're using config version " + version);
			}
		}
		
		
		Sponge.getEventManager().registerListeners(this, new BlockListener(logger));
		
		logger.info("Lock It has successfully initiallized");
		
	}
	
}
