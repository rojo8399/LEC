package me.rojo8399.lec.listeners;

import java.util.Optional;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.title.Title;

import com.flowpowered.math.vector.Vector3i;

import me.rojo8399.lec.LEC;
import me.rojo8399.lec.config.BlockProtectionsConfig;

public class BlockListener {

	@Listener
	public void onBlockBreak(ChangeBlockEvent.Break event) {

		if (!LEC.ENABLED || event.isCancelled()) {
			return;
		}

		Cause cause = event.getCause();

		Optional<Player> player = cause.first(Player.class);
		Vector3i pos = event.getTransactions().get(0).getFinal().getPosition();

		// If ignoring block destruction, cancel function
		if (BlockProtectionsConfig.getConfig().get().getNode("protections", "settings", "ignoreBlockDestruction")
				.getBoolean()) {
			LEC.instance().getLog().info("LWC is ignoring block destruction.");
			return;
		}

		if (player.isPresent()) {
			Player p = player.get();

			Object blockUUID = BlockProtectionsConfig.getConfig().get().getNode("protections", pos.toString(), "owner").getValue();
			String playerUUID = p.getUniqueId().toString();
			

			if (blockUUID == null) {
				p.sendMessage(Text.of("BlockUUID: " + blockUUID));
				p.sendMessage(Text.of("playerUUID: " + playerUUID));
			} // Block is Protected and owner broke it
			else if (playerUUID.equals(blockUUID)) {
				p.sendMessage(Text.of("You broke a block you owned!"));
				BlockProtectionsConfig.getConfig().get().getNode("protections").removeChild(pos.toString());
				BlockProtectionsConfig.getConfig().save();
			} // Block isn't protected
			else if (!(playerUUID.equals(blockUUID))) {
				if (!(p.hasPermission("lec.admin.bypass.block"))) {
					event.setCancelled(true);
					p.sendMessage(Text.of("BlockUUID: " + blockUUID));
					p.sendMessage(Text.of("playerUUID: " + playerUUID));
					p.sendTitle(Title.builder().subtitle(Text.of("Protected Block")).stay(1).build());
				}
			}
		}

	}

	@Listener
	public void onBlockPlace(ChangeBlockEvent.Place event) {
		Cause cause = event.getCause();

		Optional<Player> player = cause.first(Player.class);
		Vector3i pos = event.getTransactions().get(0).getFinal().getPosition();

		if (player.isPresent()) {
			if (LEC.autoRegister) {
				BlockProtectionsConfig.getConfig().get().getNode("protections", pos.toString(), "owner")
						.setValue(player.get().getUniqueId().toString());
				BlockProtectionsConfig.getConfig().save();
			}
		}

	}

}
