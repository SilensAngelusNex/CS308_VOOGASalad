package engine.model.machine.weapon;

import java.util.Map;

import authoring.model.ProjectileData;
import authoring.model.WeaponData;
import engine.IViewable;
import engine.Observer;
import engine.model.machine.weapon.projectile.ProjectileFactory;
import engine.model.machine.weapon.projectile.ProjectileOwner;

public class WeaponFactory {
	ProjectileFactory myProjectileFactory;
	
	public WeaponFactory(
			Observer<IViewable> observer,
			ProjectileOwner owner,
			Map<String, ProjectileData> projMap) {
		
		myProjectileFactory = new ProjectileFactory(observer, owner, projMap);
		
	}

	public Weapon newWeapon(WeaponData data, IKillerOwner owner) {
		return new Weapon(data, owner, myProjectileFactory);
	}
}
