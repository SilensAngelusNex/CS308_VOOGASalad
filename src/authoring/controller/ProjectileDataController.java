package authoring.controller;

import java.util.AbstractMap;

import authoring.model.ProjectileData;

public class ProjectileDataController {

	private AbstractMap<String, ProjectileData> myProjectileDataMap;

	public AbstractMap getProjectileDataMap(){
		return myProjectileDataMap;
	}
	
	public void createProjectileData(String projectileName, ProjectileData projectileData){
		//Parse the FrontEndEnemy object
		//Error check
		//Add it to map
		myProjectileDataMap.put(projectileName, projectileData);
	}
	
	
	public ProjectileData getProjectileData(String projectileName){
		return myProjectileDataMap.get(projectileName);
	}
	
	
	public void updateTowerData(String originalName, String updatedProjectile){
		//Find old enemyData in map
		//create new EnemyData Object from FrontEndEnemy
	}
}