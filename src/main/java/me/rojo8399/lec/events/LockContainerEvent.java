package me.rojo8399.lec.events;

import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.cause.Cause;
public class LockContainerEvent implements Cancellable {
	
	private final Cause cause;
    private boolean cancelled = false;
    
    public LockContainerEvent(Cause cause) {
    	this.cause = cause;
	}

	@Override
	public boolean isCancelled() {
		return this.isCancelled();
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}

}
