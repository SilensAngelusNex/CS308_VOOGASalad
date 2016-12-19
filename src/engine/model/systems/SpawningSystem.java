package engine.model.systems;

import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.components.IComponent;
import engine.model.components.concrete.CreatorComponent;
import engine.model.weapons.DamageInfo;
import engine.model.entities.EntityFactory;
import engine.model.strategies.ISpawningStrategy;
import engine.model.strategies.factories.SpawningStrategyFactory;

/**
 * A system that allow entities to create/spawn other entities
 * @author Weston
 *
 */
public class SpawningSystem extends AbstractSystem<CreatorComponent> implements IObserver<TimelineController>{
	private EntityFactory myEntityFactory;
	private SpawningStrategyFactory myStrategyFactory;
	
	public SpawningSystem(TimelineController time) {
		myStrategyFactory = new SpawningStrategyFactory();
		time.attach(this);	
	}
	
	/**
	 * Sets the factory used to create new entities
	 * @param e
	 */
	public void setEntityFactory(EntityFactory e) {
		myEntityFactory = e;
	}
	
	/**
	 * Gets the factory used to create new entities
	 * @param e
	 */
	public EntityFactory getFactory() {
		return myEntityFactory;
	}
	
	/**
	 * Creates a new spawning strategy named name
	 * Used for CreatorComponent construction
	 * @param name
	 * @return the new strategy
	 */
	public ISpawningStrategy newStrategy(String name) {
		return myStrategyFactory.newStrategy(name);
	}
	
	/************Observer methods ************/
	@Override
	public void update(TimelineController aChangedObject) {
		for (CreatorComponent c: getComponents())
			c.spawnIfReady();
	}

	public void updateStats(IComponent c, DamageInfo data) {

		for (CreatorComponent parent : getComponents())
			if (parent.isParent(c.getEntity()))
				parent.updateStats(data);
	}

	@Override
	public void remove(TimelineController aRemovedObject) {
		//Do nothing.
	}
}
