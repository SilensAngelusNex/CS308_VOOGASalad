package engine.model.components.concrete;

import java.util.ArrayList;


import java.util.List;

import authoring.model.ComponentData;
import authoring.model.Hide;
import engine.IObserver;
import engine.model.components.AbstractComponent;
import engine.model.components.viewable_interfaces.IViewableHealth;
import engine.model.systems.BountySystem;
import engine.model.systems.DamageDealingSystem;
import engine.model.systems.HealthSystem;
import engine.model.weapons.DamageInfo;
import gamePlayerView.gamePlayerView.Router;
import utility.Damage;

/**
 * The purpose of this class is to manage the health information
 * relevant to an entity
 * manages how an entity should take damage
 * @author matthewfaw
 * @author Weston
 * @author owenchung (edits)
 * @author alanguo (edits)
 */
public class HealthComponent extends AbstractComponent implements IViewableHealth {
	@Hide
	private static double DEFAULT_HEALTH = 0.0;
	@Hide
	private BountySystem myBounty;
	@Hide
	private DamageDealingSystem myDamage;
	@Hide
	private HealthSystem myHealthSystem;
	@Hide
	private Double myCurrHealth;
	private Double myMaxHealth;
	private boolean explodeOnDeath;
	
	@Hide
	private List<IObserver<IViewableHealth>> myObservers;
	
	@Hide
	private Router myRouter;
	
	public HealthComponent(HealthSystem healthSystem, BountySystem bounty, DamageDealingSystem damage, ComponentData componentdata, Router router) {
		super(router);
		myHealthSystem = healthSystem;
		
		myBounty = bounty;
		myDamage = damage;
		
		myCurrHealth = Double.parseDouble(componentdata.getFields().get("myCurrHealth"));
		myMaxHealth = Double.parseDouble(componentdata.getFields().get("myMaxHealth"));
		explodeOnDeath = Boolean.parseBoolean(componentdata.getFields().get("explodeOnDeath"));
		
		myObservers = new ArrayList<IObserver<IViewableHealth>>();
		
		healthSystem.attachComponent(this);
		
	}
		
	public int getCurrentHealth() {
		return myCurrHealth.intValue();
	}
	
	public void setCurrentHealth(double newHealthValue) {
		myCurrHealth = newHealthValue;
		//notifyObservers();
	}
	
	public DamageInfo takeDamage(Damage dmg) {
		double newCurrHealth = myCurrHealth - dmg.getDamage();
		
		if (newCurrHealth < 0) {
			setCurrentHealth(0);
		}
		if (newCurrHealth > myMaxHealth) {
			setCurrentHealth(myMaxHealth);
		}
		
		int died = myCurrHealth <= 0 ? 1 : 0;
		int bounty = myBounty.collectBounty(this);
		
		if (died == 1 && explodeOnDeath)
			myDamage.explode(this);
		
		return new DamageInfo(dmg.getDamage(), died, bounty);		
	}
	
	public void setMaxHealth(double m) {
		myMaxHealth = m;
		
		if (myCurrHealth > myMaxHealth) {
			myCurrHealth = myMaxHealth;
		}
	}
	
	/********* Observable Interface **********/

	@Override
	public void attach(IObserver<IViewableHealth> aObserver) {
		myObservers.add(aObserver);
	}

	@Override
	public void detach(IObserver<IViewableHealth> aObserver) {
		myObservers.remove(aObserver);
	}

	@Override
	public void notifyObservers() {
		myObservers.forEach(observer -> observer.update(this));
	}

	@Override
	public double getMaxHealth() {
		return myMaxHealth;
	}

	@Override
	public double getCurrHealth() {
		return myCurrHealth;
	}

	@Override
	public void distributeInfo() {
		getRouter().distributeViewableComponent(this);
	}
	
	@Override
	public String getEntityID() {
		return getEntity().getId();
	}

	public void delete() {
		myHealthSystem.detachComponent(this);
		myObservers.forEach(observer -> observer.remove(this));
	}

}