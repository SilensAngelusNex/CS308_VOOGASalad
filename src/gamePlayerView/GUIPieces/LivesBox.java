package gamePlayerView.GUIPieces;

import engine.IObservable;
import engine.IObserver;

/**
 * @author Guhan Muruganandam
 */

/**
 * UI feature for Lives text box and Label
 */

public class LivesBox extends InfoBox implements IObserver {
	
	public LivesBox(){
		myDisplay=makeDisplay("Lives: ");
	}

	public void giveObject(IObservable aObservable) {
		aObservable.attach(this);
	}

	@Override
	public void update(Object aChangedObject) {
		myOutput.setText(aChangedObject.toString());
	}

}