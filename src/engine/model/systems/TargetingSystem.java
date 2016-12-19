package engine.model.systems;

import engine.model.components.IComponent;
import engine.model.components.concrete.CreatorComponent;
import engine.model.components.concrete.MoveableComponent;
import engine.model.components.concrete.TargetingComponent;
import engine.model.strategies.IPosition;
import engine.model.strategies.ITargetingStrategy;
import engine.model.strategies.factories.TargetingStrategyFactory;

/**
 * A system that allows entities to target other entites
 * @author Weston
 *
 */
public class TargetingSystem extends AbstractSystem<TargetingComponent> {
	private transient TargetingStrategyFactory myStrategyFactory;
	
	public TargetingSystem() {
		myStrategyFactory = new TargetingStrategyFactory();
	}
	
	/**
	 * Return c's newly calculated target
	 * @param c
	 * @return
	 */
	public IPosition getTarget(MoveableComponent c) {
		TargetingComponent t = getComponent(c);
		if (t != null)
			return t.getTarget();
		return c.getGoal();
	}
	
	/**
	 * Returns c's newly calculated target
	 * @param c
	 * @return
	 */
	public IPosition getTarget(CreatorComponent c) {
		TargetingComponent t = getComponent(c);
		if (t != null)
			return t.getTarget();
		return c.getTarget();
	}
	
	/**
	 * Checks if c's entity has a component in myComponents
	 * @param c
	 * @return true iff it does
	 */
	public boolean canTarget(IComponent c) {
		return getComponent(c) != null;
	}

	/**
	 * Creates a new targeting strategy named name
	 * Used for targetingComponent construction
	 * @param name
	 * @return the new strategy
	 */
	public ITargetingStrategy newStrategy(String name) {
		return myStrategyFactory.newStrategy(name);
	}
}
