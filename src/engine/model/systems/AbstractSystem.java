package engine.model.systems;

import java.util.ArrayList;
import java.util.List;

import engine.model.components.IComponent;
import engine.model.entities.IEntity;

/**
 * A class to handle the implementation of all the shared system methods from ISystem
 * @author Weston
 *
 * @param <A> the type of component this system stores
 */
abstract public class AbstractSystem<A extends IComponent> implements ISystem<A> {
	private List<A> myComponents;
	
	public AbstractSystem() {
		myComponents = new ArrayList<A>();
	}
	
	@Override
	public List<A> getComponents() {
		return myComponents;
	}
	
	@Override
	public A getComponent(IComponent component) {
		return (component != null) ? getComponent(component.getEntity()) : null;
	}
	
	@Override
	public A getComponent(IEntity entity) {
		for (A a: myComponents)
			if (a.getEntity().equals(entity))
				return a;
		return null;
	}

	/************ Attach and detach component methods ************/
	@Override
	public void attachComponent(A aComponet) {
		myComponents.add(aComponet);
	}

	@Override
	public void detachComponent(A aComponent) {
		myComponents.remove(aComponent);
	}
	
}
