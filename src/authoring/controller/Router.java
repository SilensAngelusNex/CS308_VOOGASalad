package authoring.controller;
import authoring.view.side_panel.*;
import authoring.view.tabs.WaveTab;

public class Router {
	private MapDataContainer mdc = new MapDataContainer();
	private PlayerDataContainer pldc = new PlayerDataContainer();
	private LevelDataContainer ldc = new LevelDataContainer();
	private WaveDataContainer wadc = new WaveDataContainer();
	
	public void link(TowerTab tTab, EnemyTab eTab, WaveTab wTab, GameTab gTab){
		/**
		 * CONNECTIONS TO BE CREATED:
		 * MAP WILL NEED TO LISTEN TO: TERRAIN (SetChangeListener)
		 */
		
		//Listeners for WaveLevelTab
		mdc.attach(wTab); //Spawn point listener
		
		//Listeners for EnemyTab
		mdc.attach(eTab); //Terrain listener

                //TowerTab
		mdc.attach(tTab); //Terrain listener
		
		//GameTab
		
	}
	
	
	public MapDataContainer getMapDataController(){
		return mdc;
	}
	
	public PlayerDataContainer getPlayerDataController(){
		return pldc;
	}
	
	public WaveDataContainer getWaveDataController(){
		return wadc;
	}
	
	public LevelDataContainer getLevelDataController(){
		return ldc;
	}
}
