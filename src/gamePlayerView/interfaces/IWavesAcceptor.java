package gamePlayerView.interfaces;

import engine.IObservable;

/**
 * WavesBox interface utilised by the router.
 */

public interface IWavesAcceptor{
	
 /**
 * Allows router to establish connection between backend and frontend object 
 */
	public void acceptWaves(IObservable aObservable);
	
}