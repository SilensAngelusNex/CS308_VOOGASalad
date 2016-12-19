// This entire file is part of my masterpiece.
// Weston Carvalho

package engine.model.systems;

import java.util.ArrayList;
import java.util.List;

import engine.model.components.concrete.CollidableComponent;
import engine.model.components.concrete.PhysicalComponent;

/**
 * A system to manage collision detection in the game
 * Entities with the proper components can register with the
 * Collision detection system so that collisions may be reported
 * @author Weston
 * @author Alan
 *
 */
public class CollisionDetectionSystem extends AbstractSystem<CollidableComponent> {
	
	/**
	 * Checks if a physical component collides with anything in
	 * it's collision radius.
	 * @param the physical component that changed position
	 */
	public void checkCollision(PhysicalComponent movedPhysical) {
		CollidableComponent movedCollidable = getComponent(movedPhysical);
		if (movedCollidable != null) {
			//The creation of this list prevents the danger of modifying the list returned by getComponents() while it is being iterated over. 
			List<CollidableComponent> iterate = new ArrayList<CollidableComponent>(getComponents());
			
			for (CollidableComponent unmovedCollidable: iterate)	
				movedCollidable.checkCollision(unmovedCollidable);
		}
	}
}
