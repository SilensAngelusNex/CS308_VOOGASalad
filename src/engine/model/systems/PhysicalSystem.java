package engine.model.systems;

import java.util.List;
import java.util.stream.Collectors;

import engine.model.components.concrete.PhysicalComponent;
import engine.model.game_environment.MapMediator;
import utility.Point;

/**
 * A system to keep track of all the entities positios on the map
 * @author Weston
 *
 */
public class PhysicalSystem extends AbstractSystem<PhysicalComponent> {
	private transient MapMediator myMap;
	
	public PhysicalSystem(MapMediator map) {
		myMap = map;
	}
	
	/**
	 * Gets the map
	 * @return MapMediator
	 */
	public MapMediator getMap() {
		return myMap;
	}

	/**
	 * Gets all entities within an arc on the map
	 * @param pos
	 * @param radius
	 * @param heading
	 * @param width
	 * @return all entites within the defined arc
	 */
	public List<PhysicalComponent> withinRange(Point pos, double radius, double heading, double width) {
		return getComponents()
							.stream()
							.filter(p -> isWithin(p, pos, radius, heading, width))
							.collect(Collectors.toList());
	}
	
	private boolean isWithin(PhysicalComponent p, Point pos, double radius, double heading, double width) {
		return pos.getDistanceTo(p.getPosition()) <= radius &&
				Math.abs(pos.towards(p.getPosition()) - heading) <= width;
	}
}
