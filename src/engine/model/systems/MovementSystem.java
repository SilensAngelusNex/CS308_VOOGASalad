package engine.model.systems;

import java.util.ArrayList;
import java.util.List;

import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.components.MoveableComponent;
import engine.model.components.PhysicalComponent;
import engine.model.entities.IEntity;
import engine.model.strategies.StrategyFactory;

public class MovementSystem implements IObserver<TimelineController>, ISystem {
	private List<MoveableComponent> myMoveableComponents;
	private TargetingSystem myTargeting;
	private PhysicalSystem myPhysical;
	private CollisionDetectionSystem myCollision;
	private StrategyFactory myStrategyFactory;
	
	
	public MovementSystem (PhysicalSystem physical, CollisionDetectionSystem collision, TargetingSystem targeting, 
			TimelineController time) {
		myMoveableComponents = new ArrayList<MoveableComponent>();
		myPhysical = physical;
		myTargeting = targeting;
		myCollision = collision;
		myStrategyFactory = new StrategyFactory(myPhysical);
		
		time.attach(this);
		
	}
	
	public MoveableComponent get(IEntity entity) {
		for (MoveableComponent m: myMoveableComponents)
			if (m.getEntity().equals(entity))
				return m;
		return null;
	}
	
	private void updateNextMoves() {
		for (MoveableComponent mc: myMoveableComponents) {
			mc.setGoal(myTargeting.getTarget(mc));
			PhysicalComponent p = myPhysical.get(mc);
			p.setPosition(mc.getMove(p));
			myCollision.checkCollision(p);
		}
	}
	
	/************ Attach and detach component methods ************/
	public void attachComponent(MoveableComponent aComponent) {
		myMoveableComponents.add(aComponent);
	}
	public void detachComponent(MoveableComponent aComponent) {
		myMoveableComponents.remove(aComponent);
	}
	
	/********* Observer interface ***********/
	@Override
	public void update(TimelineController aChangedObject) {
		updateNextMoves();
	}

	public StrategyFactory getStrategyFactory() {
		return myStrategyFactory;
	}
}
