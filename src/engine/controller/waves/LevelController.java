package engine.controller.waves;

import authoring.controller.LevelDataContainer;
import authoring.controller.MapDataContainer;
import authoring.model.EntityData;
import engine.IObserver;
import engine.controller.timeline.TimelineController;
import engine.model.data_stores.DataStore;
import engine.model.entities.EntityFactory;
import engine.model.systems.MovementSystem;
import engine.model.systems.PhysicalSystem;
/**
 * 
 * @author owenchung
 *
 */
public class LevelController implements IObserver<TimelineController> {
	private static final double DEFAULT_START_TIME = 0.0;
	private LevelDataContainer myLevelDataContainer;
	private WaveController myWaveController;
	private DataStore<EntityData> myEntityDataStore;
	private int myCurrentLevel;
	private EntityFactory myEntityFactory;
	private PhysicalSystem myPhysicalSystem;
	private MovementSystem myMovementSystem;
	private MapDataContainer myMapDataContainer;
	
	public LevelController(
			LevelDataContainer aGameLevelsData,
			int aStartingLevel,
			DataStore<EntityData> aEntityDataStore,
			EntityFactory aEntityFactory,
			PhysicalSystem aPhysicalSystem,
			MovementSystem aMovementSystem,
			MapDataContainer aMapDataContainer) {
		myLevelDataContainer = aGameLevelsData;
		myEntityDataStore = aEntityDataStore;
		myCurrentLevel = aStartingLevel;
		myEntityFactory = aEntityFactory;
		myPhysicalSystem = aPhysicalSystem;
		myMovementSystem = aMovementSystem;
		myMapDataContainer = aMapDataContainer;
	
	}

	@Override
	//*******************Observer interface***************//
	public void update(TimelineController aChangedObject) {
		if (myWaveController == null) {
			myWaveController = new WaveController(
					myEntityDataStore,
					myLevelDataContainer.getLevelData(myCurrentLevel),
					DEFAULT_START_TIME,
					myEntityFactory,
					myPhysicalSystem,
					myMovementSystem,
					myMapDataContainer);
		}
		if (myWaveController.isLevelFinished()) {
			myCurrentLevel ++;
			if (myLevelDataContainer.hasLevel(myCurrentLevel)) {
				myWaveController.newWave(myEntityDataStore, myLevelDataContainer.getLevelData(myCurrentLevel), aChangedObject.getTotalTimeElapsed());
			}
		}
		System.out.println("distributing Entities");
		myWaveController.distributeEntities(aChangedObject.getTotalTimeElapsed());
	}
}
