package authoring.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import engine.model.components.PurchasableComponentData;
import engine.model.components.SellableComponentData;
import engine.model.components.UpgradableComponentData;

/**
 * @author owenchung and alanguo
 */

public class EntityData implements IReadableData {
	private String myName;
	Map<String, ComponentData> myComponents;
	Optional<UpgradableComponentData> myUpgradeData;
	Optional<SellableComponentData> mySellData;
	Optional<PurchasableComponentData> myPurchaseData;
	
	public EntityData()
	{
		myComponents = new HashMap<String, ComponentData>();
	}
	
	public String getName(){
		return myName;
	}
	
	public void setName(String s){
		this.myName = s;
	}
	
	public void addComponent(String aName, ComponentData comp){
		myComponents.put(aName, comp);
	}
	
	public Map<String, ComponentData> getComponents(){
		return myComponents;
	}

	public int getBuyPrice() {
		return myPurchaseData.isPresent() ? myPurchaseData.get().getBuyPrice() : Integer.MAX_VALUE;
	}

	public int getSellPrice() {
		return mySellData.isPresent() ? mySellData.get().getSellValue() : 0;
	}

	public Map<String, Integer> getUpgrades() {
		return myUpgradeData.isPresent() ? myUpgradeData.get().getUpgrades() : new HashMap<String, Integer>();
	}

}
