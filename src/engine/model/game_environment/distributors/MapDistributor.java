package engine.model.game_environment.distributors;

import authoring.model.EnemyData;
import authoring.model.ProjectileData;
import authoring.model.TowerData;
import authoring.model.WeaponData;
import engine.IObservable;
import engine.controller.PlayerController;
import engine.controller.timeline.TimelineController;
import engine.controller.waves.DummyEntityData;
import engine.model.components.PhysicalComponent;
import engine.model.components.PhysicalComponentData;
import engine.model.data_stores.DataStore;
import engine.model.entities.EntityData;
import engine.model.entities.EntityFactory;
import engine.model.entities.IEntity;
import engine.model.game_environment.MapMediator;
import engine.model.machine.Machine;
import engine.model.machine.MachineFactory;
import engine.model.machine.tower.Tower;
import engine.model.playerinfo.Player;
import engine.model.resourcestore.ResourceStore;
import gamePlayerView.gamePlayerView.Router;
import utility.Point;

/**
 * This class manages distributing entities to the map
 * given data to construct a entity
 * @author matthewfaw
 *
 */
public class MapDistributor implements IDistributor {
	private MapMediator myMapMediator;
	private EntityFactory myEntityFactory;
	private MachineFactory myAnarchosyndacalistCommune;
	private ResourceStore myResourceStore;
	private Router myRouter;
	
	public MapDistributor(MapMediator aMapMediator,
			ResourceStore aTowerDataStore,
			DataStore<DummyEntityData> aEnemyDataStore,
			DataStore<WeaponData> aWeaponDataStore,
			DataStore<ProjectileData> aProjectileDataStore,
			TimelineController aTimelineController,
			Router aRouter
			) {
		myRouter = aRouter;
		myMapMediator = aMapMediator;
		myEntityFactory = new EntityFactory();
		myResourceStore = aTowerDataStore;
		myAnarchosyndacalistCommune = new MachineFactory(
				aTimelineController,
				myResourceStore,
				aEnemyDataStore,
				aWeaponDataStore,
				aProjectileDataStore,
				aMapMediator
				);
	}
	
	public void distribute(String aTowerName, PlayerController aPlayerController, Point aLocation)
	{
		Tower tower = myAnarchosyndacalistCommune.newTower(aTowerName, aPlayerController.getActivePlayer(), aLocation);
		if (myMapMediator.attemptToPlaceEntity(aLocation, tower)) {
			myRouter.distributeViewableComponent(tower);
			int price = myResourceStore.getTowerPrice(tower.getName());
			aPlayerController.getActivePlayer().updateAvailableMoney(-price);
		}
//		return true;
	}

	/**
	 * Constructs the Entity object, and places it on the map, if the location
	 * is valid
	 */
	@Override
	public boolean distribute(EntityData aEntityData, PhysicalComponentData aPhysicalComponentData, Point aLocation)
	{
		//TODO: possibly check if machine can be placed before constructing the object?
		//1: construct object 
		//2: determine if the object can be placed on the map
		//3: distribute object to the map, if it can be placed there
		IEntity entity = myEntityFactory.constructEntity(aEntityData);
		PhysicalComponent physicalComponent = new PhysicalComponent(aPhysicalComponentData);
		entity.addComponent(physicalComponent);
		
		return myMapMediator.attemptToPlaceEntity(aLocation, physicalComponent);
	}
	
	//TODO: add the implementation
	//TODO: I'm imagining that EnemyData and TowerData are going to have similar interfaces, so we're probs
	// gonna have to repeat this code--it'd be nice if we could add an interface around the Enemy/Tower data objects
	// to get rid of this repeated code
	public boolean distribute(EnemyData aEnemyData, String aSpawnPoint, IObservable<TimelineController> aObservableTC)
	{
		//TODO: Construct the enemy object
		// return myMapMediator.attemptToPlaceEntity(aSpawnPoint, enemy);
		throw new RuntimeException("Add implementation here");
	}
	/* TODO: should be changed
	@Deprecated
	public void distribute(EnemyData random, TimelineController aChangedObject, Player p) {
		Point loc = new Point(1,1);
		Machine toAdd = myAnarchosyndacalistCommune.newEnemy(random.getName(), p, loc);
		
		if (myMapMediator.attemptToPlaceEntity(loc, toAdd)) {
			myRouter.distributeViewableComponent(toAdd);
		}
	}
	 */

}
