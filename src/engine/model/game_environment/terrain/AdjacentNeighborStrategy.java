package engine.model.game_environment.terrain;

import java.util.ArrayList;
import java.util.List;

import utility.Index;

public class AdjacentNeighborStrategy implements INeighborStrategy<Terrain> {
	
	private TerrainMap myMap;
	
	public AdjacentNeighborStrategy(TerrainMap aTerrainMap)
	{
		myMap = aTerrainMap;
	}

	@Override
	public List<Terrain> getNeighbors(Terrain aTerrainNode) {
		List<Terrain> neighbors = new ArrayList<Terrain>();

		for (Index index: getNeighborIndices(aTerrainNode.getIndexInMap())) {
			neighbors.add(myMap.getTerrain(index));
		}

		return neighbors;
	}

	private List<Index> getNeighborIndices(Index aIndex)
	{
		List<Index> neighborIndices = new ArrayList<Index>();
		
		int x = aIndex.getX();
		int y = aIndex.getY();
		
		if (x+1 < myMap.getHeight()) {
			neighborIndices.add(new Index(x+1, y));
		}
		if (x-1 > 0) {
			neighborIndices.add(new Index(x-1, y));
		}
		if (y+1 < myMap.getWidth()) {
			neighborIndices.add(new Index(x, y+1));
		}
		if (y-1 > 0) {
			neighborIndices.add(new Index(x, y-1));
		}
		
		return neighborIndices;
	}

}
