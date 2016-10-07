package me.rojo8399.lec.listeners;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.data.Transaction;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.cause.Cause;

import me.rojo8399.lec.LEC;

public class BlockListener {
	
	Logger logger;

	public BlockListener(Logger logger) {
		this.logger = logger;
	}

	@Listener
	public void onBlockPlace(ChangeBlockEvent.Place e) {
		
		if (!LEC.ENABLED) {
			return;
		}
		
		Cause cause = e.getCause();
		
		Optional<Player> oplayer = cause.first(Player.class);
		Player player = null;
		
		if (oplayer.isPresent()) {
			player = oplayer.get();
		}
		
		List<Transaction<BlockSnapshot>> transactions = e.getTransactions();
		BlockState block = transactions.get(0).getFinal().getExtendedState();
		
		if (player.hasPermission("lockit.admin.bypass.place")) {
			return;
		}
		
		
		
	}
	
}
