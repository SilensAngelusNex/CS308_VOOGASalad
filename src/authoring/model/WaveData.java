package authoring.model;

public class WaveData {
	/**
	 * A String corresponding to the enemy name, which should have an EnemyData map entry
	 */
	private String waveEnemy;
	
	/**
	 * Time between each enemy (so they don't all show up at once).
	 */
	private double timeBetweenEnemy;
	
	/**
	 * Number of enemies in this wave.
	 */
	private int numEnemies;
	
	/**
	 * A String corresponding to the spawn point name, which should have an entry in the map.
	 */
	private String spawnPointName;
	
	public String getWaveEnemy() {
		return waveEnemy;
	}
	public void setWaveEnemy(String waveEnemy) throws Exception{
		if (waveEnemy == null || waveEnemy.length() == 0){
			throw new Exception("Enemy in wave must be specified.");
		}
		this.waveEnemy = waveEnemy;
	}
	
	public double getTimeBetweenEnemy() {
		return timeBetweenEnemy;
	}
	public void setTimeBetweenEnemy(double timeBetweenEnemy) throws Exception{
		if (timeBetweenEnemy < 0){
			throw new Exception("Time between enemy cannot be a negative number.");
		}
		this.timeBetweenEnemy = timeBetweenEnemy;
	}
	
	public int getNumEnemies(){
		return numEnemies;
	}
	public void setNumEnemies(int numEnemies) throws Exception{
		if (numEnemies < 0){
			throw new Exception("Number of enemies cannot be a negative number.");
		}
		this.numEnemies = numEnemies;
	}
	
	public String getSpawnPointName() {
		return spawnPointName;
	}
	public void setSpawnPointName(String spawnPointName) throws Exception{
		if (spawnPointName == null || spawnPointName.length() == 0){
			throw new Exception("Spawn point name must be specified.");
		}
		this.spawnPointName = spawnPointName;
	}
	
}
