package engine.controller.waves;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import authoring.controller.LevelDataContainer;
import authoring.model.EntityData;
import authoring.model.GameLevelsData;
import authoring.model.LevelData;
import authoring.model.OneLevelData;
import authoring.model.WaveData;
import engine.model.data_stores.DataStore;

/**
 * A class intended to manage which waves are currently active
 * Provides a simple interface to retrieve which Enemies to be constructed
 * at a given time step 
 * @author matthewfaw and owenchung
 *
 */
public class ActiveWaveManager {
	private static final double DEFAULT_START_TIME = 0.0;
	private LevelDataContainer myLevelDataContainer;
	private LevelData myCurrLevelData;
	private String myCurrLevel;
	private DataStore<EntityData> myEnemyDataStore;
	
//	private LinkedHashMap<WaveData, Integer> myUnreleasedEnemyCountForActiveWave;
	private List<WaveState> myWaveStates;
	private double myCurrentTime;
	private double myTimeToAddMoreWaves;
	
	public ActiveWaveManager(LevelDataContainer aLevelDataContainer, DataStore<EntityData> aEnemyDataStore)
	{
		myCurrLevel = "0";
		myLevelDataContainer = aLevelDataContainer;
		myEnemyDataStore = aEnemyDataStore;
		myCurrLevelData = myLevelDataContainer.getLevelData(myCurrLevel);
//		myUnreleasedEnemyCountForActiveWave = new LinkedHashMap<WaveData, Integer>();
		myWaveStates = new ArrayList<WaveState>();
		
		setCurrentTime(DEFAULT_START_TIME);
		setNextRoundOfWaveDataAsActive();
	}
	
	/**
	 * A method that returns the Enemies to construct, given a current time
	 * @param aTotalTimeElapsed
	 * @return a map from enemy data to the spawn point corresponding to that enemy
	 * 
	 * TODO: This return type is kinda hacky... maybe make a custom class for this?
	 */
	public Map<EntityData, String> getEnemiesToConstruct(double aTotalTimeElapsed)
	{
		//1. Update the current time
		setCurrentTime(aTotalTimeElapsed);

		//2. dispatch next waves, if it's time
		if (isTimeToAddMoreWaves()) {
			setNextRoundOfWaveDataAsActive();
		}
		
		//3. get all the enemies
		Map<EntityData, String> enemiesToConstruct = new HashMap<EntityData, String>();
		for (WaveState activeWave: myWaveStates) {
			if (activeWave.canReleaseEnemy(aTotalTimeElapsed)) {
				EntityData enemy = myEnemyDataStore.getData(activeWave.releaseWaveEnemy(aTotalTimeElapsed));
				enemiesToConstruct.put(enemy, activeWave.getSpawnPointName());
			} else {
				myWaveStates.remove(activeWave);
			}
		}
		
		return enemiesToConstruct;
	}

	/**
	 * A method to add the next round of wave data to be set as active
	 * Assumes that multiple waves can be active at the same time
	 */
	private void setNextRoundOfWaveDataAsActive() {
		while (!myCurrLevelData.isEmpty()) {
			WaveData waveData = myCurrLevelData.popNextWaveData();
			myWaveStates.add(new WaveState(waveData, myCurrentTime));
			if (waveData.getTimeUntilNextWave() != 0) {
				updateTimeUntilNextTransition(waveData.getTimeUntilNextWave());
				break;
			}
		}
	}
	
	/**
	 * A method to determine if we can set more waves as active
	 * @return true if we can, false otherwise
	 */
	private boolean isTimeToAddMoreWaves()
	{
		return myCurrentTime > myTimeToAddMoreWaves;
	}
	
	/**
	 * Sets the current time to the input value
	 * Note that the time is specified in milliseconds
	 * @param aTotalTimeElapsed: a new current time
	 */
	private void setCurrentTime(double aTotalTimeElapsed)
	{
		myCurrentTime = aTotalTimeElapsed;
	}
	/**
	 * sets the time until the next wave is to be added, relative
	 * to the current time
	 * @param aDeltaTime: a wave duration
	 */
	private void updateTimeUntilNextTransition(double aDeltaTime)
	{
		myTimeToAddMoreWaves = myCurrentTime + aDeltaTime;
	}
}
