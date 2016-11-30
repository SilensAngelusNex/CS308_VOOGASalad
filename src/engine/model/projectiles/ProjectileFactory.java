package engine.model.projectiles;

import java.util.Map;

import authoring.model.ProjectileData;
import engine.controller.timeline.TimelineController;
import engine.model.game_environment.MapMediator;
import engine.model.machine.Machine;
import engine.model.weapons.IKillerOwner;
import utility.ResouceAccess;

/**
 * A class to make projectiles and store everything that all the projectiles will have in common, so the common
 * data won't need to be passed around. 
 * @author Weston
 *
 */
public class ProjectileFactory {
	private static final String NO_SUCH_PROJECTILE = "NoSuchProjectile";
	private Map<String, ProjectileData> myProjectiles;
	private TimelineController myTime;
	private MapMediator myMap;
	
	public ProjectileFactory(Map<String, ProjectileData> projMap, TimelineController time, MapMediator map) {
		myTime = time;
		myProjectiles = projMap;
		myMap = map;
		
	}

	/**
	 * Makes a projectile from the data for name, targeting target owned by owner.
	 * @param name
	 * @param target
	 * @param owner
	 * @return new Projectile
	 */
	public Projectile newProjectile(String name, Machine target, IKillerOwner owner) {
		if (myProjectiles.containsKey(name))
			return new Projectile(myProjectiles.get(name), target, owner, myTime, myMap);
		else
			throw new UnsupportedOperationException(ResouceAccess.getError(NO_SUCH_PROJECTILE) + name);
	}
}
