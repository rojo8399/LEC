package me.rojo8399.lec;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import com.google.inject.Inject;

import me.rojo8399.lec.config.Config;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

@Plugin(id = "lec", name = "Lock Entities & Items", version = "0.0.1", authors = "rojo8399")
public class LEC {

	private static PluginContainer plugin;
	private static LEC instance;

	@Inject
	private Logger log;

	@Inject
	@ConfigDir(sharedRoot = false)
	private Path configDir;

	@Inject
	@DefaultConfig(sharedRoot = false)
	private ConfigurationLoader<CommentedConfigurationNode> configManager;

	public CommentedConfigurationNode configurationNode;

	@Listener
	public void onPreInitializationEvent(GamePreInitializationEvent event) {
		getLog().info("LEC is loading...");
		instance = this;
		plugin = Sponge.getPluginManager().getPlugin("lec").get();
		
		// Create Configuration Directory for LEC
		if (!Files.exists(configDir)) {
			try {
				Files.createDirectories(configDir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Create Chests Directory for LEC
		if (!Files.exists(configDir.resolve("data"))) {
			try {
				Files.createDirectories(configDir.resolve("data"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Create config.conf
		Config.getConfig().setup();
		
		
	}

	@Listener
	public void onInitializationEvent(GameInitializationEvent event) {
	}

	@Listener
	public void onPostInitializationEvent(GamePostInitializationEvent event) {

		// Register Listeners
		// Register Commands
		// Register Events

	}

	public Logger getLog() {
		return log;
	}

	public static PluginContainer getPlugin() {
		return plugin;
	}

	public static LEC instance() {
		return instance;
	}

	public Path getConfigDir() {
		return configDir;
	}

}
