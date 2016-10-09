package me.rojo8399.lec.events;

import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.cause.Cause;

public class UnprotectBlockEvent implements Cancellable {
	
	private final Cause cause;
	
	public UnprotectBlockEvent(Cause cause) {
		this.cause = cause;
	}
	
	

	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCancelled(boolean cancel) {
		// TODO Auto-generated method stub
		
	}

}
