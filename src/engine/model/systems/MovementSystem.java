package engine.model.systems;

import java.util.ArrayList;
import java.util.List;

import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.components.concrete.MoveableComponent;
import engine.model.game_environment.MapMediator;
import engine.model.strategies.IMovementStrategy;
import engine.model.strategies.factories.MovementStrategyFactory;

/**
 * A system to keep track of and move movable components
 * @author Weston
 *
 */
public class MovementSystem extends AbstractSystem<MoveableComponent> implements IObserver<TimelineController> {
	private transient MovementStrategyFactory myStrategyFactory;
	
	public MovementSystem (MapMediator map, TimelineController time) {
		myStrategyFactory = new MovementStrategyFactory(map);
		time.attach(this);
	}

	/**
	 * Creates a new movement strategy named name
	 * Used for MovementComponent construction
	 * @param name
	 * @return the new strategy
	 */
	public IMovementStrategy newStrategy(String name) {
		return myStrategyFactory.newStrategy(name);
	}
	
	/********* Observer interface ***********/
	@Override
	public void update(TimelineController aChangedObject) {
		List<MoveableComponent> components = new ArrayList<MoveableComponent>();
		components.addAll(getComponents());
		for (MoveableComponent mc :components) {
			mc.move();
		}
	}
	@Override
	public void remove(TimelineController aRemovedObject) {
		//Do nothing.
	}
}
