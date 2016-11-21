package authoring.model.map;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class MapData {
	private int numXCells;
	private int numYCells;
	private HashSet<Point> spawnPoints;
	private HashSet<Point> sinkPoints;
	private HashSet<TerrainData> terrainList;
	
	public void setNumXCells(int x) throws Exception{
		if (x <= 0){
			throw new Exception("The map must be wider than 0 cells.");
		}
		this.numXCells = x;
	}
	public int getNumXCells(){
		return numXCells;
	}
	
	public void setNumYCells(int y) throws Exception{
		if (y <= 0){
			throw new Exception("The map must be taller than 0 cells.");
		}
	}
	public int getNumYCells(){
		return numYCells;
	}
	
	public void addSpawnPoint(Point newSpawnPoint) throws Exception{
		validatePoint(newSpawnPoint, "spawn");
		spawnPoints.add(newSpawnPoint);
	}
	
	public void removeSpawnPoint(Point p){
		if (spawnPoints.contains(p)){
			spawnPoints.remove(p);
		}
	}
	
	public Set<Point> getSpawnPoints(){
		return spawnPoints;
	}
	
	public void addSinkPoint(Point newSinkPoint) throws Exception{
		validatePoint(newSinkPoint, "sink");
		sinkPoints.add(newSinkPoint);
	}
	
	public void removeSinkPoint(Point p){
		if (sinkPoints.contains(p)){
			sinkPoints.remove(p);
		}
	}
	
	public Set<Point> getSinkPoints(){
		return sinkPoints;
	}
	
	public void addTerrainData(TerrainData terrain) throws Exception{
		validatePoint(terrain.getLoc(), "terrain");
		terrainList.add(terrain);
	}
	
	public void removeTerrainData(TerrainData terrain){
		if (terrainList.contains(terrain)){
			terrainList.remove(terrain);
		}
	}
	
	public Set<TerrainData> getTerrainList(){
		return terrainList;
	}
	
	private void validatePoint(Point p, String type) throws Exception{
		if (p.getX() >= numXCells || p.getX() < 0){
			throw new Exception("X location of " + type + " point not valid.");
		}
		if (p.getY() >= numYCells || p.getY() < 0){
			throw new Exception("Y location of " + type + " point not valid.");
		}
	}
}
