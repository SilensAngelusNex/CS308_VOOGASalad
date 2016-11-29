package engine.model.collision_detection;

public class CollisionHandler implements ICollisionHandler{

	/**
	 * Handle collisions differently based on types that collided.
	 * Collide IFF both radii are NOT 0.
	 */
	@Override
	public void handleCollision(ICollidable moved, ICollidable unmoved) {
		if (! (moved.getRadius() == 0 || unmoved.getRadius() == 0) ) {
			moved.collideInto(unmoved);
		}
	}

}
