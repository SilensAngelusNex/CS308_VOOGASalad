package engine.model.game_environment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import engine.model.components.PhysicalComponent;
import engine.model.game_environment.paths.PathFactory;
import engine.model.game_environment.paths.PathManager;
import engine.model.game_environment.terrain.TerrainMap;
import engine.model.machine.Machine;
import engine.model.machine.MachineFactory;
import engine.model.strategies.IPhysical;
import utility.Point;

//TODO: update this class to ECS
@Deprecated
public class MapMediator {
	private PathFactory myPathFactory;
	
	private TerrainMap myTerrainMap;
	private ArrayList<PhysicalComponent> myEntityManager;
	private PathManager myPathManager;
	private MachineFactory myAnarchosyndacalistCommune;
	
	//TODO: Change this constructor so that it hides away the terrain map
	// so constructor could take in terrain map data instead of terrain map
	// this makes the ownership model more explicit
	public MapMediator(TerrainMap aTerrainMap) {
		myTerrainMap = aTerrainMap;
		myEntityManager = new ArrayList<PhysicalComponent>();
		myPathFactory = new PathFactory();
		myPathManager = myPathFactory.constructPaths(myTerrainMap);

	}
	
	/**
	 * Determines if an object can be placed on the map at the requested location
	 * Places the object if it can
	 * @param aLocation to place the object
	 * @param aValidTerrainList: a list of terrains the object can be placed on
	 * @return true if the entity was placed, false otherwise
	 */
	public boolean attemptToPlaceEntity(Point aLocation, PhysicalComponent aPhysicalComponent)
	{
		/*
		if (myTerrainMap.hasTerrain(aPhysicalComponent.getValidTerrains(), aLocation)) {
			aPhysicalComponent.setPosition(new Pair<Double, Point>(0.0, aLocation));
			accept(aPhysicalComponent, aLocation);
			return true;
		}
		*/
		return false;
	}
	
	private void accept(PhysicalComponent aPhysicalComponent, Point aLocation) {
		// TODO Auto-generated method stub
	}

	public boolean attemptToPlaceEntity(Point aLocation, Machine aPhysicalComponent)
	{
		/*
		if (myTerrainMap.hasTerrain(aPhysicalComponent.getValidTerrains(), aLocation)) {
			aPhysicalComponent.setPosition(new Pair<Double, Point>(0.0, aLocation));
			accept(aPhysicalComponent, aLocation);
			return true;
		}
		*/
		return false;
	}
	
	@Deprecated
	public List<PhysicalComponent> withinRange(Point p, double radius){
		Stream<PhysicalComponent> s = myEntityManager.stream();
		
		s.filter(e -> isEnemy(e) && isInRadius(e, p, radius));
		
		return s.collect(Collectors.toList());
		
	}

	private boolean isInRadius(IPhysical e, Point p, double radius) {
		/*
		return e.getPosition().euclideanDistance(p) - e.getCollisionRadius() - radius >= 0;
		*/
		return false;
	}

	@Deprecated //please don't use instanceof unless completely unavoidable. If necessary, please document why
	private boolean isEnemy(IPhysical e) {
		/*
		return e instanceof Enemy;
		*/
		return false;
	}


}
