package me.rojo8399.lec.api;

import org.spongepowered.api.entity.living.player.Player;

import com.flowpowered.math.vector.Vector3i;

import me.rojo8399.lec.config.BlockProtectionsConfig;

public class lecAPI {
	public static void protectBlock(Vector3i location, Player owner) {
		BlockProtectionsConfig.getConfig().get().getNode("protections").removeChild(location.toString());
		BlockProtectionsConfig.getConfig().save();
	}
}
