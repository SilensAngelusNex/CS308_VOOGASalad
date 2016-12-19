package engine.model.systems;

import engine.model.components.IComponent;
import engine.model.components.concrete.DamageDealingComponent;
import engine.model.strategies.IDamageStrategy;
import engine.model.strategies.factories.DamageStrategyFactory;

/**
 * A system to handle entities dealing damage to other entities
 * @author Weston
 *
 */
public class DamageDealingSystem extends AbstractSystem<DamageDealingComponent> {
	private DamageStrategyFactory myStrategyFactory;

	public DamageDealingSystem() {
		myStrategyFactory = new DamageStrategyFactory();
	}

	/**
	 * Makes a deal damage to b and explode, if applicable
	 * @param a
	 * @param b
	 */
	public void dealDamageToTarget(IComponent a, IComponent b) {
		if (getComponent(a) != null && !a.equals(b))
			getComponent(a).explode(b);
	}

	/**
	 * Makes c's entity explode, possibly dealing damage to nearby entities
	 * @param c
	 */
	public void explode(IComponent c) {
		DamageDealingComponent dmg = getComponent(c);
		if(dmg != null)
			dmg.explode();
		else {
			c.getEntity().delete();
		}
	}
	
	/**
	 * Creates a new damage strategy named name
	 * Used for DamageDealingComponent construction
	 * @param name
	 * @return the new strategy
	 */
	public IDamageStrategy newStrategy(String name) {
		return myStrategyFactory.newStrategy(name);
	}
}
