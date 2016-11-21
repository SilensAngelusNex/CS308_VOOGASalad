package engine.model.machine.tower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.model.resourcestore.IMoney;

public class TowerNode {
	private Tower myTowerId;
	private IMoney myPrice;
	private IMoney mySellPrice;
	private TowerNode myParent;
	private List<TowerNode> myChildren = new ArrayList<TowerNode>();
	private Map<TowerNode, IMoney> myUpgradeCostMap = new HashMap<TowerNode, IMoney>();
	
	public TowerNode(Tower id, TowerNode parent){
		myTowerId = id;
		myParent = parent;
	}
	
	public List<TowerNode> getChildren(){
		return myChildren;
	}
	
	public TowerNode getParent(){
		return myParent;
	}

	public Tower getID() {
		return myTowerId;
	}
	
	public IMoney getPrice(){
		return myPrice;
	}
	
}
