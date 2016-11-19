package engine;

//XXX matthewfaw: what do add and remove do? I don't recognize these as observer functionality
//I'm not sure that I want to go down the rabbit hole of making this interface templated
//We should talk about this
//The issue is that this forces the observable to know exactly what kind of observer it's observing
//which seems to be violating the data hiding that the observer/observable pattern provides
@Deprecated
public interface Observer<A> {
	abstract public void add(A aObjectToAdd);
	abstract public void update(A aObjectToUpdate);
	abstract public void remove(A aObjectToRemove);
}