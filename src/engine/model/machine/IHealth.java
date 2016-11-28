package engine.model.machine;

/**
 * Wrapper interface for health
 *
 */
public interface IHealth {
	public int getHealth();
	public void updateHealth(IHealth deltaHealth);
}