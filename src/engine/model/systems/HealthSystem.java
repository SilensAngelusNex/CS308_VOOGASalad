package engine.model.systems;

import engine.model.components.IComponent;
import engine.model.components.concrete.HealthComponent;
import engine.model.weapons.DamageInfo;
import utility.Damage;

/**
 * A system to keep track of health and units taking damage
 * @author Weston
 * @author owenchung(edited)
 *
 */
public class HealthSystem extends AbstractSystem<HealthComponent> {
	
	/**
	 * Deals the damageToTake to the HealthComponent corresponding to target
	 * @param target
	 * @param damageToTake
	 * @return
	 */
	public DamageInfo dealDamageTo(IComponent target, Damage damageToTake) {
		HealthComponent health = getComponent(target);
		if (health != null) {
			return health.takeDamage(damageToTake);
		}
		return new DamageInfo();
	}
}
