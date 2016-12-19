package engine.model.systems;

import engine.model.components.IComponent;
import engine.model.components.concrete.TeamComponent;

/**
 * A system that tracks which team entities belong to.
 * @author Weston
 *
 */
public class TeamSystem extends AbstractSystem<TeamComponent> {

	/**
	 * Checks if a and b are enemies. If one or both have no team data, default is that they are enemies
	 * @param a
	 * @param b
	 * @return false iff a and b are allies
	 */
	public boolean areEnemies(IComponent a, IComponent b) {
		TeamComponent aTeam = getComponent(a);
		TeamComponent bTeam = getComponent(b);
		if (aTeam != null && bTeam != null)
			return !getComponent(a).getTeamID().equals(getComponent(b).getTeamID());
		else {
			//If they aren't on teams they are everyone's enemy, I guess.
			return true;
		}
	}

	/**
	 * Checks if a and b are allies. If one or both have no team data, default is that they are enemies
	 * @param a
	 * @param b
	 * @return true iff a and b are allies
	 */
	public boolean areAllies(IComponent a, IComponent b) {
		return !areEnemies(a, b);
	}
}
