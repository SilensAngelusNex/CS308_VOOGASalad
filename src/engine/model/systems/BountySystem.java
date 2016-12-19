package engine.model.systems;

import engine.model.components.IComponent;
import engine.model.components.concrete.BountyComponent;
import engine.controller.PlayerController;
import engine.model.playerinfo.IModifiablePlayer;

/**
 * A system to keep track of the bounties of entities and award them to the player.
 * @author Weston
 *
 */
public class BountySystem extends AbstractSystem<BountyComponent> {
	private IModifiablePlayer myPlayer;
	
	public BountySystem(PlayerController player) {
		myPlayer = player.getPlayer(0);
	}
	
	/**
	 * Called when a c's entity reaches its goal, (an enemy reaches a player's base)
	 * @param c
	 * @return the amount of lives taken
	 */
	public int pillagePlayerBase(IComponent c) {
		BountyComponent b = getComponent(c);
		if (b == null)
			return 0;
		myPlayer.updateLivesRemaining(b.getLivesTaken());
		return b.getLivesTaken();
	}
	
	/**
	 * Called when c's entity is destroyed
	 * @param c
	 * @return the amount of bounty money
	 */
	public int collectBounty(IComponent c) {
		BountyComponent b = getComponent(c);
		if (b == null)
			return 0;
		myPlayer.updateAvailableMoney(b.getBounty());
		myPlayer.updatePoints(b.getPoints());
		return b.getPoints();
		
	}
}
